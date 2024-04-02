package cn.sliew.http.workflow.api;

import cn.sliew.http.workflow.api.execution.ExecutionStatus;
import lombok.Data;

import java.util.Map;

@Data
public class TaskResult {

    private ExecutionStatus status;
    private Map<String, Object> context;

    private DataOutput output;
}
