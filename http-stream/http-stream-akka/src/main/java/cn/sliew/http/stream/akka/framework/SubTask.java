package cn.sliew.http.stream.akka.framework;

import java.util.concurrent.CompletableFuture;

public interface SubTask<Context extends JobContext> extends Lifecycle<Context> {

    Long getId();

    CompletableFuture<ProcessResult> execute(Context context);
}
