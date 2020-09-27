package com.instrumentation;

import com.instrumentation.engine.InstrumentationEngine;
import com.instrumentation.engine.InstrumentationEngineImpl;
import com.instrumentation.monitoring.MonitoringController;
import com.instrumentation.monitoring.MonitoringService;
import com.instrumentation.monitoring.MonitoringServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    @ConditionalOnMissingBean
    public InstrumentationEngine engine() {
        return new InstrumentationEngineImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public MonitoringController monitoringController() {
        return new MonitoringController();
    }


    @Bean
    @ConditionalOnMissingBean
    public MonitoringService monitoringService() {
        return new MonitoringServiceImpl(engine());
    }
}
