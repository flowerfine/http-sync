package cn.sliew.http.stream.akka;

import akka.Done;
import akka.NotUsed;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.SpawnProtocol;
import akka.japi.Pair;
import akka.stream.*;
import akka.stream.javadsl.*;
import cn.sliew.http.stream.akka.framework.*;
import cn.sliew.http.stream.akka.framework.base.DefaultIncrementalJobProcessor;
import cn.sliew.http.stream.akka.framework.base.DefaultJobProcessor;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;
import java.util.concurrent.CompletionStage;

@Slf4j
public abstract class AbstractJob {

    protected Properties properties = new Properties();
    protected MeterRegistry meterRegistry;
    protected ActorSystem<SpawnProtocol.Command> actorSystem;

    public AbstractJob(MeterRegistry meterRegistry,
                       ActorSystem<SpawnProtocol.Command> actorSystem) {
        this.meterRegistry = meterRegistry;
        this.actorSystem = actorSystem;
    }

    protected void execute(Object param) {
        if (doExecuteBefore(param)) {
            Pair<UniqueKillSwitch, CompletionStage<Done>> pair = doExecuteAsync(param);
            handleExecuteAsyncResult(param, pair);
            pair.second().whenComplete((unused, throwable) -> doExecuteAfter(param));
        }
    }

    boolean doExecuteBefore(Object param) {
        return true;
    }

    Pair<UniqueKillSwitch, CompletionStage<Done>> doExecuteAsync(Object param) {
        JobContext context = buildJobContext(param);
        JobProcessor processor = buildJobProcessor(context);
        RootTask rootTask = buildRootTask(param);

        Source<SubTask, UniqueKillSwitch> source = Source.single(rootTask)
                .mapConcat(root -> processor.map(root))
                .viaMat(KillSwitches.single(), Keep.right());

        Flow<SubTask, ProcessResult, NotUsed> process = Flow.<SubTask>create()
                .map(subTask -> processor.process(subTask)).mapAsync(1, future -> future);

        Flow<SubTask, ProcessResult, NotUsed> subTasks =
                Flow.fromGraph(
                        GraphDSL.create(
                                b -> {
                                    int concurrency = getParallelism(context);
                                    UniformFanOutShape<SubTask, SubTask> partition =
                                            b.add(Partition.create(concurrency, subTask -> Math.toIntExact(subTask.getId()) % concurrency));
                                    UniformFanInShape<ProcessResult, ProcessResult> merge =
                                            b.add(MergeSequence.create(concurrency, result -> result.getSubTask().get()));

                                    for (int i = 0; i < concurrency; i++) {
                                        b.from(partition.out(i))
                                                .via(b.add(process.async()))
                                                .viaFanIn(merge);
                                    }

                                    return FlowShape.of(partition.in(), merge.out());
                                }));

        return source.via(subTasks)
                .log(context.getJobName())
                .toMat(Sink.foreach(result -> processor.reduce(result)), Keep.both())
                .run(actorSystem);
    }

    abstract void handleExecuteAsyncResult(Object param, Pair<UniqueKillSwitch, CompletionStage<Done>> pair);

    void doExecuteAfter(Object param) {

    }

    protected abstract JobContext buildJobContext(Object param);

    protected JobProcessor buildJobProcessor(JobContext context) {
        if (context instanceof SyncOffsetJobContext) {
            return new DefaultIncrementalJobProcessor((SyncOffsetJobContext) context);
        }
        return new DefaultJobProcessor(context);
    }

    protected abstract RootTask buildRootTask(Object param);

    private int getParallelism(JobContext context) {
        if (context instanceof Parallel) {
            Parallel parallel = (Parallel) context;
            int subTaskParallelism = parallel.getSubTaskParallelism();
            return subTaskParallelism >= 2 ? subTaskParallelism : 2;
        }
        return 2;
    }
}
