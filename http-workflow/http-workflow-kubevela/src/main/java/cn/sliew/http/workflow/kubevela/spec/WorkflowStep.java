package cn.sliew.http.workflow.kubevela.spec;

import lombok.Data;

import java.util.List;

@Data
public class WorkflowStep extends WorkflowStepBase {

    private WorkflowMode mode;
    private List<WorkflowStepBase> subSteps;
}
