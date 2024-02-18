package cn.sliew.http.stream.akka.framework;

public interface SyncOffsetJobContext<Root extends RootTask, Sub extends SubTask> extends JobContext<Root, Sub> {

    SyncOffsetManager getSyncOffsetManager();

    String getInitialSyncOffset();

    String getFinalSyncOffset();
}
