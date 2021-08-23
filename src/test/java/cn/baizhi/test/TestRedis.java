package cn.baizhi.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

public class TestRedis {

    @Autowired
     private RedisTemplate redisTemplate;

    @Autowired
     private StringRedisTemplate stringRedisTemplate;
}
