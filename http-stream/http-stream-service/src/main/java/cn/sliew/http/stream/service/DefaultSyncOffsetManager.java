package cn.sliew.http.stream.service;

import cn.sliew.http.stream.akka.framework.SubTask;
import cn.sliew.http.stream.akka.framework.SyncOffsetJobContext;
import cn.sliew.http.stream.akka.framework.SyncOffsetManager;
import cn.sliew.http.stream.dao.entity.job.JobInfo;
import cn.sliew.http.stream.dao.entity.job.JobSyncOffset;
import cn.sliew.http.stream.dao.mapper.job.JobSyncOffsetMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Component;

@Component
public class DefaultSyncOffsetManager<Context extends SyncOffsetJobContext> implements SyncOffsetManager<Context> {

    private JobSyncOffsetMapper jobSyncOffsetMapper;

    public DefaultSyncOffsetManager(JobSyncOffsetMapper jobSyncOffsetMapper) {
        this.jobSyncOffsetMapper = jobSyncOffsetMapper;
    }

    @Override
    public JobSyncOffset getSyncOffset(SyncOffsetJobContext context) {
        JobInfo job = context.getJob();


        LambdaQueryWrapper<JobSyncOffset> queryWrapper = Wrappers.lambdaQuery(JobSyncOffset.class)
                .eq(JobSyncOffset::getGroup, context.getGroup())
                .eq(JobSyncOffset::getJob, context.getJob())
                .eq(context.getSubJob().isPresent(), JobSyncOffset::getSubJob, context.getSubJob());

        JobSyncOffset syncOffset = jobSyncOffsetMapper.selectOne(queryWrapper);
        if (syncOffset != null) {
            return syncOffset;
        }
        initSyncOffset(context);

        return getSyncOffset(context);
    }

    @Override
    public void updateSyncOffset(SyncOffsetJobContext context, SubTask subTask) {

    }

    @Override
    public void initSyncOffset(SyncOffsetJobContext context) {

    }

    @Override
    public void resetSyncOffset(SyncOffsetJobContext context) {

    }

    @Override
    public void initSyncOffset(SimpleJobContext context) {
        JobSyncOffset record = new JobSyncOffset();
        record.setGroup(context.getGroup());
        record.setJob(context.getJob());
        context.getSubJob().ifPresent(subJob -> record.setSubJob(subJob));
        record.setSyncOffset(context.getInitialSyncOffset());
        record.setCreator("sync-offset-manager");
        record.setModifier("sync-offset-manager");
        jobSyncOffsetMapper.insert(record);
    }

    @Override
    public void updateSyncOffset(SimpleJobContext context, String syncOffset) {
        JobSyncOffset jobSyncOffset = getSyncOffset(context);

        LambdaUpdateWrapper<JobSyncOffset> updateWrapper = Wrappers.lambdaUpdate(JobSyncOffset.class)
                .eq(JobSyncOffset::getGroup, context.getGroup())
                .eq(JobSyncOffset::getJob, context.getJob())
                .eq(context.getSubJob().isPresent(), JobSyncOffset::getSubJob, context.getSubJob());

        JobSyncOffset record = new JobSyncOffset();
        record.setGroup(context.getGroup());
        record.setJob(context.getJob());
        context.getSubJob().ifPresent(subJob -> record.setSubJob(subJob));
        record.setLastSyncOffset(jobSyncOffset.getSyncOffset());
        record.setSyncOffset(syncOffset);
        record.setModifier("sync-offset-manager");
        jobSyncOffsetMapper.update(record, updateWrapper);
    }

    @Override
    public void resetSyncOffset(SimpleJobContext context) {
        LambdaUpdateWrapper<JobSyncOffset> updateWrapper = Wrappers.lambdaUpdate(JobSyncOffset.class)
                .eq(JobSyncOffset::getGroup, context.getGroup())
                .eq(JobSyncOffset::getJob, context.getJob())
                .eq(context.getSubJob().isPresent(), JobSyncOffset::getSubJob, context.getSubJob());
        jobSyncOffsetMapper.delete(updateWrapper);
        initSyncOffset(context);
    }
}
