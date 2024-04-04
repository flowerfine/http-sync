package cn.sliew.http.workflow.api.entrypoint;

import cn.sliew.http.workflow.api.instance.WorkflowInstance;
import cn.sliew.http.workflow.api.queue.Queue;

public class QueueExecutionRunner implements ExecutionRunner {

    private Queue queue;

    @Override
    public void start(WorkflowInstance instance) {
        queue.push(null);
    }

    @Override
    public void restart(WorkflowInstance instance, String stageId) {
        queue.push(null);
    }

    @Override
    public void reschedule(WorkflowInstance instance) {
        queue.push(null);
    }

    @Override
    public void unpause(WorkflowInstance instance) {
        queue.push(null);
    }

    @Override
    public void cancel(WorkflowInstance instance, String reason) {
        queue.push(null);
    }
}
