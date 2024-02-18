package cn.sliew.http.stream.akka.framework.base;

import akka.actor.typed.ActorSystem;
import cn.sliew.http.stream.akka.framework.JobContext;
import cn.sliew.http.stream.akka.framework.RootTask;
import cn.sliew.http.stream.akka.framework.SubTask;
import cn.sliew.http.stream.dao.entity.job.JobAuthorization;
import cn.sliew.http.stream.dao.entity.job.JobInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.Properties;

public abstract class AbstractJobContext<Root extends RootTask, Sub extends SubTask>
        implements JobContext<Root, Sub> {

    private final Long jobId;
    private final Long jobInstanceId;
    private final JobInfo job;
    private final JobAuthorization authorization;
    private final Properties properties;
    private final MeterRegistry meterRegistry;
    private final ActorSystem actorSystem;

    public AbstractJobContext(Long jobId, Long jobInstanceId, JobInfo job, JobAuthorization authorization, Properties properties, MeterRegistry meterRegistry, ActorSystem actorSystem) {
        this.jobId = jobId;
        this.jobInstanceId = jobInstanceId;
        this.job = job;
        this.authorization = authorization;
        this.properties = properties;
        this.meterRegistry = meterRegistry;
        this.actorSystem = actorSystem;
    }

    @Override
    public Long getJobId() {
        return jobId;
    }

    @Override
    public Long getJobInstanceId() {
        return jobInstanceId;
    }

    @Override
    public JobInfo getJob() {
        return job;
    }

    @Override
    public JobAuthorization getAuthorization() {
        return authorization;
    }

    @Override
    public Properties getProperties() {
        return properties;
    }

    @JsonIgnore
    @Override
    public MeterRegistry getMetrics() {
        return meterRegistry;
    }

    @JsonIgnore
    @Override
    public ActorSystem getActorSystem() {
        return actorSystem;
    }
}
