package cn.sliew.http.workflow.kubevela;

import cn.sliew.http.workflow.kubevela.spec.WorkflowRunSpec;
import cn.sliew.http.workflow.kubevela.status.WorkflowRunStatus;
import lombok.Data;

@Data
public class WorkflowRun {

    private String apiVersion;
    private String kind;
    private Object metadata;

    private WorkflowRunSpec spec;
    private WorkflowRunStatus status;
}
