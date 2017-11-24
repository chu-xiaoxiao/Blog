package com.zyc.realm;

import com.zyc.jedis.JedisPoolUtil1;
import org.apache.commons.lang.SerializationUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

/**
 * 自定义redis cache
 * @author MOTUI
 *
 */
@Component
public class ShiroRedisCache<K,V> implements Cache<K,V>{

    @Autowired
    @Qualifier("jedisPoolUtil1")
    JedisPoolUtil1 jedisPoolUtil1;

    public Object get(Object key) throws CacheException {
        byte[] bs = SerializationUtils.serialize((Serializable) key);
        byte[] value = jedisPoolUtil1.get(bs,1);
        if (value == null) {
            return null;
        }
        return SerializationUtils.deserialize(value);
    }

    /**
     * 将shiro的缓存保存到redis中
     */
    public Object put(Object key, Object value) throws CacheException {
        jedisPoolUtil1.getJedis().select(1);
        //序列化   和  反序列化
        jedisPoolUtil1.set(SerializationUtils.serialize((Serializable)key), SerializationUtils.serialize((Serializable)value),1,1*60*60);
        byte[] bs = jedisPoolUtil1.get(SerializationUtils.serialize((Serializable)key),1);

        Object object = SerializationUtils.deserialize(bs);

        return object;
    }

    public Object remove(Object key) throws CacheException {
        jedisPoolUtil1.getJedis().select(1);

        byte[] bs = jedisPoolUtil1.get(SerializationUtils.serialize((Serializable)key),1);

        jedisPoolUtil1.del(SerializationUtils.serialize((Serializable)key),1);

        return SerializationUtils.deserialize(bs);
    }
    /**
     * 清空所有缓存
     */
    public void clear() throws CacheException {
        jedisPoolUtil1.clear();
    }

    @Override
    public int size() {
        return jedisPoolUtil1.size().intValue();
    }

    /**
     * 获取所有的key
     */
    public Set keys() {
        Set<byte[]> keys = jedisPoolUtil1.allKeys(new String("*").getBytes());

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
            byte[] bs =jedisPoolUtil1.get(SerializationUtils.serialize((Serializable)object));
            values.add(SerializationUtils.deserialize(bs));
        }
        return values;
    }

/*    public SerializationUtils getSerializationUtils() {
        return SerializationUtils;
    }

    public void setSerializationUtils(SerializationUtils SerializationUtils) {
        this.SerializationUtils = SerializationUtils;
    }*/
}