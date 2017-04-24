package com.deppon.demo.cache;

/**
 * Created by yamorn on 2016/7/6.
 */
public interface ItemCallback<K, V> {
    Tuple<K, V> processItem(K key, V value);
}
