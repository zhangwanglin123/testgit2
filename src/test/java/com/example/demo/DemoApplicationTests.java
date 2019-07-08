package com.example.demo;

import com.example.demo.redis.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Resource(name = "redisTemplate")
    HashOperations<String,String,Object> hashOps;
    @Test
    public void contextLoads() {
    }
    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("aaa", "222");
        //Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
        System.out.print(stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testHash(){
        //具体调用
        Map<String,String> map = new HashMap<String, String>();
        map.put("value","code");
        map.put("key","keyValue");
        map.put("name","zhangwang");
        hashOps.putAll("hashOps",map);
        System.out.println(redisTemplate.opsForHash().entries("hashOps"));
    }

    @Test
    public void ListPush(){
        redisTemplate.opsForList().leftPush("li1", "a");
        redisTemplate.opsForList().leftPush("li1", "b");
        redisTemplate.opsForList().leftPush("li1", "c");
        List<Object> range1 = redisTemplate.opsForList().range("li1", 0, -1);
        for (Object object : range1) {
            System.out.println(object);//f e d c b a
        }



    }

    @Test
    public void testObj() throws Exception {
        User user=new User("aa@126.com", "aa", "aa123456", "aa","123");
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        operations.set("com.neox", user);
        operations.set("com.neo.f", user,1, TimeUnit.SECONDS);
        Thread.sleep(1000);
        //redisTemplate.delete("com.neo.f");

        //注入HashOperations对象

        boolean exists=redisTemplate.hasKey("com.neo.f");

        System.out.print("============"+redisTemplate.opsForValue().get("com.neox"));
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
        // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }


}
