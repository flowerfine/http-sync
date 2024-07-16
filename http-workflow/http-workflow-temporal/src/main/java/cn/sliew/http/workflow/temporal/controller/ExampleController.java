package cn.sliew.http.workflow.temporal.controller;

import cn.sliew.http.workflow.temporal.workflow.hello.HelloWorkflow;
import cn.sliew.http.workflow.temporal.workflow.hello.Person;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/temporal/example")
public class ExampleController {

    @Autowired
    private WorkflowClient client;

    @GetMapping("/hello")
    public ResponseEntity<String> helloSample(Person person) {
        HelloWorkflow workflow =
                client.newWorkflowStub(
                        HelloWorkflow.class,
                        WorkflowOptions.newBuilder()
                                .setTaskQueue("HelloSampleTaskQueue")
                                .setWorkflowId("HelloSample")
                                .setCronSchedule("@every 10s")
                                .build());

        // bypass thymeleaf, don't return template name just result
        return new ResponseEntity<>("\"" + workflow.sayHello(person) + "\"", HttpStatus.OK);
    }

    @GetMapping("/untyped-hello")
    public ResponseEntity<String> untypedHelloSample(Person person) {
        WorkflowStub workflowStub =
                client.newUntypedWorkflowStub(
                        HelloWorkflow.WORKFLOW_METHOD,
                        WorkflowOptions.newBuilder()
                                .setTaskQueue("HelloSampleTaskQueue")
                                .setWorkflowId("UntypedHelloSample5")
                                .build());

        WorkflowExecution execution = workflowStub.start(person);
        log.info("工作流启动! workflowId: {}, runId: {}", execution.getWorkflowId(), execution.getRunId());

        // bypass thymeleaf, don't return template name just result
        return new ResponseEntity<>("\"" + workflowStub.getResult(String.class) + "\"", HttpStatus.OK);
    }

}
