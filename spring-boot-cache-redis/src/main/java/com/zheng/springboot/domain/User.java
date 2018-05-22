package com.zheng.springboot.domain;

import java.io.Serializable;

/**
 * @Author Administrator
 * @Date 2018/5/22 17:40
 */
public class User implements Serializable {
    private Integer userId;
    private String username;
    private String password;
    
    public User() {
    }
    
    public User(Integer userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
