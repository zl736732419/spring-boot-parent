package com.zheng.springboot.shiro.enums;


import java.util.Objects;
import java.util.Optional;

/**
 * 角色状态
 * @Author zhenglian
 * @Date 15:05 2018/6/15
 */
public enum EnumRoleStatus {

    ACTIVE(100, "正常", "正常"),
    FORBIDDEN(10, "禁用", "禁用");

    ;

    EnumRoleStatus(Integer key, String value, String description) {
        this.key = key;
        this.value = value;
        this.description = description;
    }

    private Integer key;
    private String value;
    private String description;

    public static EnumRoleStatus findByKey(Integer key) {
        if (!Optional.ofNullable(key).isPresent()) {
            return null;
        }
        for(EnumRoleStatus status : EnumRoleStatus.values()) {
            if(Objects.equals(status.getKey(), key)) {
                return status;
            }
        }
        return null;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
