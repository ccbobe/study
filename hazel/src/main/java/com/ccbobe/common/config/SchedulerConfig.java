package com.ccbobe.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@EnableScheduling
@Configuration
public class SchedulerConfig implements SchedulingConfigurer {

    @Bean
    public ScheduledExecutorService concurrentTaskScheduler(){
        ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(20);
        executorService.setMaximumPoolSize(20);
        executorService.setRejectedExecutionHandler(new ScheduledThreadPoolExecutor.CallerRunsPolicy());
        return executorService;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(concurrentTaskScheduler());
    }
}
