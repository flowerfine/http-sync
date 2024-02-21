package cn.sliew.http.stream.akka.framework;

import akka.actor.typed.ActorSystem;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.Properties;

public interface JobContext<Job, Authorization> {

    Long getJobId();

    Long getJobInstanceId();

    Job getJob();

    Authorization getAuthorization();

    Properties getProperties();

    MeterRegistry getMetrics();

    ActorSystem getActorSystem();
}
