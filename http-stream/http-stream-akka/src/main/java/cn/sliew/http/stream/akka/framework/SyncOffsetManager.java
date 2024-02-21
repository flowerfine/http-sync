package cn.sliew.http.stream.akka.framework;

import cn.sliew.http.stream.dao.entity.job.JobSyncOffset;

public interface SyncOffsetManager<Context extends SyncOffsetJobContext, Sub extends SyncOffsetSubTask> {

    JobSyncOffset getSyncOffset(Context context);

    void updateSyncOffset(Context context, Sub subTask);

    void initSyncOffset(Context context);

    void resetSyncOffset(Context context);
}
