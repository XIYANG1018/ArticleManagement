package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest  // 在测试类上添加这个注解后，在单元测试方法执行之前会先初始化spring容器，想获取什么对象只需要在这个容器中注入即可
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void testSet() {
        // 往redis中存储一个键值对 调用StringRedisTemplate
        ValueOperations<String,String> operations = stringRedisTemplate.opsForValue();

        operations.set("username", "zhangsan");
        operations.set("id", "1", 15, TimeUnit.SECONDS); // 有过期的时间

    }

    @Test
    public void testGet() {
        // 从redis中获取一个键值对
        ValueOperations<String,String> operations = stringRedisTemplate.opsForValue();

       System.out.println(operations.get("username"));
        System.out.println(operations.get("id"));  // 会找不到，因为redis存储的键值对已经过期了
    }
}
