package org.greece.mythology.uranus.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

/**
 *
 */
@Configuration
public class CommonsConfiguration {

    @Autowired
    private ExecutorProperty executorProperty;

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(executorProperty.maxPoolSize);
        taskExecutor.setCorePoolSize(executorProperty.corePoreSize);
        taskExecutor.setQueueCapacity(executorProperty.queueCapacity);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return taskExecutor;
    }

    @Component
    @ConfigurationProperties(prefix = "executor.task")
    @Setter
    @Getter
    public class ExecutorProperty {
        private int corePoreSize;
        private int maxPoolSize;
        private int queueCapacity;
    }

}
