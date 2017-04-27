package com.deppon.demo.cache.aop;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import com.deppon.demo.cache.annotation.Cacheable;
import com.deppon.demo.cache.exception.DynamicExpireSettingException;
import com.deppon.demo.cache.handler.DynamicExpireHandler;
import com.deppon.demo.cache.redis.RedisStringCache;
import com.deppon.demo.cache.util.AopUtils;

/**
 * Cache aspect used to intercept method which has @Cacheable annotation on it.
 * Then do the cache job. Note: methods which are intercepted must not have
 * primitive type arguments.
 */

@Aspect
@Component
public class CacheUpdateAspect {

	@Autowired
	private RedisStringCache redisStringCache;

	@Pointcut("@annotation(com.deppon.demo.cache.annotation.CacheUpdate)")
	public void cacheUpdateAdvice() {
	}

	@AfterReturning(pointcut = "cacheUpdateAdvice()", returning = "rtv")
	public void cacheUpdate(JoinPoint jp, Object rtv) {
		System.out.println("cacheUpdate:"+rtv);
	}
}
