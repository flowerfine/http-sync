package cn.sliew.http.workflow.api;

import lombok.Data;

@Data
public class ExecutionContext {

    private final String executionId;
    private final String stageId;
    private final Long stageStartTime;
}
