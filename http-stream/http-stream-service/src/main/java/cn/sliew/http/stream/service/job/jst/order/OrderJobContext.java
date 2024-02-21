package cn.sliew.http.stream.service.job.jst.order;

import akka.actor.typed.ActorSystem;
import cn.sliew.http.stream.akka.framework.SyncOffsetManager;
import cn.sliew.http.stream.common.util.DateUtil;
import cn.sliew.http.stream.dao.entity.job.JobAuthorization;
import cn.sliew.http.stream.dao.entity.job.JobInfo;
import cn.sliew.http.stream.service.job.jst.JstIncrementalJobContext;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.Properties;

class OrderJobContext extends JstIncrementalJobContext {

    public OrderJobContext(Long jobId, Long jobInstanceId, JobInfo job, JobAuthorization authorization, Properties properties, MeterRegistry meterRegistry, ActorSystem actorSystem, SyncOffsetManager syncOffsetManager) {
        super(jobId, jobInstanceId, job, authorization, properties, meterRegistry, actorSystem, syncOffsetManager);
    }

    @Override
    public String getInitialSyncOffset() {
        return DateUtil.formatDateTime(DateUtil.lastThreeDay());
    }
}
