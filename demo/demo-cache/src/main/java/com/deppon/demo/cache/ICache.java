package com.deppon.demo.cache;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public interface ICache<K, V> extends InitializingBean, DisposableBean {


    void set(K key, V value);

    /**
     * @param key    cache key
     * @param value  cache value
     * @param expire seconds
     */
    void setEx(K key, V value, long expire);


    /**
     * use pipelined
     * Sets the given keys to their respective values. MSET replaces existing values with
     * new values, just as regular SET.
     * MSET is atomic. so all given keys are set at once.
     *
     * @param values A set of cache value
     */
    void mSet(Map<K, V> values);


    /**
     * Get the value of key.
     *
     * @param key cache key
     * @return generic value
     */
    <T> T get(final String key);

    /**
     * O(N) where N is the number of keys that will be removed.
     *
     * @param keys An array of cache key
     */
    void del(Set<K> keys);

    /**
     * remove the value of key
     *
     * @param key cache key
     */
    void del(String key);

    /**
     * get key collection by pattern match
     *
     * @param pattern regex expression
     * @return set
     */
    Set<K> keys(String pattern);

    /**
     * Return if key is exists. Since Redis 3.0.3 it is possible to specify multiple keys.
     *
     * @param key cache key
     * @return True is exits this key. vice versa.
     */
    boolean exists(K key);

    /**
     * multiple keys with the same namespase will be removed.
     *
     * @param pattern pattern of keys that will delete
     */
    void multiDel(K pattern);


    /**
     * get the values length of the key
     *
     * @param key key
     * @return size
     */
    long size(K key);
}

