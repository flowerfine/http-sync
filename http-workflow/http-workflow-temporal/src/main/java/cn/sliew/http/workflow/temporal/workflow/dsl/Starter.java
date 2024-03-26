package cn.sliew.http.workflow.temporal.workflow.dsl;

import com.fasterxml.jackson.databind.JsonNode;
import io.serverlessworkflow.api.Workflow;
import io.serverlessworkflow.api.interfaces.WorkflowValidator;
import io.serverlessworkflow.api.validation.ValidationError;
import io.serverlessworkflow.validation.WorkflowValidatorImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.WorkerFactory;

import java.util.List;

import static cn.sliew.http.workflow.temporal.workflow.dsl.util.DslWorkflowUtils.*;

public class Starter {

    public static final WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
    public static final WorkflowClient client = WorkflowClient.newInstance(service);
    public static final WorkerFactory factory = WorkerFactory.newInstance(client);

    public static void main(String[] args) {

        // Customer application workflow
        runCustomerApplicationWorkflow();
        // Banking transaction workflow
        runBankingTransactionWorkflow();
        // Customer approval workflow
        runCustomerApprovalWorkflow();
        // Banking transaction workflow with parent/child relationship
        runBankingTransactionWithChildWorkflow();

        System.exit(0);
    }

    private static void runCustomerApplicationWorkflow() {
        runWorkflow("customerapplication", "1.0", "dsl/customerapplication/datainput.json", true);
    }

    private static void runBankingTransactionWorkflow() {
        runWorkflow("bankingtransactions", "1.0", "dsl/bankingtransactions/datainput.json", true);
    }

    private static void runBankingTransactionWithChildWorkflow() {
        runWorkflow(
                "bankingparentworkflow", "1.0", "dsl/bankingtransactionssubflow/datainput.json", false);
    }

    private static void runWorkflow(
            String workflowId, String workflowVersion, String dataInputFileName, boolean doQuery) {
        try {
            // Get the workflow dsl from cache
            Workflow dslWorkflow = ServerlessWorkflowCache.getWorkflow(workflowId, workflowVersion);

            assertValid(dslWorkflow);

            WorkflowOptions workflowOptions = getWorkflowOptions(dslWorkflow);

            WorkflowStub workflowStub =
                    client.newUntypedWorkflowStub(dslWorkflow.getName(), workflowOptions);

            System.out.println(
                    "Starting workflow with id: " + workflowId + " and version: " + workflowVersion);
            // Start workflow execution
            startWorkflow(workflowStub, dslWorkflow, getSampleWorkflowInput(dataInputFileName));

            // Wait for workflow to finish
            JsonNode result = workflowStub.getResult(JsonNode.class);

            if (doQuery) {
                // Query the customer name and age
                String customerName = workflowStub.query("QueryCustomerName", String.class);
                int customerAge = workflowStub.query("QueryCustomerAge", Integer.class);

                System.out.println("Query result for customer name: " + customerName);
                System.out.println("Query result for customer age: " + customerAge);
            }

            // Print workflow results
            System.out.println("Workflow results: \n" + result.toPrettyString());

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void runCustomerApprovalWorkflow() {
        try {
            Workflow applicantWorkflow = ServerlessWorkflowCache.getWorkflow("applicantworkflow", "1.0");
            Workflow approvalWorkflow = ServerlessWorkflowCache.getWorkflow("approvalworkflow", "1.0");

            assertValid(applicantWorkflow);
            assertValid(approvalWorkflow);

            // start approval workflow first
            WorkflowStub approvalWorkflowStub =
                    client.newUntypedWorkflowStub(
                            approvalWorkflow.getName(), getWorkflowOptions(approvalWorkflow));
            System.out.println(
                    "Starting workflow with id: "
                            + approvalWorkflow.getId()
                            + " and version: "
                            + applicantWorkflow.getVersion());
            startWorkflow(
                    approvalWorkflowStub,
                    approvalWorkflow,
                    getSampleWorkflowInput("dsl/customerapproval/approvaldatainput.json"));

            // start applicant workflow second
            WorkflowStub applicantWorkflowStub =
                    client.newUntypedWorkflowStub(
                            applicantWorkflow.getName(), getWorkflowOptions(applicantWorkflow));
            System.out.println(
                    "Starting workflow with id: "
                            + applicantWorkflow.getId()
                            + " and version: "
                            + applicantWorkflow.getVersion());
            startWorkflow(
                    applicantWorkflowStub,
                    applicantWorkflow,
                    getSampleWorkflowInput("dsl/customerapproval/applicantdatainput.json"));

            // Wait for workflow to finish
            JsonNode result = applicantWorkflowStub.getResult(JsonNode.class);
            // Print workflow results
            System.out.println("Workflow results: \n" + result.toPrettyString());

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void assertValid(Workflow dslWorkflow) {
        // Validate dsl
        System.out.println("Validating workflow: " + dslWorkflow.getId());
        WorkflowValidator dslWorkflowValidator = new WorkflowValidatorImpl();
        if (!dslWorkflowValidator.setWorkflow(dslWorkflow).isValid()) {
            System.err.println(
                    "Workflow DSL not valid. Consult github.com/serverlessworkflow/specification/blob/main/specification.md for more info");
            List<ValidationError> validationErrorList =
                    dslWorkflowValidator.setWorkflow(dslWorkflow).validate();
            for (ValidationError error : validationErrorList) {
                System.out.println("Error: " + error.getMessage());
            }
            System.exit(1);
        }
    }
}
