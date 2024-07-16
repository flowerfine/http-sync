package cn.sliew.http.workflow.temporal.workflow.hello;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface HelloWorkflow {

    String WORKFLOW_METHOD = "HelloWorkflow";

    /**
     * 默认是 HelloWorkflow，即接口名，不包含 package
     * 可以修改
     */
    @WorkflowMethod(name = WORKFLOW_METHOD)
    String sayHello(Person person);
}
