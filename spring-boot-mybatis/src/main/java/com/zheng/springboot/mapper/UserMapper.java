package com.zheng.springboot.mapper;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.zheng.springboot.domain.User;
import com.zheng.springboot.filter.UserFilter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author zhenglian
 * @Date 2018/5/17 16:17
 */
// 注解配置时引用，需要依赖mybatis starter
@Mapper
//@MybatisMapper
//@Repository
public interface UserMapper {
    User findById(Integer id);
    int save(User user);
    int deleteById(Integer id);
    int update(User user);
    PageList<User> findByPage(@Param("filter") UserFilter userFilter, PageBounds pageBounds);
}
