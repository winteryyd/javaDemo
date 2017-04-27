package com.deppon.demo.cache.util;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class AopUtils {
	private static final String NAMESPACE_SPLIT = "_";
    private static final String KEY_SPLIT = ":";
	
	
    /**
     * Get the intercept method object.
     * <p/>
     * MethodSignature.getMethod() The top-level interface or parent class method objects
     * While the cache in the annotation method.
     * Should the object method uses reflection to obtain the current object so.
     */
    public static Method getMethod(JoinPoint pjp) {
        Object[] args = pjp.getArgs();
        Class[] argTypes = new Class[pjp.getArgs().length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }

        Method method = null;
        try {
            method = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), argTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return method;
    }

    /**
     * Get method parameters name
     *
     * @param method method object
     * @return parameters name
     */
    public static String[] getParametersName(Method method) {
        if (method == null)
            return null;
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        return u.getParameterNames(method);
    }
    
    /**
     * Parse key and build a redis key with namespace.
     * The key's definition is support the SpEL Expression
     */
    public static String parseKey(String namespace, String[] fieldsKey, Method method, Object[] args) {
        StringBuilder sb = new StringBuilder();
        /**
         * Get method parameters using the spring support library.
         */
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNameArray = u.getParameterNames(method);
        /**
         * Put all the parameters into SpEL context and analysis key using SpEL
         */
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < paramNameArray.length; i++) {
        	//System.out.println(paramNameArray[i]+"   -   "+args[i]);
            context.setVariable(paramNameArray[i], args[i]);
        }

        sb.append(namespace).append(NAMESPACE_SPLIT);
        for (String key : fieldsKey) {
        	//System.out.println(key);
            Object value = parser.parseExpression(key).getValue(context, Object.class);
            //System.out.println(value);
            sb.append(value).append(KEY_SPLIT);
        }
        String fullKey = sb.toString();
        System.out.println(fullKey);
        int index;
        if (fullKey.length() > 0 && (index = fullKey.lastIndexOf(":")) > 0) {
            fullKey = fullKey.substring(0, index);
        }
        return fullKey;
    }
}
