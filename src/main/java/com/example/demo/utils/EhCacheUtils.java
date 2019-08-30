package com.example.demo.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @author chenxue
 * @Description EhCache工具类
 * @Date 2019/8/30 9:41
 */

public class EhCacheUtils {

    /**
     * @Description
     * @param cacheManager
     * @param key
     * @param object
     * @author chenxue
     * @date 2019/8/30
    */
    public static void setCache(CacheManager cacheManager, String key, Object object){
        Cache cache = cacheManager.getCache("user");
        Element element = new Element(key,object);
        cache.put(element);
        cache.flush();
    }


    public static Object getCache(CacheManager cacheManager,String key){
        Object obj = null;
        Cache cache = cacheManager.getCache("user");
        if(cache.get(key) != null && !cache.get(key).equals("")){
            obj = cache.get(key).getObjectValue();
        }
        cache.flush();
        return obj;
    }
}
