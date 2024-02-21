package cn.sliew.http.stream.akka.framework;

public interface SyncOffsetJobContext<Job> extends JobContext<Job> {

    SyncOffsetManager getSyncOffsetManager();

    String getInitialSyncOffset();

    String getFinalSyncOffset();
}
