package cn.sliew.http.stream.akka.framework2;

import akka.actor.typed.ActorSystem;
import cn.sliew.http.stream.akka.framework.RootTask;
import cn.sliew.http.stream.akka.framework.SubTask;
import cn.sliew.http.stream.dao.entity.job.JobAuthorization;
import cn.sliew.http.stream.dao.entity.job.JobInfo;
import cn.sliew.http.stream.dao.entity.job.JobSyncOffset;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.Properties;

public interface JobContext2<Root extends RootTask, Sub extends SubTask> {

    JobInfo getJob();

    JobAuthorization getAuthorization();

    JobSyncOffset getSyncOffset();

    Properties getProperties();

    MeterRegistry getMetrics();

    ActorSystem getActorSystem();
}
