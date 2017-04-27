package com.deppon.demo.cache.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.deppon.demo.cache.handler.DynamicExpireHandler;

/**
 * Created by yamorn on 2015/11/10.
 * <p/>
 * This annotation used on methods which would be intercepted by spring AOP.
 * Then build keys and storage in a cache which is usually a redis.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cacheable {

    /*
        A Logical partition namespace of cache.
     */
    String namespace() default "";

    /*
        A set of fields used to build the cache key.
     */
    String[] fieldsKey();

    /*
        expire time. Units are seconds.
     */
    int expire() default -1;

    /**
     * dynamic expire setting
     *
     * @return class
     */
    Class<? extends DynamicExpireHandler>[] dynamicExpireHandler() default {};

    /**
     * Dynamic expire field name
     *
     * @return string type
     */
    String[] dynamicExpireFields() default {};

    /**
     * Dynamic expire field format
     *
     * @return date string format
     */
    String[] dynamicExpireFieldFormat() default {};

}
