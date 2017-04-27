package com.deppon.demo.cache.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yamorn on 2015/11/23.
 *
 * Cache synchronized
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheUpdate {
    /*
    A Logical partition namespace of cache.
 */
    String namespace() default "";

    /*
        A set of fields used to build the cache key.
     */
    String[] fieldsKey() default {};

    /*
        The update entity of field
     */
    String valueField() default "";

    /*
        The value of update entity
     */
    Class valueType();

    /**
     *  If use this method, AOP will update fieldsKey with return value. Ignore valueField() and valueField()
     */
    boolean updateRetVal() default false;

    /*
        expire time. Units are seconds.
     */
    int expire() default -1;
}
