package com.zheng.springboot.shiro.domain.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色与资源关联BO
 * @Author zhenglian
 * @Date 2018/6/16 9:47
 */
public class RoleUrlResourceBo implements Serializable {
    private Integer roleId;
    private List<Integer> resourceIds = new ArrayList<>();

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<Integer> resourceIds) {
        this.resourceIds = resourceIds;
    }
}
