package cn.sliew.http.workflow.kubevela.spec;

import lombok.Data;

import java.util.List;

@Data
public class WorkflowSpec {

    private List<WorkflowStep> steps;
}
