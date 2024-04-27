package cn.sliew.http.workflow.kubevela.status;

import cn.sliew.http.workflow.kubevela.spec.WorkflowExecuteMode;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class WorkflowRunStatus {

    private WorkflowExecuteMode mode;
    private WorkflowRunPhase status;
    private String message;
    private boolean suspend;
    private String suspendState;
    private boolean terminated;
    private boolean finished;
    private List<WorkflowStepStatus> steps;

    private Date startTime;
    private Date endTime;

    public enum WorkflowRunPhase {
        Initializing,
        Executing,
        Failed,
        Succeeded,
        Suspending,
        Terminated,
        Skipped
    }
}
