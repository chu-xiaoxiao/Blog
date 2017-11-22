package com.zyc.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by YuChen Zhang on 17/09/13.
 */
public class JedisDataSource {

    private  JedisPool pool;
    private  String jedisPassword;
    private  String jedishost;
    private  Integer port;
    /**
     * 最大空闲连接
     */
    private  Integer maxIdle = 10;
    /**
     * 最大阻塞时间
     */
    private  Integer maxWaitMillis = 10000;
    /**
     * 最大连接数
     */
    private  Integer setMaxTotal = 100;
    /**
     * 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
     */
    private Integer mnEvictableIdleTimeMillis;

    public JedisDataSource() {

    }

    /**
     * 建立连接池 真实环境，一般把配置参数缺抽取出来。
     *
     */

    private void createJedisPool() {
        // 建立连接池配置参数
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxIdle(this.maxIdle);

        // 设置最大阻塞时间，记住是毫秒数milliseconds
        config.setMaxWaitMillis(this.maxWaitMillis);

        // 设置空闲连接
        config.setMaxTotal(this.maxIdle);

        // 创建连接池

        pool = new JedisPool(config, this.jedishost, this.port);

    }

    /**
     * 在多线程环境同步初始化
     */
    private synchronized void poolInit() {
        if (pool == null)
            createJedisPool();
    }

    /**
     * 获取一个jedis 对象
     *
     * @return
     */
    public Jedis getJedis() {
        if (pool == null)
            poolInit();
        Jedis jedis = pool.getResource();
        jedis.auth(this.jedisPassword);
        return jedis;
    }

    /**
     * 归还一个连接
     *
     * @param jedis
     */
    public  void returnRes(Jedis jedis) {
        if (jedis.isConnected()) {
            jedis.close();
        }
    }

    public String getJedisPassword() {
        return jedisPassword;
    }

    public void setJedisPassword(String jedisPassword) {
        this.jedisPassword = jedisPassword;
    }

    public String getJedishost() {
        return jedishost;
    }

    public void setJedishost(String jedishost) {
        this.jedishost = jedishost;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(Integer maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public Integer getSetMaxTotal() {
        return setMaxTotal;
    }

    public void setSetMaxTotal(Integer setMaxTotal) {
        this.setMaxTotal = setMaxTotal;
    }

    public Integer getMnEvictableIdleTimeMillis() {
        return mnEvictableIdleTimeMillis;
    }

    public void setMnEvictableIdleTimeMillis(Integer mnEvictableIdleTimeMillis) {
        this.mnEvictableIdleTimeMillis = mnEvictableIdleTimeMillis;
    }
}
