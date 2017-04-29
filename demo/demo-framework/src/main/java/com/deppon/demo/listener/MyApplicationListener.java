package com.deppon.demo.listener;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.deppon.demo.cache.aop.CacheAspect;

import redis.clients.jedis.JedisPoolConfig;

public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent>{
	private static final Logger logger = LoggerFactory.getLogger(MyApplicationListener.class);
	public void onApplicationEvent(ContextRefreshedEvent event) {
		 //root application context 没有parent，他就是老大.
		if(event.getApplicationContext().getParent() == null){
		//需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
			ApplicationContext context= event.getApplicationContext();
			/*DefaultListableBeanFactory acf = (DefaultListableBeanFactory) context.getAutowireCapableBeanFactory();
			
			Properties p=(Properties)context.getBean("configproperties");
			if(p.getProperty("cache").equals("false")){
				if(acf.containsBean("cacheAspect")) {
					acf.removeBeanDefinition("cacheAspect");
				}
				if(acf.containsBean("cacheDeleteAspect")) {
					acf.removeBeanDefinition("cacheDeleteAspect");
				}
				if(acf.containsBean("cacheSaveAspect")) {
					acf.removeBeanDefinition("cacheSaveAspect");
				}
			}*/
			
			logger.info("{}",context.getBeanDefinitionCount());
			for(String str:context.getBeanDefinitionNames()){
				logger.info("{}",str);
			}
			logger.info("spring init fanish.......");
			//logger.info("{}",context.containsBean("cacheAspect"));
			
		}else{
			logger.info("root application context 没有parent，他就是老大.");
			ApplicationContext context= event.getApplicationContext();
			logger.info("{}",context.getBeanDefinitionCount());
			for(String str:context.getBeanDefinitionNames()){
				logger.info("{}",str);
			}
			//logger.info("son contain cacheAspect {}",context.containsBean("cacheAspect"));
		}
	}

}
