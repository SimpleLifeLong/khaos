package org.greece.mythology.uranus.service;

import org.greece.mythology.uranus.UranusApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = UranusApplication.class)
@RunWith(SpringRunner.class)
public class RedisServiceTest {

    @Autowired
    RedisTemplate<String, String> redisTemplate;


    @Test
    public void test1() {

        HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        hashOperations.put("123", "456", "789");
//        Set<String> keys = redisTemplate.keys("");
//        redisTemplate.delete(keys.iterator().next());

    }

}
