package com.zyc.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by YuChen Zhang on 17/09/13.
 */
public class JedisPoolUtil {

    private static JedisPool pool;
    /**
     * 最大空闲连接
     */
    private static Integer maxIdle = 10;
    /**
     * 最大阻塞时间
     */
    private static Integer maxWaitMillis = 10000;
    /**
     * 最大连接数
     */
    private static Integer setMaxTotal = 100;
    /**
     * 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
     */
    private Integer mnEvictableIdleTimeMillis;
    /**
     * 建立连接池 真实环境，一般把配置参数缺抽取出来。
     *
     */
    static{
        createJedisPool();
    }
    private static void createJedisPool() {

        // 建立连接池配置参数
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxIdle(JedisPoolUtil.maxIdle);

        // 设置最大阻塞时间，记住是毫秒数milliseconds
        config.setMaxWaitMillis(JedisPoolUtil.maxWaitMillis);

        // 设置空闲连接
        config.setMaxTotal(JedisPoolUtil.maxIdle);

        // 创建连接池

        pool = new JedisPool(config, "123.206.8.180", 6379);


    }

    /**
     * 在多线程环境同步初始化
     */
    private static synchronized void poolInit() {
        if (pool == null)
            createJedisPool();
    }

    /**
     * 获取一个jedis 对象
     *
     * @return
     */
    public static Jedis getJedis() {
        if (pool == null)
            poolInit();
        Jedis jedis = pool.getResource();
        jedis.auth("199616");
        return jedis;
    }

    /**
     * 归还一个连接
     *
     * @param jedis
     */
    public static void returnRes(Jedis jedis) {
        if(jedis.isConnected()) {
            jedis.close();
        }
    }
}