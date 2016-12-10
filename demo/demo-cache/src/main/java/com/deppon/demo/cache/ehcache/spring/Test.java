package com.deppon.demo.cache.ehcache.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext
				("classpath:com/deppon/demo/cache/ehcache/spring/application.xml");
		EhCacheTestService ehCacheTestService = (EhCacheTestService)context.getBean("ehCacheTestService");
		System.out.println(ehCacheTestService.getTimestamp("param"));
        Thread.sleep(2000);
        System.out.println(ehCacheTestService.getTimestamp("param"));
        Thread.sleep(11000);
        System.out.println(ehCacheTestService.getTimestamp("param"));
        Thread.sleep(21000);
        System.out.println(ehCacheTestService.getTimestamp("param"));
	}
}
