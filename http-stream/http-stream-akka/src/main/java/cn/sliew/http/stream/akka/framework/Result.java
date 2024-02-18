package cn.sliew.http.stream.akka.framework;

import java.util.Optional;

public interface Result<Context extends JobContext> {

    boolean isSuccess();

    String getMessage();

    Throwable getThrowable();

    Optional<SubTask<Context>> getSubTask();
}
