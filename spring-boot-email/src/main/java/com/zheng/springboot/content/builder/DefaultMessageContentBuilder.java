package com.zheng.springboot.content.builder;

/**
 * 带有图片内容的消息体默认实现
 * @Author Administrator
 * @Date 2018/5/23 11:31
 */
public class DefaultMessageContentBuilder implements MessageContentBuilder {
    @Override
    public String build(String text, String imageUrl) {
        String format = "<p>%s</p><img src=\"%s\">";
        String content = String.format(format, text, imageUrl);
        return content;
    }
}
