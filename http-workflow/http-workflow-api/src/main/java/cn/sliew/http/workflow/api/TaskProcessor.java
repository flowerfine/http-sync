package cn.sliew.http.workflow.api;

import org.pf4j.ExtensionPoint;

public interface TaskProcessor extends ExtensionPoint {

    boolean support(Task task);

    void process(Task task);
}
