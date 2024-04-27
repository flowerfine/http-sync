package cn.sliew.http.workflow.kubevela.spec;

import lombok.Data;

import java.util.List;
import java.util.Properties;

@Data
public class WorkflowStepBase extends WorkflowStepMeta {

    private String name;
    private String type;
    private Object meta;
    private String ifCondition;
    private String timeout;
    private List<InputItem> inputs;
    private List<OutputItem> outputs;
    private Properties properties;
}
