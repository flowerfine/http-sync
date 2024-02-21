package cn.sliew.http.stream.akka.framework;

public interface SyncOffsetJobContext<Job, Authorization> extends JobContext<Job, Authorization> {

    SyncOffsetManager getSyncOffsetManager();

    String getInitialSyncOffset();

    String getFinalSyncOffset();
}
