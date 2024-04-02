package cn.sliew.http.workflow.api.instance;

import cn.sliew.http.workflow.api.execution.ExecutionStatus;
import cn.sliew.http.workflow.api.trigger.Trigger;

import java.util.Map;

public interface Instance {

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
