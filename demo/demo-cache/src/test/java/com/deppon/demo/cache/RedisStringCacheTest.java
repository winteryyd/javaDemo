package com.deppon.demo.cache;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import com.deppon.demo.cache.redis.RedisStringCache;

public class RedisStringCacheTest {
	
	RedisStringCache redisStringCache = null;
	@Before
	public void init(){
		redisStringCache = new RedisStringCache();
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-redis.xml");
		RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>)context.getBean("jedisTemplate");
		redisStringCache.setRedisTemplate(redisTemplate);
	}
	
	@Test
	public void TestSave(){
		redisStringCache.set("key1", "value1");
		System.out.println(redisStringCache.get("key1"));
		assertEquals(redisStringCache.get("key1").toString(), "value1");
	}
}
