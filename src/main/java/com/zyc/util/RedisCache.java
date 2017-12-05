package com.zyc.util;

import com.zyc.jedis.JedisTemplateUtil;
import org.apache.commons.lang.SerializationUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

/**
 * 自定义redis cache
 * @author MOTUI
 *
 */
public class RedisCache<K,V> implements Cache<K,V>{

    @Autowired
    @Qualifier("jedisTemplateUtil")
    private JedisTemplateUtil jedisTemplateUtil;;

    protected Integer select;

    public void setSelect(Integer select) {
        this.select = select;
    }

    public Object get(Object key) throws CacheException {
        byte[] bs = SerializationUtils.serialize((Serializable) key);
        byte[] value = jedisTemplateUtil.get(bs,select);
        if (value == null) {
            return null;
        }
        return SerializationUtils.deserialize(value);
    }

    /**
     * 将shiro的缓存保存到redis中
     */
    public Object put(Object key, Object value) throws CacheException {

        //序列化   和  反序列化
        jedisTemplateUtil.set(SerializationUtils.serialize((Serializable)key), SerializationUtils.serialize((Serializable)value),select*60*60,select);
        byte[] bs = jedisTemplateUtil.get(SerializationUtils.serialize((Serializable)key),select);

        Object object = SerializationUtils.deserialize(bs);

        return object;
    }

    public Object remove(Object key) throws CacheException {

        byte[] bs = jedisTemplateUtil.get(SerializationUtils.serialize((Serializable)key),select);

        jedisTemplateUtil.del(SerializationUtils.serialize((Serializable)key),select);

        return SerializationUtils.deserialize(bs);
    }
    /**
     * 清空所有缓存
     */
    public void clear() throws CacheException {
        jedisTemplateUtil.clear(select);
    }

    @Override
    public int size() {
        return jedisTemplateUtil.size(select).intValue();
    }

    /**
     * 获取所有的key
     */
    public Set keys() {
        Set<byte[]> keys = jedisTemplateUtil.keys(new String("*").getBytes(),select);

        Set<Object> set = new HashSet<Object>();

        for (byte[] bs : keys) {
            set.add(SerializationUtils.deserialize(bs));
        }
        return set;
    }

    /**
     * 获取所有的value
     */
    public Collection values() {
        Set keys = this.keys();

        List<Object> values = new ArrayList<Object>();

        for (Object object : keys) {
            byte[] bs = jedisTemplateUtil.get(SerializationUtils.serialize((Serializable)object),select);
            values.add(SerializationUtils.deserialize(bs));
        }
        return values;
    }
}