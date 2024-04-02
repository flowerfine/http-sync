package cn.sliew.http.workflow.api;

public interface TaskResolver {

    Task getTask(String taskType);

    Task getTask(Class<? extends Task> taskType);
}
