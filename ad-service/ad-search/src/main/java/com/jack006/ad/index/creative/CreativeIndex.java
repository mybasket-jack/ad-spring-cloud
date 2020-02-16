package com.jack006.ad.index.creative;

import com.jack006.ad.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 创意索引服务实现
 *
 * @Author jack
 * @Since 1.0 2020/2/16 15:01
 */
@Slf4j
@Component
public class CreativeIndex implements IndexAware<Long, CreativeObject> {

    private static Map<Long, CreativeObject> objectMap;

    static {
        objectMap = new ConcurrentHashMap<>();
    }

    @Override
    public CreativeObject get(Long key) {
        return objectMap.get(key);
    }

    @Override
    public void add(Long key, CreativeObject value) {
        log.info("CreativeIndex before add: {}", objectMap);
        objectMap.put(key, value);
        log.info("CreativeIndex after add: {}", objectMap);

    }

    @Override
    public void update(Long key, CreativeObject value) {
        log.info("CreativeIndex before update: {}", objectMap);
        CreativeObject oldObject = objectMap.get(key);
        if (null != oldObject) {
            objectMap.put(key, value);
        } else {
            oldObject.update(value);
        }
        log.info("CreativeIndex after update: {}", objectMap);

    }

    @Override
    public void delete(Long key, CreativeObject value) {
        log.info("before delete: {}",objectMap);
        objectMap.remove(key);
        log.info("after delete: {}", objectMap);
    }
}
