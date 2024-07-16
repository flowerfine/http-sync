package cn.sliew.http.workflow.temporal.controller;

import cn.sliew.http.workflow.temporal.workflow.hello.HelloWorkflow;
import cn.sliew.http.workflow.temporal.workflow.hello.Person;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                                .build());

        // bypass thymeleaf, don't return template name just result
        return new ResponseEntity<>("\"" + workflow.sayHello(person) + "\"", HttpStatus.OK);
    }

}
