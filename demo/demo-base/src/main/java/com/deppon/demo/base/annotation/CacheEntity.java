package com.deppon.demo.base.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheEntity {
    /*
        A set of fields used to build the cache key.
     */
    String[] fieldsKey();

    /*
        expire time. Units are seconds.
     */
    int expire() default -1;


}
