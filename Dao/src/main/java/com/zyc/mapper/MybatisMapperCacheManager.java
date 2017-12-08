package com.zyc.mapper;

import com.zyc.redis.JedisTemplateUtil;
import com.zyc.util.SpringUtil;
import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.SerializationUtils;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by YuChen Zhang on 17/11/30.
 */
public class MybatisMapperCacheManager implements Cache {
    {
        jedisTemplateUtil= (JedisTemplateUtil) SpringUtil.getBean("jedisTemplateUtil");
    }
    JedisTemplateUtil jedisTemplateUtil;

    private Integer select;

    private final String id;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    

    public MybatisMapperCacheManager(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        jedisTemplateUtil.set(SerializationUtils.serialize(key),SerializationUtils.serialize(value),0,select);
    }

    @Override
    public Object getObject(Object key) {
        return jedisTemplateUtil.get(SerializationUtils.serialize(key),select);
    }

    @Override
    public Object removeObject(Object key) {
        return jedisTemplateUtil.get(SerializationUtils.serialize(key),select);
    }

    @Override
    public void clear() {
        jedisTemplateUtil.clear(select);

    }

    @Override
    public int getSize() {
        return jedisTemplateUtil.size(select).intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    public void setSelect(Integer select) {
        this.select = select;
    }
}