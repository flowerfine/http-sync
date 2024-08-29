package cn.sliew.http.workflow.kubevela.spec;

import lombok.Data;

@Data
public class WorkflowExecuteMode {

    // workflow steps execution mode
    private WorkflowMode steps;
    // workflow subSteps execution mode
    private WorkflowMode subSteps;
}
