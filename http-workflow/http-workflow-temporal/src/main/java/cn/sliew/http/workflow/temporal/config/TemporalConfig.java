package cn.sliew.http.workflow.temporal.config;

import io.temporal.spring.boot.autoconfigure.properties.TemporalProperties;
import io.temporal.spring.boot.autoconfigure.properties.WorkersAutoDiscoveryProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

public class TemporalConfig {

    static final String NAMESPACE_DATA_CENTER_DEV_NAME = "cn.sliew.http.workflow.temporal.config.data-center-dev";
    static final String NAMESPACE_DATA_LINK_NAME = "cn.sliew.http.workflow.temporal.config.data-link";

    @Bean(NAMESPACE_DATA_CENTER_DEV_NAME)
    @ConfigurationProperties(prefix = "spring.temporal.data-center-dev")
    public TemporalProperties dataCenterDevNamespace() {
        return new TemporalProperties("data-center-dev", new WorkersAutoDiscoveryProperties(Arrays.asList("cn.sliew.http.workflow.temporal.workflow")), null, null, null, null, null);
    }

    @Bean(NAMESPACE_DATA_LINK_NAME)
    @ConfigurationProperties(prefix = "spring.temporal.data-link")
    public TemporalProperties datalinkNamespace() {
        return new TemporalProperties("data-link", new WorkersAutoDiscoveryProperties(Arrays.asList("cn.sliew.http.workflow.temporal.controller")), null, null, null, null, null);
    }
}
