package com.deppon.demo.cache;

/**
 * Created by yamorn on 2016/7/6.
 */
public interface ExpireCallback<K, V> {
    long expire(K key, V value);

}
