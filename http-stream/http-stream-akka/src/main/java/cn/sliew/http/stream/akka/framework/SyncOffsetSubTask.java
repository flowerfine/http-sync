package cn.sliew.http.stream.akka.framework;

public interface SyncOffsetSubTask<Context extends SyncOffsetJobContext> extends SubTask<Context> {

    String getStartSyncOffset();

    String getEndSyncOffset();
}
