package cn.sliew.http.workflow.kubevela.spec;

import lombok.Data;

import java.util.Properties;

@Data
public class WorkflowRunSpec {

    private Properties context;
    private WorkflowExecuteMode mode;
    private WorkflowSpec workflowSpec;
    private String workflowRef;
}
