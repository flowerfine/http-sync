package cn.sliew.http.workflow.api.entrypoint;

import cn.sliew.http.workflow.api.instance.WorkflowInstance;

public interface ExecutionRunner {

    void start(WorkflowInstance instance);

    default void restart(WorkflowInstance instance, String stageId) {
        throw new UnsupportedOperationException();
    }

    default void reschedule(WorkflowInstance instance) {
        throw new UnsupportedOperationException();
    }

    default void unpause(WorkflowInstance instance) {
        throw new UnsupportedOperationException();
    }

    default void cancel(WorkflowInstance instance, String reason) {
        throw new UnsupportedOperationException();
    }
}
