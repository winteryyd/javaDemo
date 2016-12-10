package com.deppon.demo.cache.ehcache.client;
import java.io.Serializable;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhCache {
    public static void main(String[] args) {
        // CacheManager manager = new CacheManager();
        //创建一个缓存管理器
        CacheManager singletonManager = CacheManager.create();
        //建立一个缓存实例
        Cache memoryOnlyCache = new Cache("testCache", 5000, false, false, 5, 2);
        //在内存管理器中添加缓存实例
        singletonManager.addCache(memoryOnlyCache);
        Cache cache = singletonManager.getCache("testCache");
        //使用缓存
        Element element = new Element("key1", "value1");
        cache.put(element);
        cache.put(new Element("key1", "value2"));

        element = cache.get("key1");
        Serializable value = element.getValue();
        System.out.println(value);

        int elementsInMemory = cache.getSize();
        System.out.println(elementsInMemory);

        long elementsInMemory2 = cache.getMemoryStoreSize();
        System.out.println(elementsInMemory2);

        Object obj = element.getObjectValue();
        cache.remove("key1");
        System.out.println(obj);
        singletonManager.shutdown();
        // manager.shutdown();
    }
}