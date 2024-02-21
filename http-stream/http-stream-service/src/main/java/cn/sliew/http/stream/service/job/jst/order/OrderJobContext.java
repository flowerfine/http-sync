package cn.sliew.http.stream.service.job.jst.order;

import akka.actor.typed.ActorSystem;
import cn.sliew.http.stream.akka.framework.SyncOffsetManager;
import cn.sliew.http.stream.common.util.DateUtil;
import cn.sliew.http.stream.dao.entity.job.JobInstance;
import cn.sliew.http.stream.service.enums.JstJob;
import cn.sliew.http.stream.service.job.jst.JstIncrementalJobContext;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.Properties;

class OrderJobContext extends JstIncrementalJobContext {

    public OrderJobContext(Long jobId, Long jobInstanceId, JobInstance job, Properties properties, MeterRegistry meterRegistry, ActorSystem actorSystem, SyncOffsetManager syncOffsetManager) {
        super(jobId, jobInstanceId, job, properties, meterRegistry, actorSystem, syncOffsetManager);
    }

    @Override
    public String getJobName() {
        return JstJob.ORDERS_SINGLE_QUERY.getJob();
    }

    @Override
    public String getInitialSyncOffset() {
        return DateUtil.formatDateTime(DateUtil.lastThreeDay());
    }
}
