package com.example.EAS.util;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.xml.XmlConfiguration;
import org.springframework.core.io.ClassPathResource;

import java.util.concurrent.ConcurrentHashMap;

public class EhcacheUtils {

    private static CacheManager cacheManager =null;

    private static ConcurrentHashMap<String,Cache<String,String>> cacheMap=new ConcurrentHashMap<String,Cache<String,String>>();

    static{
        org.springframework.core.io.Resource fileRource = new ClassPathResource("ehcache.xml");
        try {
            XmlConfiguration xmlConfig = new XmlConfiguration(fileRource.getURL());
            System.out.println(xmlConfig.asRenderedDocument());
            CacheManager myCacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
            myCacheManager.init();
            cacheManager= myCacheManager;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static Cache<String,String> getCache(String cacheName){
        Cache<String,String> cache=cacheMap.get(cacheName);
        if(cache == null){
            cache=getCacheManager().getCache(cacheName, String.class, String.class);
            if(cache != null){
                cacheMap.put(cacheName, cache);
            }
        }
        return cache;
    }

    public static CacheManager getCacheManager(){
        if(cacheManager != null ){
            return cacheManager;
        }
        org.springframework.core.io.Resource fileRource = new ClassPathResource("ehcache.xml");
        System.out.println(fileRource.getFilename());
        try {
            XmlConfiguration xmlConfig = new XmlConfiguration(fileRource.getURL());
            System.out.println(xmlConfig.asRenderedDocument());
            CacheManager myCacheManager = CacheManagerBuilder.newCacheManager(xmlConfig);
            myCacheManager.init();
            cacheManager= myCacheManager;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return cacheManager;
    }

    public static void main(String[] args) {
        Cache<String,String> cache=getCache("EASCache");
        cache.put("key1", "value1");
        System.out.println(cache.get("key1"));
        try {
            Thread.sleep(2000L);
            System.out.println(cache.get("key1"));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
