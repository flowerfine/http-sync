package cn.sliew.http.workflow.api.instance;

import java.util.List;
import java.util.Map;

public interface StageInstance extends Instance {

    Map<String, Object> getOutputs();

    List getTasks();

    Object getTaskById(String taskId);

    String getRefId();

    String getParentStageId();

    StageInstance getParentStage();
}
