package com.zyc.realm;

import com.zyc.redis.RedisCache;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

public class ShiroCacheManager implements CacheManager {

    RedisCache redisCache;

    public RedisCache getRedisCache() {
        return redisCache;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return this.redisCache;
    }

    public void setRedisCache(RedisCache redisCache) {
        this.redisCache = redisCache;
    }
}