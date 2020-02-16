package com.jack006.ad.utils;

import java.util.Map;
import java.util.function.Supplier;

/**
 * TODO
 *
 * @Author jack
 * @Since 1.0 2020/2/16 11:34
 */
public class CommonUtils {

    public static <K, V> V getOrCreate(K key, Map<K, V> map,
                                        Supplier<V> factory) {
        return map.computeIfAbsent(key, k -> factory.get());
    }
}
