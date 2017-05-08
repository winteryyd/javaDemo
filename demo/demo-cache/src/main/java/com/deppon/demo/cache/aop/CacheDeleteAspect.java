package com.deppon.demo.cache.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deppon.demo.cache.annotation.CacheDelete;
import com.deppon.demo.cache.annotation.CacheSave;
import com.deppon.demo.cache.redis.RedisStringCache;
import com.deppon.demo.cache.util.AopUtils;

@Aspect
@Component
public class CacheDeleteAspect {
	private static final Logger logger = LoggerFactory.getLogger(CacheDeleteAspect.class);
	@Autowired
	private RedisStringCache redisStringCache;

	@Pointcut("@annotation(com.deppon.demo.cache.annotation.CacheDelete)")
	public void cacheDeleteAdvice() {
	}

	@AfterReturning(pointcut = "cacheDeleteAdvice()", returning = "rtv")
	public void cacheUpdate(JoinPoint jp, Object rtv) {
		// 以下获取的是接口方法
		Signature s = jp.getSignature();
		MethodSignature ms = (MethodSignature) s;
		Method method = ms.getMethod();
		// 具体的实现类的方法
		// Method method = AopUtils.getMethod(jp);
		if (method == null)
			return;
		CacheDelete cacheUpdate = method.getAnnotation(CacheDelete.class);
		/**
		 * The cacheKey is the full name of redis cache key
		 */
		//String namespace = cacheUpdate.namespace();
		String[] fieldsKey = cacheUpdate.fieldsKey();
		String cacheKey = AopUtils.parseKey(/*namespace, */fieldsKey, method,
				jp.getArgs());

//		如果方法本身返回为boolean类型，且删除成功，则删除缓存
		if (rtv instanceof Boolean|| rtv instanceof Integer || "true".equals(rtv.toString())|| "1".equals(rtv.toString()) ) {
			redisStringCache.del(cacheKey);
			logger.info("删除缓存。。。");
			logger.info("key: {}",cacheKey);
		} 
		

	}
}
