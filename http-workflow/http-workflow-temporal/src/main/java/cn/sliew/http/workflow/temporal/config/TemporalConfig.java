package cn.sliew.http.workflow.temporal.config;

import io.temporal.client.WorkflowClientOptions;
import io.temporal.client.schedules.ScheduleClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.spring.boot.TemporalOptionsCustomizer;
import io.temporal.spring.boot.WorkerOptionsCustomizer;
import io.temporal.worker.WorkerFactoryOptions;
import io.temporal.worker.WorkerOptions;
import io.temporal.worker.WorkflowImplementationOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nonnull;

@Configuration
public class TemporalConfig {

    @Bean
    public TemporalOptionsCustomizer<WorkflowServiceStubsOptions.Builder> temporalWorkflowServiceStubsOptions() {
        return new TemporalOptionsCustomizer<>() {
            @Nonnull
            @Override
            public WorkflowServiceStubsOptions.Builder customize(
                    @Nonnull WorkflowServiceStubsOptions.Builder optionsBuilder) {
                // set options on optionsBuilder as needed
                // ...
                return optionsBuilder;
            }
        };
    }

    @Bean
    public TemporalOptionsCustomizer<WorkflowClientOptions.Builder> temporalWorkflowClientOptions() {
        return new TemporalOptionsCustomizer<>() {
            @Nonnull
            @Override
            public WorkflowClientOptions.Builder customize(
                    @Nonnull WorkflowClientOptions.Builder optionsBuilder) {
                // set options on optionsBuilder as needed
                // ...
                return optionsBuilder;
            }
        };
    }

    @Bean
    public TemporalOptionsCustomizer<ScheduleClientOptions.Builder> temporalScheduleClientOptions() {
        return new TemporalOptionsCustomizer<>() {
            @Nonnull
            @Override
            public ScheduleClientOptions.Builder customize(
                    @Nonnull ScheduleClientOptions.Builder optionsBuilder) {
                // set options on optionsBuilder as needed
                // ...
                return optionsBuilder;
            }
        };
    }

    @Bean
    public TemporalOptionsCustomizer<WorkerFactoryOptions.Builder> temporalWorkerFactoryOptions() {
        return new TemporalOptionsCustomizer<>() {
            @Nonnull
            @Override
            public WorkerFactoryOptions.Builder customize(
                    @Nonnull WorkerFactoryOptions.Builder optionsBuilder) {
                // set options on optionsBuilder as needed
                // ...
                return optionsBuilder;
            }
        };
    }

    @Bean
    public WorkerOptionsCustomizer temporalWorkerOptions() {
        return new WorkerOptionsCustomizer() {
            @Nonnull
            @Override
            public WorkerOptions.Builder customize(
                    @Nonnull WorkerOptions.Builder optionsBuilder,
                    @Nonnull String workerName,
                    @Nonnull String taskQueue) {

                // For CustomizeTaskQueue (also name of worker) we set worker
                // to only handle workflow tasks and local activities
                if (taskQueue.equals("CustomizeTaskQueue")) {
                    optionsBuilder.setLocalActivityWorkerOnly(true);
                }
                return optionsBuilder;
            }
        };
    }

    @Bean
    public TemporalOptionsCustomizer<WorkflowImplementationOptions.Builder> temporalWorkflowImplementationOptions() {
        return new TemporalOptionsCustomizer<>() {
            @Nonnull
            @Override
            public WorkflowImplementationOptions.Builder customize(
                    @Nonnull WorkflowImplementationOptions.Builder optionsBuilder) {
                // set options on optionsBuilder such as per-activity options
                return optionsBuilder;
            }
        };
    }
}
