package com.zheng.springboot.configuration.yaml;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.Optional;

/**
 * @Author zhenglian
 * @Date 2018/5/15 16:27
 */
@ConfigurationProperties(prefix = "server")
@Validated
public class Server {
    // 采用自定义验证器实现验证逻辑
//    @NotNull 
    private String url;
    private String name;
    private String address;
    
    private Date upTime;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    @Override
    public String toString() {
        String time = Optional.ofNullable(upTime).isPresent() ? upTime.toString() : null;
//        String time = null;
        return new ToStringBuilder(this).append("url", url)
                .append("name", name)
                .append("address", address)
                .append("up-time", time)
                .build();
    }
}
