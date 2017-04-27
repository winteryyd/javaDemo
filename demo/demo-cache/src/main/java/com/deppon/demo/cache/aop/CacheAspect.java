package com.deppon.demo.cache.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * Cache aspect used to intercept method which has @Cacheable annotation on it.
 * Then do the cache job.
 * Note: methods which are intercepted must not have primitive type arguments.
 */

@Aspect
@Component
public class CacheAspect {

	@Pointcut("@annotation(com.deppon.demo.cache.annotation.Cacheable)")
    public void cacheAdvice() {
    }
	
	@Around("cacheAdvice()")
    public Object cache(ProceedingJoinPoint pjp){
		Object retObj = null;
		System.out.println("cache==============");
		return retObj;
	}
}
