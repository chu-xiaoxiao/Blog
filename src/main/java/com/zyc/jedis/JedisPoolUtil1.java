package com.zyc.jedis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by YuChen Zhang on 17/09/13.
 */

public class JedisPoolUtil1 {

    public Jedis jedis;

    public Jedis getJedis(){
        return jedis;
    }
    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }

    public void set(String key, String value){
        jedis.set(key,value);
    }

    public Object get(String key){
        return jedis.get(key);
    }
    public void del(String key){
        jedis.del(key);
    }
    public void set(byte[] key,byte[] value){
        jedis.set(key,value);
    }
    public void setWithIndexAndExpire(byte[] key,byte[] value,Integer index,Integer second){
        jedis.expire(key,second);
        jedis.select(index);
        this.set(key,value);
    }

    public byte[] get(byte[] key){
        return jedis.get(key);
    }
    public byte[] getWithIndex(byte[] key,Integer select){
        jedis.select(select);
        return this.get(key);
    }
    public void del(byte[] key){
        jedis.del(key);
    }
    public void delWhthIndel(byte[] key,Integer select){
        jedis.select(select);
        this.del(key);
    }
    /**
     * 存入map
     * @param key
     * @param value
     */
    public void hmset(String key, Map value){
        jedis.hmset(key,value);
    }

    /**
     * 取出map
     * mkey map的键值
     * @param key
     * @param mkey
     * @return
     */
    public List hmget (String key,String ... mkey){
        return jedis.hmget(key,mkey);
    }

    /**
     * 删除map
     * mkey map的键值
     * @param key
     * @param mkey
     */
    public void hdel(String key,String ...mkey){
        jedis.hdel(key,mkey);
    }

    /**
     * 存入list
     * @param key
     * @param value
     */
    public void lpush(String key,String value){
        jedis.lpush(key,value);
    }

    /**
     * 将list存入list
     * @param key
     * @param list
     */
    public void lpushAll(String key , List<String> list){
        for(String temp : list) {
            jedis.lpush(key,temp);
        }
    }

    /**
     * 从list中取出值
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> lrange(String key,Integer start,Integer end){
        return jedis.lrange(key,start,end);
    }

    /**
     * 添加单个值进入set
     * @param key
     * @param value
     */
    public void sadd(String key,String value){
        jedis.sadd(key,value);
    }

    /**
     * 添加所有值入set
     * @param key
     * @param value
     */
    public void saddAll(String key,Set<String> value){
        for(String temp:value){
            jedis.sadd(key,temp);
        }
    }

    /**
     * 从set中删除某个值
     * @param key
     * @param value
     */
    public void srem(String key,String value){
        jedis.srem(key,value);
    }

    /**
     * 清空数据库
     */
    public void clear(){
        jedis.flushDB();
    }

    public Long size(){
        return jedis.dbSize();
    }

    public Set<byte[]> allKeys(byte[] pattern){
        return jedis.keys(pattern);
    }
    public Set<String> allKeys(String pattern){
        return jedis.keys(pattern);
    }
}