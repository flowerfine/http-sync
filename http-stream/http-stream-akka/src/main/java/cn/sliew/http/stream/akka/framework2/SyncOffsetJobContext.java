package cn.sliew.http.stream.akka.framework2;

import cn.sliew.http.stream.akka.framework.RootTask;
import cn.sliew.http.stream.akka.framework.SubTask;

public interface SyncOffsetJobContext<Root extends RootTask, Sub extends SubTask> extends ParalleJobContext<Root, Sub> {

    SyncOffsetManager getSyncOffsetManager();

    String getInitialSyncOffset();

    String getFinalSyncOffset();
}
