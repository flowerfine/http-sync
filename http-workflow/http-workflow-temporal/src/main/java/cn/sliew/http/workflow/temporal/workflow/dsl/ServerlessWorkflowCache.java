package cn.sliew.http.workflow.temporal.workflow.dsl;

import io.serverlessworkflow.api.Workflow;

import java.util.HashMap;
import java.util.Map;

import static cn.sliew.http.workflow.temporal.workflow.dsl.util.DslWorkflowUtils.getFileAsString;

public class ServerlessWorkflowCache {

    private static class WorkflowHolder {
        static final Map<String, Workflow> dslWorkflowMap = new HashMap<>();

        static {
            try {
                Workflow customerApplicationWorkflow =
                        Workflow.fromSource(getFileAsString("dsl/customerapplication/workflow.yml"));
                Workflow bankingTransactionsWorkflow =
                        Workflow.fromSource(getFileAsString("dsl/bankingtransactions/workflow.yml"));
                Workflow applicantWorkflow =
                        Workflow.fromSource(getFileAsString("dsl/customerapproval/applicantworkflow.json"));
                Workflow approvalWorkflow =
                        Workflow.fromSource(getFileAsString("dsl/customerapproval/approvalworkflow.json"));
                Workflow bankingParentWorkflow =
                        Workflow.fromSource(
                                getFileAsString("dsl/bankingtransactionssubflow/parentworkflow.json"));
                Workflow bankingChildWorkflow =
                        Workflow.fromSource(
                                getFileAsString("dsl/bankingtransactionssubflow/childworkflow.json"));

                dslWorkflowMap.put(
                        customerApplicationWorkflow.getId() + "-" + customerApplicationWorkflow.getVersion(),
                        customerApplicationWorkflow);
                dslWorkflowMap.put(
                        bankingTransactionsWorkflow.getId() + "-" + bankingTransactionsWorkflow.getVersion(),
                        bankingTransactionsWorkflow);
                dslWorkflowMap.put(
                        applicantWorkflow.getId() + "-" + applicantWorkflow.getVersion(), applicantWorkflow);
                dslWorkflowMap.put(
                        approvalWorkflow.getId() + "-" + approvalWorkflow.getVersion(), approvalWorkflow);
                dslWorkflowMap.put(
                        bankingParentWorkflow.getId() + "-" + bankingParentWorkflow.getVersion(),
                        bankingParentWorkflow);
                dslWorkflowMap.put(
                        bankingChildWorkflow.getId() + "-" + bankingChildWorkflow.getVersion(),
                        bankingChildWorkflow);
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }
    }

    public static Workflow getWorkflow(String workflowId, String workflowVersion) {
        return WorkflowHolder.dslWorkflowMap.get(workflowId + "-" + workflowVersion);
    }
}
