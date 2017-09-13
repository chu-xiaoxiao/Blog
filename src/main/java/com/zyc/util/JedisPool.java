package com.zyc.util;

import redis.clients.jedis.Jedis;

/**
 * Created by YuChen Zhang on 17/09/13.
 */
public class JedisPool {
    private static Jedis jedis = new Jedis("123.206.8.180");

    public static Jedis getJedis() {
        return jedis;
    }

    public static void setJedis(Jedis jedis) {
        JedisPool.jedis = jedis;
    }
}
