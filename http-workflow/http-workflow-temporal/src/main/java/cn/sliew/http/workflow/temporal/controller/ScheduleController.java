package cn.sliew.http.workflow.temporal.controller;

import cn.sliew.http.workflow.temporal.workflow.hello.HelloWorkflow;
import cn.sliew.http.workflow.temporal.workflow.hello.Person;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.schedules.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

@RestController
@RequestMapping("/temporal/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleClient client;

    @GetMapping("/start")
    public ResponseEntity<String> start(String schedulerId) {
        Person person = new Person();
        person.setFirstName("start");
        person.setLastName("schedule");

        Schedule schedule =
                Schedule.newBuilder()
                        .setAction(
                                ScheduleActionStartWorkflow.newBuilder()
                                        .setWorkflowType(HelloWorkflow.class)
                                        .setArguments(person)
                                        .setOptions(
                                                WorkflowOptions.newBuilder()
                                                        .setWorkflowId("HelloWorkflow")
                                                        .setTaskQueue("HelloSampleTaskQueue")
                                                        .build())
                                        .build())
                        .setSpec(
                                ScheduleSpec.newBuilder()
                                        .setIntervals(Arrays.asList(new ScheduleIntervalSpec(Duration.ofSeconds(10L))))
                                        .setStartAt(Instant.now())
                                        .setEndAt(Instant.now().plus(10, ChronoUnit.MINUTES))
                                        .build())
                        .build();

        client.createSchedule(schedulerId, schedule, ScheduleOptions.newBuilder().build());
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @GetMapping("/delete")
    public ResponseEntity<String> delete(String schedulerId) {
        ScheduleHandle handle = client.getHandle(schedulerId);
        handle.delete();
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/pause")
    public ResponseEntity<String> pause(String schedulerId) {
        ScheduleHandle handle = client.getHandle(schedulerId);
        handle.pause("暂停");
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/unpause")
    public ResponseEntity<String> unpause(String schedulerId) {
        ScheduleHandle handle = client.getHandle(schedulerId);
        handle.unpause("解除暂停");
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/trigger")
    public ResponseEntity<String> trigger(String schedulerId) {
        ScheduleHandle handle = client.getHandle(schedulerId);
        handle.trigger();
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<ScheduleDescription> getStatus(String schedulerId) {
        ScheduleHandle handle = client.getHandle(schedulerId);
        ScheduleDescription describe = handle.describe();
        return new ResponseEntity<>(describe, HttpStatus.OK);
    }

}
