package com.deppon.demo.batch.ItemWriter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.deppon.demo.base.annotation.CacheEntity;
import com.deppon.demo.base.entity.TestEntity;
import com.deppon.demo.cache.redis.RedisStringCache;

public class ItemToRedisWriter implements ItemWriter<Object>{
	private static final Logger logger = LoggerFactory.getLogger(ItemToRedisWriter.class);
	
	@Autowired
    private RedisStringCache redisStringCache;
	
	public void write(List<? extends Object> items) throws Exception {
		for(Object item:items ){
			logger.info("{}",buildReidsKey(item));
		}
	}

	private String buildReidsKey(Object item){
		CacheEntity cacheEntity = null;
		for(Class<?> clazz = item.getClass();clazz!=Object.class&&cacheEntity==null;clazz=clazz.getSuperclass()){
			cacheEntity = clazz.getAnnotation(CacheEntity.class);
		}
		if(cacheEntity==null){
			return null;
		}
		String[] fieldsKey = cacheEntity.fieldsKey();
		int expire = cacheEntity.expire();
		StringBuilder key = new StringBuilder();
		key.append(item.getClass().getName());
		for(String filed:fieldsKey){
			try {
				key.append(":").append(BeanUtils.getProperty(item,filed));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		if(expire>0){
			redisStringCache.setEx(key.toString(), item, expire);
		}else{
			redisStringCache.set(key.toString(), item);
		}
		return key.toString();
	}
}
