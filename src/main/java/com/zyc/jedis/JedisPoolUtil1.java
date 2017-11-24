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
    public void set(String key, String value,Integer index,Integer second){
        jedis.select(index);
        jedis.set(key,value);
        jedis.expire(key,second);
    }
    public Object get(String key){
        return jedis.get(key);
    }
    public Object get(String key,Integer select){
        jedis.select(select);
        return this.get(key);
    }
    public void del(String key){
        jedis.del(key);
    }
    public void del(String key,Integer select){
        jedis.select(select);
        this.del(key);
    }

    public void set(byte[] key,byte[] value){
        jedis.set(key,value);
    }
    public void set(byte[] key,byte[] value,Integer index,Integer second){
        jedis.select(index);
        this.set(key,value);
        jedis.expire(key,second);
    }

    public byte[] get(byte[] key){
        return jedis.get(key);
    }
    public byte[] get(byte[] key,Integer select){
        jedis.select(select);
        return this.get(key);
    }
    public void del(byte[] key){
        jedis.del(key);
    }
    public void del(byte[] key,Integer select){
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
    public void hmset(String key, Map value,Integer index,Integer second){
        jedis.select(index);
        this.hmset(key,value);
        jedis.expire(key,second);
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
    public List hmget (String key,Integer select,String ... mkey){
        jedis.select(select);
        return this.hmget(key,mkey);
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
    public void hdel(String key,Integer select,String ...mkey){
        jedis.select(select);
        this.hdel(key,mkey);
    }
    /**
     * 存入list
     * @param key
     * @param value
     */
    public void lpush(String key,String value){
        jedis.lpush(key,value);
    }
    public void lpush(String key,String value,Integer index,Integer second){
        jedis.select(index);
        this.lpush(key,value);
        jedis.expire(key,second);
    }

    /**
     * 将list存入list
     * @param key
     * @param list
     */
    public void lpushAll(String key , List<String> list){
        for(String temp : list) {
            this.lpush(key,temp);
        }
    }
    public void lpushAll(String key , List<String> list,Integer index,Integer second){
        jedis.select(index);
        this.lpushAll(key,list);
        jedis.expire(key,second);
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
    public List<String> lrange(String key,Integer start,Integer end,Integer select){
        jedis.select(select);
        return this.lrange(key,start,end);
    }

    /**
     * 添加单个值进入set
     * @param key
     * @param value
     */
    public void sadd(String key,String value){
        jedis.sadd(key,value);
    }
    public void sadd(String key,String value,Integer index,Integer second){
        jedis.select(index);
        this.sadd(key,value);
        jedis.expire(key,second);
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
    public void saddAll(String key,Set<String> value,Integer index,Integer second){
        jedis.select(index);
        this.saddAll(key,value);
        jedis.expire(key,second);
    }

    /**
     * 从set中删除某个值
     * @param key
     * @param value
     */
    public void srem(String key,String value){
        jedis.srem(key,value);
    }
    public void srem(String key,String value,Integer select){
        jedis.select(select);
        this.srem(key,value);
    }

    /**
     * 清空数据库
     */
    public void clear(){
        jedis.flushDB();
    }
    public void clear(Integer select){
        jedis.select(select);
        jedis.flushDB();
    }

    public Long size(){
        return jedis.dbSize();
    }
    public Long size(Integer select){
        jedis.select(select);
        return jedis.dbSize();
    }

    public Set<byte[]> allKeys(byte[] pattern){
        return jedis.keys(pattern);
    }
    public Set<byte[]> allKeys(byte[] pattern,Integer select){
        jedis.select(select);
        return this.allKeys(pattern);
    }
    public Set<String> allKeys(String pattern){
        return jedis.keys(pattern);
    }
    public Set<String> allKeys(String pattern,Integer select){
        jedis.select(select);
        return this.allKeys(pattern);
    }

    public Long getTtl(String key){
        return jedis.ttl(key);
    }
    public Long getTtl(String key,Integer select){
        jedis.select(select);
        return this.getTtl(key);
    }
    public Long getTtl(byte[] key){
        return jedis.ttl(key);
    }
    public Long getTtl(byte[] key,Integer select){
        jedis.select(select);
        return this.getTtl(key);
    }
}