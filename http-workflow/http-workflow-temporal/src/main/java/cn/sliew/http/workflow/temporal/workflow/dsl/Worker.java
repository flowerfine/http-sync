package cn.sliew.http.workflow.temporal.workflow.dsl;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.WorkerFactory;

public class Worker {
    private static final WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
    private static final WorkflowClient client = WorkflowClient.newInstance(service);
    private static final WorkerFactory factory = WorkerFactory.newInstance(client);
    public static final String DEFAULT_TASK_QUEUE_NAME = "dsltaskqueue";

    public static void main(String[] args) {
        io.temporal.worker.Worker worker = factory.newWorker(DEFAULT_TASK_QUEUE_NAME);
        worker.registerWorkflowImplementationTypes(DynamicDslWorkflow.class);
        worker.registerActivitiesImplementations(new DslActivitiesImpl());

        factory.start();
    }
}
