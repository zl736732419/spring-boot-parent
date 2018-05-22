package com.zheng.springboot.error;

/**
 * 出现错误时，将错误状态码和错误信息封装实体中返回到前端
 * @Author zhenglian
 * @Date 2018/5/16 17:18
 */
public class CustomErrorType {
    private int status;
    private String message;

    public CustomErrorType(){
    }

    public CustomErrorType(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
