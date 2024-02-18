package cn.sliew.http.stream.akka.framework;

public interface Parallel {

    default int getSubTaskParallelism() {
        return 10;
    }

    default int getSubTaskBatchSize() {
        return 100;
    }
}
