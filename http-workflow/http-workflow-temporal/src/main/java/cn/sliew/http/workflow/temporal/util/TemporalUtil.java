package cn.sliew.http.workflow.temporal.util;

import cn.sliew.milky.common.exception.Rethrower;
import io.temporal.activity.ActivityExecutionContext;
import io.temporal.activity.ActivityInfo;
import io.temporal.api.namespace.v1.NamespaceInfo;
import io.temporal.api.workflowservice.v1.DescribeNamespaceResponse;
import io.temporal.api.workflowservice.v1.ListNamespacesRequest;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.client.schedules.ScheduleClient;
import io.temporal.client.schedules.ScheduleClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.workflow.WorkflowInfo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toSet;

@Slf4j
public class TemporalUtil {

    public static WorkflowClient createWorkflowClient(final String temporalHost, final String namespace) {
        WorkflowServiceStubsOptions options = WorkflowServiceStubsOptions.newBuilder()
                .setTarget(temporalHost)
                .setEnableKeepAlive(true)
                .setKeepAliveTime(Duration.ofMinutes(1L))
                .setKeepAliveTimeout(Duration.ofMinutes(5L))
                .build();
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance(options);

        WorkflowClientOptions workflowClientOptions = WorkflowClientOptions.newBuilder()
                .setNamespace(namespace)
                .validateAndBuildWithDefaults();
        return WorkflowClient.newInstance(service, workflowClientOptions);
    }

    public static ScheduleClient createScheduleClient(final String temporalHost, final String namespace) {
        WorkflowServiceStubsOptions options = WorkflowServiceStubsOptions.newBuilder()
                .setTarget(temporalHost)
                .setEnableKeepAlive(true)
                .setKeepAliveTime(Duration.ofMinutes(1L))
                .setKeepAliveTimeout(Duration.ofMinutes(5L))
                .build();
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance(options);

        ScheduleClientOptions scheduleClientOptions = ScheduleClientOptions.newBuilder()
                .setNamespace(namespace)
                .build();
        return ScheduleClient.newInstance(service, scheduleClientOptions);
    }
}
