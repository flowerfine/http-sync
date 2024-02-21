package cn.sliew.http.stream.akka.framework;

import akka.actor.typed.ActorSystem;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.Properties;

public interface JobContext<Job> {

    Long getJobId();

    Long getJobInstanceId();

    String getJobName();

    Job getJobInfo();

    Properties getProperties();

    MeterRegistry getMetrics();

    ActorSystem getActorSystem();
}
