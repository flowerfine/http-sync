package cn.sliew.http.workflow.api.task;

import cn.sliew.http.workflow.api.DataOutput;
import cn.sliew.http.workflow.api.instance.TaskStatus;
import lombok.Data;

import java.util.Map;

@Data
public class TaskResult {

    private TaskStatus status;
    private Map<String, Object> context;

    private DataOutput output;
}
