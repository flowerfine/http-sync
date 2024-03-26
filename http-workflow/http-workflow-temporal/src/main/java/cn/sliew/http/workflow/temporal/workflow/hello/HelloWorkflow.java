package cn.sliew.http.workflow.temporal.workflow.hello;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface HelloWorkflow {


    @WorkflowMethod
    String sayHello(Person person);
}
