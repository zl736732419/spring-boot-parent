package com.zheng.springboot.constants;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author zhenglian
 * @Date 2018/5/17 21:31
 */
@Component
public class DefaultConstants {
    @Value("${mybatis.default.page}")
    public Integer defaultPage;
    @Value("${mybatis.default.limit}")
    public Integer defaultLimit;
}
