package cn.sliew.http.workflow.temporal.controller;

import cn.sliew.http.workflow.temporal.workflow.hello.HelloWorkflow;
import cn.sliew.http.workflow.temporal.workflow.hello.Person;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @Autowired
    private WorkflowClient client;

    @PostMapping("/hello")
    ResponseEntity<String> helloSample(@RequestBody Person person) {
        HelloWorkflow workflow =
                client.newWorkflowStub(
                        HelloWorkflow.class,
                        WorkflowOptions.newBuilder()
                                .setTaskQueue("HelloSampleTaskQueue")
                                .setWorkflowId("HelloSample")
                                .build());

        // bypass thymeleaf, don't return template name just result
        return new ResponseEntity<>("\"" + workflow.sayHello(person) + "\"", HttpStatus.OK);
    }

}
