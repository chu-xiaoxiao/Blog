package com.zyc.util;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.Session;

import java.awt.image.Kernel;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by YuChen Zhang on 17/11/20.
 */
public class EnCache<K,V> implements Cache<K,V> {
    private EhCacheManager cacheManager;
    private Cache<K, V> cache = null;

    public Cache<K, V> getCache() {
        try {
            if(null == cache){
                cache = cacheManager.getCache("shiro_cache");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return cache;
    }

    @Override
    public V get(K k) throws CacheException {
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        return null;
    }

    @Override
    public V remove(K k) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
    /**
     * 获取所有Session
     * @throws Exception
     */
    public Collection<Session> AllSession() throws Exception {
        Set<Session> sessions = new HashSet<Session>();
        try {
            //注意事项：必须此缓存只存储Session，要不造成性能下降
            cache = getCache();
            Collection<V> values = cache.values();
            for (V v : values) {
                if(v!=null&&!"".equals(v.toString().trim()) && v instanceof Session){
                    sessions.add((Session)v);
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return sessions;
    }

    public EhCacheManager getCacheManager() {
        return cacheManager;
    }

    public void setCacheManager(EhCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void setCache(Cache<K, V> cache) {
        this.cache = cache;
    }
}
