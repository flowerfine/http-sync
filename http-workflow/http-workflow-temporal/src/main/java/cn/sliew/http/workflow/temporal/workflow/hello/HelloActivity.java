package cn.sliew.http.workflow.temporal.workflow.hello;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface HelloActivity {

    String hello(Person person);
}