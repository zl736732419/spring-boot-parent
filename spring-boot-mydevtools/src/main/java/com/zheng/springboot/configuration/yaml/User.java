package com.zheng.springboot.configuration.yaml;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * 采用@ConfigurationProperties注解可以使用宽松的属性绑定方式，
 * spring boot 不严格要求配置的名称与给定的属性名完全一样，可以有几种不同的表达方式
 * 比如属性名firstName可以与配置文件中的first_name,firstName,first-name三种形式成功匹配
 * @Author zhenglian
 * @Date 2018/5/15 17:27
 */
@ConfigurationProperties(prefix = "acme")
public class User {
    private String firstName;
    
    private Map<String, Server> map = new HashMap<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Map<String, Server> getMap() {
        return map;
    }

    public void setMap(Map<String, Server> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("first-name", firstName)
                .append("server", map)
                .build();
    }
}
