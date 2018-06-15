package com.zheng.springboot.shiro.service;


import com.zheng.springboot.shiro.BaseServiceTest;
import com.zheng.springboot.shiro.domain.MyPageBounds;
import com.zheng.springboot.shiro.domain.MyPageList;
import com.zheng.springboot.shiro.domain.User;
import com.zheng.springboot.shiro.enums.EnumUserStatus;
import com.zheng.springboot.shiro.filter.UserFilter;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author zhenglian
 * @Date 16:33 2018/6/15
 */
public class UserServiceTest extends BaseServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void save() {
        User user = new User();
        user.setUsername("zl");
        user.setPassword("123456");
        user.setNickname("小炼");
        user.setSalt("null");
        user.setStatus(EnumUserStatus.OK.getKey());
        user.setAvatar("http://www.baidu.com");
        userService.insert(user);
    }

    @Test
    public void selectById() {
        User user = userService.selectById(1);
        System.out.println(user);
    }

    @Test
    public void findAll() {
        List<User> list = userService.findAll();
        System.out.println(list);
    }

    @Test
    public void listPage() {
        MyPageList<User> myPageList = userService.listPage(new MyPageBounds(1, 20));
        System.out.println(myPageList);
    }

    @Test
    public void listPageByFilter() {
        UserFilter userFilter = new UserFilter();
        userFilter.setNickname("fuck");
        MyPageList<User> myPageList = userService.listPageByFilter(userFilter, new MyPageBounds(1, 20));
        System.out.println(myPageList);
        System.out.println("size: " + myPageList.getItems().size());
    }

    @Test
    public void deleteById() {
        userService.deleteById(1);
    }

}
