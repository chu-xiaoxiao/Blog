package com.zyc.realm;

import com.zyc.realm.ShiroRedisCache;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

class CustomCacheManager implements CacheManager{
    @Autowired
    ShiroRedisCache shiroRedisCache;
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        return shiroRedisCache;
    }
}