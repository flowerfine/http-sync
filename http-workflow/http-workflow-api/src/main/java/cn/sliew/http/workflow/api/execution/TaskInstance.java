package cn.sliew.http.workflow.api.execution;

import cn.sliew.http.workflow.api.trigger.Trigger;

import java.util.Map;

public interface TaskInstance {

    String getId();

    String getName();

    String getDescription();

    Trigger getTrigger();

    Map<String, Object> getContext();

    Long getStartTime();

    Long getEndTime();

    ExecutionStatus getStatus();

    boolean isTerminated();
}
