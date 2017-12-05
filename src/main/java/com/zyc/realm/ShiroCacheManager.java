package com.zyc.realm;

import com.zyc.util.RedisCache;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiroCacheManager implements CacheManager{

    RedisCache redisCache;

    public RedisCache getRedisCache() {
        return redisCache;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return null;
    }

    public void setRedisCache(RedisCache redisCache) {
        this.redisCache = redisCache;
    }
}