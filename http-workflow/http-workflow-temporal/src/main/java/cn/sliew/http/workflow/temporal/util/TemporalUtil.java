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

    private static Map<String, WorkflowServiceStubs> workflowServiceStubsCache = new HashMap<>();

    private TemporalUtil() {
        throw new IllegalStateException("no instance");
    }

    public static WorkflowClient createWorkflowClient(final String temporalHost) {
        return createWorkflowClient(temporalHost, "default");
    }

    public static WorkflowClient createWorkflowClient(final String temporalHost, final String namespace) {
        WorkflowServiceStubs service = TemporalUtil.getTemporalService(temporalHost, namespace);
        WorkflowClientOptions workflowClientOptions = WorkflowClientOptions.newBuilder()
                .setNamespace(namespace)
                .validateAndBuildWithDefaults();
        return WorkflowClient.newInstance(service, workflowClientOptions);
    }

    public static ScheduleClient createScheduleClient(final String temporalHost, final String namespace) {
        WorkflowServiceStubs service = TemporalUtil.getTemporalService(temporalHost, namespace);
        ScheduleClientOptions scheduleClientOptions = ScheduleClientOptions.newBuilder()
                .setNamespace(namespace)
                .build();
        return ScheduleClient.newInstance(service, scheduleClientOptions);
    }

    public static WorkflowServiceStubs getTemporalService(final String temporalHost, final String namespace) {
        return workflowServiceStubsCache.computeIfAbsent(temporalHost, key -> createTemporalService(key, namespace));
    }

    public static WorkflowServiceStubs createTemporalService(final String temporalHost, final String namespace) {
        final WorkflowServiceStubsOptions options = WorkflowServiceStubsOptions.newBuilder()
                .setTarget(temporalHost)
                .setEnableKeepAlive(true)
                .setKeepAliveTime(Duration.ofMinutes(1L))
                .setKeepAliveTimeout(Duration.ofMinutes(5L))
                .build();

        return getTemporalClientWhenConnected(
                namespace,
                Duration.ofSeconds(2),
                Duration.ofMinutes(2),
                Duration.ofSeconds(10),
                () -> WorkflowServiceStubs.newInstance(options));
    }

    /**
     * Loops and waits for the Temporal service to become available and returns a client.
     * <p>
     * This function uses a supplier as input since the creation of a WorkflowServiceStubs can result in
     * connection exceptions as well.
     */
    public static WorkflowServiceStubs getTemporalClientWhenConnected(
            final String namespace,
            final Duration waitInterval,
            final Duration maxTimeToConnect,
            final Duration waitAfterConnection,
            final Supplier<WorkflowServiceStubs> temporalServiceSupplier) {
        log.info("Waiting for temporal server...");

        boolean temporalStatus = false;
        WorkflowServiceStubs temporalService = null;
        long millisWaited = 0;

        while (!temporalStatus) {
            if (millisWaited >= maxTimeToConnect.toMillis()) {
                throw new RuntimeException("Could not create Temporal client within max timeout!");
            }

            log.warn("Waiting for namespace {} to be initialized in temporal...", namespace);
            Rethrower.toRuntime(() -> Thread.sleep(waitInterval.toMillis()));
            millisWaited = millisWaited + waitInterval.toMillis();

            try {
                temporalService = temporalServiceSupplier.get();
                temporalStatus = getNamespaces(temporalService).contains(namespace);
            } catch (final Exception e) {
                // Ignore the exception because this likely means that the Temporal service is still initializing.
                log.warn("Ignoring exception while trying to request Temporal namespaces:", e);
            }
        }

        // sometimes it takes a few additional seconds for workflow queue listening to be available
        Rethrower.toRuntime(() -> Thread.sleep(waitAfterConnection.toMillis()));

        log.info("Found temporal namespace {}!", namespace);

        return temporalService;
    }

    protected static Set<String> getNamespaces(final WorkflowServiceStubs temporalService) {
        return temporalService.blockingStub()
                .listNamespaces(ListNamespacesRequest.newBuilder().build())
                .getNamespacesList()
                .stream()
                .map(DescribeNamespaceResponse::getNamespaceInfo)
                .map(NamespaceInfo::getName)
                .collect(toSet());
    }

    public static void logWorkflow(Logger log, WorkflowInfo workflowInfo) {
        log.debug("执行工作流! namespace: {}, workflowType: {}, taskQueue: {}, workflowId: {}, runId: {}",
                workflowInfo.getNamespace(), workflowInfo.getWorkflowType(), workflowInfo.getTaskQueue(),
                workflowInfo.getWorkflowId(), workflowInfo.getRunId());
    }

    public static void logActivityContext(Logger log, ActivityExecutionContext context) {
        ActivityInfo info = context.getInfo();
        log.debug("执行 activity! workflowNamespace: {}, workflowType: {}," +
                        " workflowId: {}, runId: {}, activityId: {}, " +
                        "activityNamespace: {}, activityType: {}",
                info.getWorkflowNamespace(),
                info.getWorkflowType(),
                info.getWorkflowId(),
                info.getRunId(),
                info.getActivityId(),
                info.getActivityNamespace(),
                info.getActivityType()
        );
    }
}
