package com.zheng.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * redis cache配置
 * @Author zhenglian
 * @Date 2018/5/19 14:48
 */
@Component
@ConfigurationProperties(prefix = "spring.cache.redis")
public class RedisCacheProperties {
    private Integer ttl;
    private Boolean cacheNullValues;
    private Boolean usePrefix;

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public Boolean getCacheNullValues() {
        return cacheNullValues;
    }

    public void setCacheNullValues(Boolean cacheNullValues) {
        this.cacheNullValues = cacheNullValues;
    }

    public Boolean getUsePrefix() {
        return usePrefix;
    }

    public void setUsePrefix(Boolean usePrefix) {
        this.usePrefix = usePrefix;
    }
}
