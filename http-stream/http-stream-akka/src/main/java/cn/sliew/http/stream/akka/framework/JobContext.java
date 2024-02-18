package cn.sliew.http.stream.akka.framework;

import akka.actor.typed.ActorSystem;
import cn.sliew.http.stream.dao.entity.job.JobAuthorization;
import cn.sliew.http.stream.dao.entity.job.JobInfo;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.Properties;

public interface JobContext<Root extends RootTask, Sub extends SubTask> {

    Long getJobId();

    Long getJobInstanceId();

    JobInfo getJob();

    JobAuthorization getAuthorization();

    Properties getProperties();

    MeterRegistry getMetrics();

    ActorSystem getActorSystem();
}
