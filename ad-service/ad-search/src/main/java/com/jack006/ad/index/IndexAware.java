package com.jack006.ad.index;

/**
 * 索引接口
 *
 * @Author jack
 * @Since 1.0 2020/2/16 10:34
 */
public interface IndexAware<K, V> {

    V get(K key);

    void add(K key, V value);

    void update(K key, V value);

    void delete(K key, V value);
}
