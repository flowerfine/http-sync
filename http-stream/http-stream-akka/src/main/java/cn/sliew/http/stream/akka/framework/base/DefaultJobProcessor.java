package cn.sliew.http.stream.akka.framework.base;

import cn.sliew.http.stream.akka.framework.*;
import cn.sliew.milky.common.exception.Rethrower;
import cn.sliew.milky.common.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class DefaultJobProcessor<Context extends JobContext, Root extends RootTask, Sub extends SubTask>
        implements JobProcessor<Context, Root, Sub> {

    protected final Context context;

    public DefaultJobProcessor(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public List<Sub> map(Root rootTask) {
        return rootTask.split(context);
    }

    @Override
    public CompletableFuture<ProcessResult> process(Sub subTask) {
        return subTask.execute(context);
    }

    @Override
    public ProcessResult reduce(ProcessResult result) {
        if (result.isSuccess() == false) {
            log.error("{}-{}, 子任务处理失败: {}!", context.getJobId(), context.getJobInstanceId(), result.getMessage(), result.getThrowable());
            if (result.getThrowable() != null) {
                Rethrower.throwAs(result.getThrowable());
            }
            throw new RuntimeException(result.getMessage());
        }
        Optional<Sub> optional = result.getSubTask();
        if (optional.isPresent()) {
            Sub subTask = optional.get();
            handleSubTaskResult(subTask);
            log.debug("{}-{}, 子任务处理成功! 子任务详情: {}", context.getJobId(), context.getJobInstanceId(), JacksonUtil.toJsonString(subTask));
            return ProcessResult.success();
        }
        log.debug("{}-{}, 子任务处理成功!", context.getJobId(), context.getJobInstanceId());
        return ProcessResult.success();
    }

    protected void handleSubTaskResult(Sub subTask) {

    }
}
