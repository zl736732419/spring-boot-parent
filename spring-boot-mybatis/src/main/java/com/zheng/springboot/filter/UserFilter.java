package com.zheng.springboot.filter;

import java.io.Serializable;

/**
 * 用户查询条件
 * @Author zhenglian
 * @Date 2018/5/17 21:23
 */
public class UserFilter implements Serializable {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
