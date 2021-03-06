package com.zheng.springboot.shiro.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 * @Author zhenglian
 * @Date 15:11 2018/6/15
 */
public class Role implements Serializable {
    /**
     * 标识
     */
    private Integer id;

    /**
     * 角色标识，用于shiro权限识别
     */
    private String roleTag;
    
    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色头像
     */
    private String avatar;

    /**
     * 角色状态
     * @see com.zheng.springboot.shiro.enums.EnumRoleStatus
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 是否删除
     */
    private Integer isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleTag() {
        return roleTag;
    }

    public void setRoleTag(String roleTag) {
        this.roleTag = roleTag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
