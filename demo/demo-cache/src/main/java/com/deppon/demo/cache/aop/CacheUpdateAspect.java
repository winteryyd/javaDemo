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

import com.deppon.demo.cache.annotation.CacheUpdate;
import com.deppon.demo.cache.redis.RedisStringCache;
import com.deppon.demo.cache.util.AopUtils;

@Aspect
@Component
public class CacheUpdateAspect {
	private static final Logger logger = LoggerFactory.getLogger(CacheUpdateAspect.class);
	@Autowired
	private RedisStringCache redisStringCache;

	@Pointcut("@annotation(com.deppon.demo.cache.annotation.CacheUpdate)")
	public void cacheUpdateAdvice() {
	}

	@AfterReturning(pointcut = "cacheUpdateAdvice()", returning = "rtv")
	public void cacheUpdate(JoinPoint jp, Object rtv) {
		// 以下获取的是接口方法
		Signature s = jp.getSignature();
		MethodSignature ms = (MethodSignature) s;
		Method method = ms.getMethod();
		// 具体的实现类的方法
		// Method method = AopUtils.getMethod(jp);
		if (method == null)
			return;
		CacheUpdate cacheUpdate = method.getAnnotation(CacheUpdate.class);
		/**
		 * The cacheKey is the full name of redis cache key
		 */
		String namespace = cacheUpdate.namespace();
		String[] fieldsKey = cacheUpdate.fieldsKey();
		String cacheKey = AopUtils.parseKey(namespace, fieldsKey, method,
				jp.getArgs());

		Object value = null;
//		如果方法本身返回为boolean类型，且修改成功，则缓存参数entity
		if (rtv instanceof Boolean && "true".equals(rtv.toString())) {
			value=jp.getArgs()[0];
			//logger.info("如果方法本身返回为boolean类型，且修改成功，则缓存参数entity");
		} 
//		如果方法本身返回对象，且返回值不为空，则缓存返回值
		else if (rtv instanceof Object && rtv != null) {
			value = rtv;
			//logger.info("如果方法本身返回对象，且返回值不为空，则缓存返回值");
		}
		
		if (value == null)
			return;

		logger.info("更新缓存。。。");
		logger.info("key: {}",cacheKey);
		logger.info("val: {}",value);
		int expire = cacheUpdate.expire();
		if (expire > 0) {
			redisStringCache.setEx(cacheKey, value, expire);
		} else {
			redisStringCache.set(cacheKey, value);
		}

	}
}
