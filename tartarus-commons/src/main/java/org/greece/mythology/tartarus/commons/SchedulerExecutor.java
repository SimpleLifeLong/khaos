package org.greece.mythology.tartarus.commons;


import org.springframework.integration.redis.util.RedisLockRegistry;

import java.util.concurrent.locks.Lock;
import java.util.function.Consumer;

/**
 * @author suhaohao
 * @Date 2019/6/13
 * @see RedisLockRegistry
 */
public class SchedulerExecutor {

    private final RedisLockRegistry lockRegistry;

    public SchedulerExecutor(RedisLockRegistry lockRegistry) {
        this.lockRegistry = lockRegistry;
    }

    public void execute(String lockKey, Consumer<Object> consumer) {
        Lock lock = lockRegistry.obtain(lockKey);
        if (lock != null && lock.tryLock()) {
            try {
                lock.unlock();
            } catch (Exception e) {
            }
            consumer.accept(null);
        }
    }
}
