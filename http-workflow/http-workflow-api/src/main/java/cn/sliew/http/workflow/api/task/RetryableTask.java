package cn.sliew.http.workflow.api.task;

import cn.sliew.http.workflow.api.task.Task;

import java.time.Duration;

public interface RetryableTask extends Task {

    Duration getTimeout();

    Duration getBackoffPeriod();
}
