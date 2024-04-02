package cn.sliew.http.workflow.api.execution;

import java.util.List;
import java.util.Map;

public interface StageExecution extends TaskInstance {

    Map<String, Object> getOutputs();

    List getTasks();

    Object getTaskById(String taskId);

    String getRefId();

    String getParentStageId();

    StageExecution getParentStage();
}
