package cn.sliew.http.stream.akka.jst.order;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.SpawnProtocol;
import cn.sliew.http.stream.akka.AbstractJobSync;
import cn.sliew.http.stream.akka.enums.JstJob;
import cn.sliew.http.stream.akka.framework.JobContext;
import cn.sliew.http.stream.akka.framework.RootTask;
import cn.sliew.http.stream.dao.mapper.job.JobSyncOffsetMapper;
import cn.sliew.http.stream.dao.mapper.jst.JstOrderMapper;
import cn.sliew.http.stream.remote.jst.JstRemoteService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderStreamJob extends AbstractJobSync {

    private JstJob job = JstJob.ORDERS_SINGLE_QUERY;

    @Autowired
    private JobSyncOffsetMapper jobSyncOffsetMapper;
    @Autowired
    private JstRemoteService jstRemoteService;
    @Autowired
    private JstOrderMapper jstOrderMapper;

    public OrderStreamJob(MeterRegistry meterRegistry, ActorSystem<SpawnProtocol.Command> actorSystem) {
        super(meterRegistry, actorSystem);
    }

    @Override
    protected JobContext buildJobContext(Object param) {
        return new OrderJobContext(1L, 1L, null, null, properties, meterRegistry, actorSystem, null);
    }

    @Override
    protected RootTask buildRootTask(Object param) {
        return new OrderRootTask(jstRemoteService, jstOrderMapper);
    }
}
