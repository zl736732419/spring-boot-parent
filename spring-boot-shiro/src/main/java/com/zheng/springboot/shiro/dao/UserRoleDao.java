package com.zheng.springboot.shiro.dao;
import com.zheng.springboot.shiro.domain.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色关联数据接口
* @Author zhenglian
* @Date 10:30 2018-06-16
*/
@Mapper
public interface UserRoleDao extends BaseDao<UserRole> {
}