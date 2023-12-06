package cn.sliew.http.stream.akka.framework2;

import cn.sliew.http.stream.akka.framework.RootTask;
import cn.sliew.http.stream.akka.framework.SubTask;

public interface ParalleJobContext<Root extends RootTask, Sub extends SubTask>
        extends JobContext2<Root, Sub> {

    default int getSubTaskParallelism() {
        return 10;
    }

    default int getSubTaskBatchSize() {
        return 100;
    }
}
