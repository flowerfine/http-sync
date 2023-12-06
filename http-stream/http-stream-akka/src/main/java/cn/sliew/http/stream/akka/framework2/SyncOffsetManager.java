package cn.sliew.http.stream.akka.framework2;

import cn.sliew.http.stream.dao.entity.job.JobSyncOffset;

public interface SyncOffsetManager<Context extends SyncOffsetJobContext, Sub extends SubTask2> {

    JobSyncOffset getSyncOffset(Context context);

    void updateSyncOffset(Context context, Sub subTask);

    void initSyncOffset(Context context);

    void resetSyncOffset(Context context);

}
