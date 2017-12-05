package com.zyc.mapper;

import com.zyc.util.RedisCache;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

/**
 * Created by YuChen Zhang on 17/11/30.
 */
public class MybatisMapperCacheManager implements CacheManager {

    RedisCache mybatisMapperCacheManager;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return mybatisMapperCacheManager;
    }

    public void setMybatisMapperCacheManager(RedisCache mybatisMapperCacheManager) {
        this.mybatisMapperCacheManager = mybatisMapperCacheManager;
    }
}