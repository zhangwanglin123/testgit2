package com.example.demo.redis.redisconfig;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Method;

public class RedisConfig extends CachingConfigurerSupport {
@Bean
        public KeyGenerator keyGenerator() {
            return new KeyGenerator() {
                @Override
                public Object generate(Object target, Method method, Object... params) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(target.getClass().getName());
                    sb.append(method.getName());
                    for (Object obj : params) {
                        sb.append(obj.toString());
                    }
                    return sb.toString();
                }
            };
        }
}
