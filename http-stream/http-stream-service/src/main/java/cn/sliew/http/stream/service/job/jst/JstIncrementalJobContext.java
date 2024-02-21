package cn.sliew.http.stream.service.job.jst;

import akka.actor.typed.ActorSystem;
import cn.sliew.http.stream.akka.framework.ParallelJobContext;
import cn.sliew.http.stream.akka.framework.SyncOffsetManager;
import cn.sliew.http.stream.akka.framework.base.AbstractJobContext;
import cn.sliew.http.stream.common.util.DateUtil;
import cn.sliew.http.stream.dao.entity.job.JobInstance;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.Properties;

public abstract class JstIncrementalJobContext extends AbstractJobContext<JobInstance> implements ParallelJobContext<JobInstance> {

    private final SyncOffsetManager syncOffsetManager;

    public JstIncrementalJobContext(Long jobId, Long jobInstanceId, JobInstance job, Properties properties, MeterRegistry meterRegistry, ActorSystem actorSystem, SyncOffsetManager syncOffsetManager) {
        super(jobId, jobInstanceId, job, properties, meterRegistry, actorSystem);
        this.syncOffsetManager = syncOffsetManager;
    }

    @Override
    public SyncOffsetManager getSyncOffsetManager() {
        return syncOffsetManager;
    }

    @Override
    public String getFinalSyncOffset() {
        return DateUtil.formatDateTime(DateUtil.lastHour());
    }
}
