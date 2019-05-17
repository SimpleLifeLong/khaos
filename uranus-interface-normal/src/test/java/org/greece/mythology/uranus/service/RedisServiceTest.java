package org.greece.mythology.uranus.service;

import org.greece.mythology.uranus.UranusApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = UranusApplication.class)
@RunWith(SpringRunner.class)
public class RedisServiceTest {

    @Autowired
    RedisTemplate<String, String> redisTemplate;


    @Test
    public void test1() {
        redisTemplate.opsForValue().set("oo", "value");
    }

}
