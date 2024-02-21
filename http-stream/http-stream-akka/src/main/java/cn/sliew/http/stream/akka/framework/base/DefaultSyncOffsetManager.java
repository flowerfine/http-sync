package cn.sliew.http.stream.akka.framework.base;

import cn.sliew.http.stream.akka.framework.SyncOffsetJobContext;
import cn.sliew.http.stream.akka.framework.SyncOffsetManager;
import cn.sliew.http.stream.akka.framework.SyncOffsetSubTask;
import cn.sliew.http.stream.dao.entity.job.JobInstance;
import cn.sliew.http.stream.dao.entity.job.JobSyncOffset;
import cn.sliew.http.stream.dao.mapper.job.JobSyncOffsetMapper;
import org.springframework.stereotype.Component;

@Component
public class DefaultSyncOffsetManager<Context extends SyncOffsetJobContext<JobInstance>, Sub extends SyncOffsetSubTask> implements SyncOffsetManager<Context, Sub> {

    private JobSyncOffsetMapper jobSyncOffsetMapper;

    public DefaultSyncOffsetManager(JobSyncOffsetMapper jobSyncOffsetMapper) {
        this.jobSyncOffsetMapper = jobSyncOffsetMapper;
    }

    @Override
    public JobSyncOffset getSyncOffset(Context context) {
        JobSyncOffset syncOffset = jobSyncOffsetMapper.selectById(context.getJobInfo().getSyncOffsetId());
        if (syncOffset != null) {
            return syncOffset;
        }
        initSyncOffset(context);

        return getSyncOffset(context);
    }

    @Override
    public void updateSyncOffset(Context context, Sub subTask) {
        JobSyncOffset jobSyncOffset = getSyncOffset(context);
        JobSyncOffset record = new JobSyncOffset();
        record.setId(context.getJobInfo().getSyncOffsetId());
        record.setLastSyncOffset(jobSyncOffset.getSyncOffset());
        record.setSyncOffset(subTask.getEndSyncOffset());
        record.setModifier("sync-offset-manager");
        jobSyncOffsetMapper.updateById(record);
    }

    @Override
    public void initSyncOffset(Context context) {
        JobSyncOffset record = new JobSyncOffset();
        record.setSyncOffset(context.getInitialSyncOffset());
        record.setCreator("sync-offset-manager");
        record.setModifier("sync-offset-manager");
        jobSyncOffsetMapper.insert(record);
    }

    @Override
    public void resetSyncOffset(Context context) {
        jobSyncOffsetMapper.deleteById(context.getJobInfo().getSyncOffsetId());
        initSyncOffset(context);
    }
}
