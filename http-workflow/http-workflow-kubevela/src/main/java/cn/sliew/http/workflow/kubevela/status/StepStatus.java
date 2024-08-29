package cn.sliew.http.workflow.kubevela.status;

import lombok.Data;

import java.util.Date;

@Data
public class StepStatus {

    private String id;
    private String name;
    private String type;
    private WorkflowStepPhase phase;
    private String message;
    private String reason;
    private Date firstExecuteTime;
    private Date lastExecuteTime;

    public enum WorkflowStepPhase {
        Succeeded,
        Failed,
        Skipped,
        Running,
        Pending,
        Suspending
    }
}
