package cn.sliew.http.workflow.api;

import java.time.Duration;

public interface RetryableTask extends Task {

    Duration getTimeout();

    Duration getBackoffPeriod();
}
