package com.deppon.demo.cache.redis.springConfig;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class test {
	public static void main(String[] args) {
		// resources/beans.xml
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:com/deppon/demo/cache/redis/springConfig/beans.xml");
		JedisPool jedisPool = (JedisPool) context.getBean("jedisPool");
		Jedis client = jedisPool.getResource();
		try {
			client.select(0);
			client.set("k1", "v1");
			System.out.println(client.get("k1"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedisPool.returnResource(client);// must be
		}
	}
}
