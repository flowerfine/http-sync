package cn.sliew.http.workflow.api;

import cn.sliew.http.workflow.api.execution.Execution;
import org.pf4j.ExtensionPoint;

public interface Task extends ExtensionPoint {

    TaskResult execute(Execution execution);

    TaskResult onTimeout(Execution execution);

    TaskResult onTerminate(Execution execution);
}
