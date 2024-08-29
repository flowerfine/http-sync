package cn.sliew.http.workflow.api.task;

import org.pf4j.ExtensionPoint;

public interface TaskInterceptor extends ExtensionPoint {

    void beforeExecute(Task task);

    void afterExecute(Task task);

    void finallyAfterExecute(Task task, TaskResult taskResult, Exception e);
}
