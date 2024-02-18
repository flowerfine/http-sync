package cn.sliew.http.stream.akka.framework;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class ProcessResult<Context extends JobContext> implements Result<Context> {

    private boolean success = false;

    private String message;

    private Throwable throwable;

    private Optional<SubTask<Context>> subTask;

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Throwable getThrowable() {
        return throwable;
    }

    @Override
    public Optional<SubTask<Context>> getSubTask() {
        return subTask;
    }

    public static ProcessResult success() {
        ProcessResult result = new ProcessResult();
        result.setSuccess(true);
        result.setSubTask(Optional.empty());
        result.setMessage("success");
        return result;
    }

    public static ProcessResult success(SubTask subTask) {
        ProcessResult result = new ProcessResult();
        result.setSuccess(true);
        result.setSubTask(Optional.ofNullable(subTask));
        result.setMessage("success");
        return result;
    }

    public static ProcessResult failure(SubTask subTask) {
        return failure(subTask, "internal error");
    }

    public static ProcessResult failure(SubTask subTask, String message) {
        ProcessResult result = new ProcessResult();
        result.setSuccess(false);
        result.setSubTask(Optional.ofNullable(subTask));
        result.setMessage(message);
        return result;
    }

    public static ProcessResult failure(SubTask subTask, Throwable throwable) {
        ProcessResult result = new ProcessResult();
        result.setSuccess(false);
        result.setThrowable(throwable);
        result.setSubTask(Optional.ofNullable(subTask));
        result.setMessage(throwable.getMessage());
        return result;
    }

}
