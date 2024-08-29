package cn.sliew.http.workflow.api.task;

public interface TaskResolver {

    Task getTask(String taskType);

    Task getTask(Class<? extends Task> taskType);
}
