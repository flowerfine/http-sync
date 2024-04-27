package cn.sliew.http.workflow.kubevela;

import cn.sliew.http.workflow.kubevela.spec.WorkflowExecuteMode;
import cn.sliew.http.workflow.kubevela.spec.WorkflowSpec;
import lombok.Data;

@Data
public class Workflow {

    private String apiVersion;
    private String kind;
    private Object metadata;
    private WorkflowSpec spec;
    private WorkflowExecuteMode mode;
}
