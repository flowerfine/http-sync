package cn.sliew.http.stream.akka.framework.base;

import akka.actor.typed.ActorSystem;
import cn.sliew.http.stream.akka.framework.JobContext;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.Properties;

public abstract class AbstractJobContext<Job>
        implements JobContext<Job> {

    private final Long jobId;
    private final Long jobInstanceId;
    private final Job job;
    private final Properties properties;
    private final MeterRegistry meterRegistry;
    private final ActorSystem actorSystem;

    public AbstractJobContext(Long jobId, Long jobInstanceId, Job job, Properties properties, MeterRegistry meterRegistry, ActorSystem actorSystem) {
        this.jobId = jobId;
        this.jobInstanceId = jobInstanceId;
        this.job = job;
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
    public Job getJobInfo() {
        return job;
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
