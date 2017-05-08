package com.deppon.demo.cache.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deppon.demo.cache.annotation.Cacheable;
import com.deppon.demo.cache.handler.DynamicExpireHandler;
import com.deppon.demo.cache.redis.RedisStringCache;
import com.deppon.demo.cache.util.AopUtils;

/**
 * Cache aspect used to intercept method which has @Cacheable annotation on it.
 * Then do the cache job.
 * Note: methods which are intercepted must not have primitive type arguments.
 */

@Aspect
@Component
public class CacheAspect {
	private static final Logger logger = LoggerFactory.getLogger(CacheAspect.class);
    @Autowired
    private RedisStringCache redisStringCache;

	@Pointcut("@annotation(com.deppon.demo.cache.annotation.Cacheable)")
    public void cacheAdvice() {
    }
	
	@Around("cacheAdvice()")
    public Object cache(ProceedingJoinPoint pjp){
		Object retObj=null;
		//以下获取的是接口方法
		/*Signature s = pjp.getSignature();
	    MethodSignature ms = (MethodSignature)s;
	    Method m = ms.getMethod();*/
		//具体的实现类的方法
		Method method = AopUtils.getMethod(pjp);
		Cacheable cacheable = method.getAnnotation(Cacheable.class);
		
		/**
         * The cacheKey is the full name of redis cache key
         */
        //String namespace = cacheable.namespace();
        String[] fieldsKey = cacheable.fieldsKey();
        String cacheKey = AopUtils.parseKey(/*namespace, */fieldsKey, method, pjp.getArgs());
        Class<? extends DynamicExpireHandler>[] handlers = cacheable.dynamicExpireHandler();

        retObj = redisStringCache.get(cacheKey);
        logger.info("搜索缓存。。。");
        logger.info("key: "+cacheKey);
		logger.info("val: "+retObj);
        if (retObj == null) {
        	try {
        		retObj = pjp.proceed();
        		if(retObj!=null){
        			 int expire = cacheable.expire();
        			 if(expire>0){
        				 redisStringCache.setEx(cacheKey, retObj, expire);
        			 }
        			 else if(handlers.length == 0){
        				 redisStringCache.set(cacheKey, retObj);
        			 }
        			 /*else {
                         Class<? extends DynamicExpireHandler> handler = handlers[0];
                         String expireFieldName = cacheable.dynamicExpireFields()[0];
                         String expireFieldFormat = cacheable.dynamicExpireFieldFormat()[0];

                         this.handlerCacheSet(expireFieldName,expireFieldFormat);

                         String expireFieldValue = getArgValue(expireFieldName, String.class, method, pjp.getArgs());
                         Date dateArg = (new SimpleDateFormat(expireFieldFormat)).parse(expireFieldValue);
                         long dynamicExpire = handler.newInstance().handler(dateArg);
                         redisStringCache.setEx(cacheKey, retObj, dynamicExpire);
                     }*/
        		}
        	} catch (Throwable e) {
        		e.printStackTrace();
        	}
        }
		return retObj;
	}
	
    /**
     * if get expireFieldName or expireFieldFormat fail
     * throw exception
     * @param expireFieldName
     * @param expireFieldFormat
     */
    /*private void handlerCacheSet(String expireFieldName,String expireFieldFormat){
        if (org.springframework.util.StringUtils.isEmpty(expireFieldName) || org.springframework.util.StringUtils.isEmpty(expireFieldFormat)) {
            throw new DynamicExpireSettingException();
        }
    }*/
    /**
     * Get arg value
     *
     * @param fieldName arg field name
     * @param argType   arg type
     * @param method    aop method
     * @param args      ProceedingJoinPoint
     * @param <T>       return type
     * @return value
     */
    /*private <T> T getArgValue(String fieldName, Class<T> argType, Method method, Object[] args) {
        *//**
         * Get method parameters using the spring support library.
         *//*
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNameArray = u.getParameterNames(method);
        *//**
         * Put all the parameters into SpEL context and analysis key using SpEL
         *//*
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < paramNameArray.length; i++) {
            context.setVariable(paramNameArray[i], args[i]);
        }
        return parser.parseExpression(fieldName).getValue(context, argType);
    }*/
    
    
}
