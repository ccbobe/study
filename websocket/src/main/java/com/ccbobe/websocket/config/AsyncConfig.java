package com.ccbobe.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean("threadPoolTaskExecutor")
    @Primary
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors()*2);
        taskExecutor.setMaxPoolSize(60);
        taskExecutor.setQueueCapacity(20000);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.setThreadGroupName("Task-");
        taskExecutor.setThreadNamePrefix("Async-");
        taskExecutor.setBeanName("threadPoolTaskExecutor");
        return taskExecutor;
    }


    @Bean("threadPool")
    public ThreadPoolTaskExecutor threadPool(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors()*2);
        taskExecutor.setMaxPoolSize(60);
        taskExecutor.setQueueCapacity(20000);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.setThreadGroupName("Task-");
        taskExecutor.setThreadNamePrefix("pools-");
        taskExecutor.setBeanName("threadPoolTaskExecutor");
        return taskExecutor;
    }
}
