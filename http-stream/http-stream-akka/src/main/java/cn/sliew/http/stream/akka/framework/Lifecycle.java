package cn.sliew.http.stream.akka.framework;

public interface Lifecycle<Context extends JobContext> {

    default ProcessResult preStart(Context context) {
        return ProcessResult.success();
    }

    default void post(Context context) {

    }
}
