package org.greece.mythology.uranus.config;

import lombok.Getter;
import lombok.Setter;
import org.greece.mythology.tartarus.commons.SchedulerExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author suhaohao
 * @Date 2019/6/13
 */
@Configuration
public class CommonsConfiguration {

    @Autowired
    private ExecutorTaskProperty taskProperty;
    @Autowired
    private ExecutorSchedulerProperty schedulerProperty;
    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public RedisLockRegistry redisLockRegistry() {
        RedisLockRegistry registry = new RedisLockRegistry(
                redisTemplate.getConnectionFactory(),
                schedulerProperty.registryKey,
                schedulerProperty.expireAfter);
        return registry;
    }

    @Bean
    public SchedulerExecutor schedulerExecutor() {
        SchedulerExecutor schedulerExecutor = new SchedulerExecutor(redisLockRegistry());
        return schedulerExecutor;
    }


    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setMaxPoolSize(taskProperty.maxPoolSize);
        taskExecutor.setCorePoolSize(taskProperty.corePoolSize);
        taskExecutor.setQueueCapacity(taskProperty.queueCapacity);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return taskExecutor;
    }

    @Configuration
    @ConfigurationProperties(prefix = "executor.task")
    @Setter
    @Getter
    public class ExecutorTaskProperty {
        private int corePoolSize;
        private int maxPoolSize;
        private int queueCapacity;
    }

    @Configuration
    @ConfigurationProperties(prefix = "executor.scheduler")
    @Setter
    @Getter
    public class ExecutorSchedulerProperty {
        private int poolSize;
        private long expireAfter;
        private String registryKey;
    }

}
