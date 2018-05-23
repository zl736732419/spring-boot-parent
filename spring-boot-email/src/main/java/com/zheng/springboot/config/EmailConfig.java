package com.zheng.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @Author Administrator
 * @Date 2018/5/23 10:42
 */
@Configuration
public class EmailConfig {
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.from}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    
    @Autowired
    private EmailProperties emailProperties;
    
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(host);
        sender.setUsername(username);
        sender.setPassword(password);
        sender.setDefaultEncoding("UTF-8");
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.connectionTimeout", emailProperties.getConnectionTimeout()+"");
        properties.setProperty("mail.smtp.timeout", emailProperties.getTimeout()+"");
        properties.setProperty("mail.smtp.writeTimeout", emailProperties.getWriteTimeout()+"");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.starttls.required", "true");
        sender.setJavaMailProperties(properties);

        //解决文件名过长导致邮件服务器解析失败的问题
        System.getProperties().setProperty("mail.mime.splitlongparameters", "false");

        return sender;
    }
    
    
    
}
