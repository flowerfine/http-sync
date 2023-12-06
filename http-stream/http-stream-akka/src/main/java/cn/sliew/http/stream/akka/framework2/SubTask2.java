package cn.sliew.http.stream.akka.framework2;

import cn.sliew.http.stream.akka.framework.ProcessResult;

import java.util.concurrent.CompletableFuture;

public interface SubTask2<Context extends JobContext2> {

    Long getIdentifier();

    CompletableFuture<ProcessResult> execute(Context context);
}
