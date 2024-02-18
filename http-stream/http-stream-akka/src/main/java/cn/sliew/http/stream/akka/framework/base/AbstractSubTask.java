package cn.sliew.http.stream.akka.framework.base;

import akka.Done;
import akka.NotUsed;
import akka.actor.typed.ActorSystem;
import akka.stream.ActorAttributes;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import cn.sliew.http.stream.akka.framework.JobContext;
import cn.sliew.http.stream.akka.framework.ProcessResult;
import cn.sliew.http.stream.akka.framework.SubTask;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public abstract class AbstractSubTask<Context extends JobContext, T>
        implements SubTask<Context> {

    private final Long id;

    public AbstractSubTask(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    /**
     * akka.actor.source-dispatcher 和 akka.actor.sink-dispatcher，指定 source 和 sink 阶段的 akka dispatcher
     */
    @Override
    public CompletableFuture<ProcessResult> execute(Context context) {
        ActorSystem actorSystem = context.getActorSystem();
        Source<T, NotUsed> source = fetch(context).withAttributes(ActorAttributes.dispatcher("akka.actor.source-dispatcher"));
        Sink<T, CompletionStage<Done>> sink = Sink.<T>foreachParallel(10, data -> persist(context, data), actorSystem.executionContext())
                .withAttributes(ActorAttributes.dispatcher("akka.actor.sink-dispatcher"));
        CompletionStage completionStage = source.runWith(sink, actorSystem);
        return completionStage.thenApply(done -> ProcessResult.success(this)).toCompletableFuture();
    }

    protected abstract Source<T, NotUsed> fetch(Context context);

    protected abstract void persist(Context context, T data);
}
