package com.zyc.jedis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.bind.annotation.ExceptionHandler;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by YuChen Zhang on 17/11/21.
 */
@Aspect
public class JedisAop {
    private static final Logger logger = LogManager.getLogger(JedisAop.class);

    JedisDataSource jedisDataSource;

    @Before("execution(* com.zyc.jedis.JedisPoolUtil1.* (..))")
    public void before(JoinPoint joinPoint) throws NoSuchFieldException, IllegalAccessException {
        Object bean = joinPoint.getTarget();
        Field field = bean.getClass().getDeclaredField("jedis");
        field.setAccessible(true);
        field.set(bean,jedisDataSource.getJedis());
    }
    @AfterThrowing("execution(* com.zyc.jedis.JedisPoolUtil1.* (..))&&args(e)")
    public void afterThrowing(JoinPoint joinPoint,Exception e) throws NoSuchFieldException, IllegalAccessException {
        Object bean = joinPoint.getTarget();
        Field field = bean.getClass().getDeclaredField("jedis");
        field.setAccessible(true);
        jedisDataSource.returnRes((Jedis) field.get(bean));
    }
    @After("execution(* com.zyc.jedis.JedisPoolUtil1.* (..))")
    public void after(JoinPoint joinPoint) throws NoSuchFieldException, IllegalAccessException {
        Object bean = joinPoint.getTarget();
        Field field = bean.getClass().getDeclaredField("jedis");
        field.setAccessible(true);
        jedisDataSource.returnRes((Jedis) field.get(bean));
    }

    public JedisDataSource getJedisDataSource() {
        return jedisDataSource;
    }

    public void setJedisDataSource(JedisDataSource jedisDataSource) {
        this.jedisDataSource = jedisDataSource;
    }
}
