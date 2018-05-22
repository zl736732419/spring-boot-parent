package com.zheng.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * redis连接集群配置
 * @Author zhenglian
 * @Date 2018/5/18 10:48
 */
@Component
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class JedisClusterConfig {
    private Integer maxRedirects;
    private String nodes;
    private String password;
    private Integer timeout;

    public Integer getMaxRedirects() {
        return maxRedirects;
    }

    public void setMaxRedirects(Integer maxRedirects) {
        this.maxRedirects = maxRedirects;
    }

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}

