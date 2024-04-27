package cn.sliew.http.workflow.api;

import cn.sliew.http.workflow.api.instance.Instance;
import org.pf4j.ExtensionPoint;

public interface Task extends ExtensionPoint {

    TaskResult execute(Instance execution);

    TaskResult onTimeout(Instance execution);

    TaskResult onTerminate(Instance execution);
}
