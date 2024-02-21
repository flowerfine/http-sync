package cn.sliew.http.stream.service.job.jst.order;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.SpawnProtocol;
import cn.sliew.http.stream.akka.AbstractJobSync;
import cn.sliew.http.stream.akka.framework.JobContext;
import cn.sliew.http.stream.akka.framework.RootTask;
import cn.sliew.http.stream.akka.framework.SyncOffsetManager;
import cn.sliew.http.stream.dao.mapper.job.JobInstanceMapper;
import cn.sliew.http.stream.dao.mapper.jst.JstOrderMapper;
import cn.sliew.http.stream.remote.jst.JstRemoteService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderStreamJob extends AbstractJobSync {

    @Autowired
    private JobInstanceMapper jobInstanceMapper;
    @Autowired
    private SyncOffsetManager syncOffsetManager;
    @Autowired
    private JstRemoteService jstRemoteService;
    @Autowired
    private JstOrderMapper jstOrderMapper;

    public OrderStreamJob(MeterRegistry meterRegistry, ActorSystem<SpawnProtocol.Command> actorSystem) {
        super(meterRegistry, actorSystem);
    }

    @Override
    protected JobContext buildJobContext(Object param) {
        Long id = (Long) param;
        return new OrderJobContext(id, System.currentTimeMillis(), jobInstanceMapper.selectById(id), properties, meterRegistry, actorSystem, syncOffsetManager);
    }

    @Override
    protected RootTask buildRootTask(Object param) {
        return new OrderRootTask(jstRemoteService, jstOrderMapper);
    }
}
