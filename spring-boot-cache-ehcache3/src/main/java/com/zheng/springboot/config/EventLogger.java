package com.zheng.springboot.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

/**
 * ehcache3.x监听器
 */
public class EventLogger implements CacheEventListener<Object, Object> {
    private Log logger = LogFactory.getLog(this.getClass());
    @Override
    public void onEvent(CacheEvent<Object, Object> event) {
        logger.info("Event: " + event.getType() + " Key: " + event.getKey() 
                + " old value: " + event.getOldValue()
                + " new value: " + event.getNewValue());
    }
}