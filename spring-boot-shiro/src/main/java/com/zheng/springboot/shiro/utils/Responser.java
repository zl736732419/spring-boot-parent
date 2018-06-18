package com.zheng.springboot.shiro.utils;

/**
 * 用于视图层返回数据的统一格式
 * @Author zhenglian
 * @Date 2018/6/18 9:53
 */
public class Responser {
    private Integer code;
    private String message;
    private Object data;
    
    private Responser() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 生成器模式
     */
    private static class Builder {
        private Responser responser;
        
        public Builder() {
            this.responser = new Responser();
        }
        
        public Builder code(Integer code) {
            responser.setCode(code);
            return this;
        }
        
        public Builder message(String message) {
            responser.setMessage(message);
            return this;
        }
        
        public Builder data(Object data) {
            responser.setData(data);
            return this;
        }
        
        public Responser build() {
            return responser;
        }
    }
}

