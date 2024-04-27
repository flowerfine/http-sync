package cn.sliew.http.workflow.kubevela.status;

import lombok.Data;

import java.util.List;

@Data
public class WorkflowStepStatus extends StepStatus {

    private List<StepStatus> subSteps;
}
