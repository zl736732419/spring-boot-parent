package com.zheng.springboot.content.builder;

/**
 * @Author Administrator
 * @Date 2018/5/23 11:29
 */
public interface MessageContentBuilder {
    String build(String text, String imageUrl);
}
