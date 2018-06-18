package com.zheng.springboot.shiro.comps;

import com.zheng.springboot.shiro.domain.Role;
import com.zheng.springboot.shiro.domain.UrlResource;
import com.zheng.springboot.shiro.domain.User;
import com.zheng.springboot.shiro.service.RoleService;
import com.zheng.springboot.shiro.service.UrlResourceService;
import com.zheng.springboot.shiro.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户认证中心
 * 进行用户身份认证和授权认证
 * @Author zhenglian
 * @Date 2018/6/16 16:27
 */
public class UserRealm extends AuthorizingRealm {
    
    @Resource
    private RoleService roleService;
    @Resource
    private UserService userService;
    @Resource
    private UrlResourceService urlResourceService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = principals.getPrimaryPrincipal().toString();
        List<Role> roles = roleService.findByUsername(username);
        Set<String> roleStrs = roles.stream()
                .filter(item -> Optional.ofNullable(item).isPresent())
                .map(Role::getRoleTag)
                .collect(Collectors.toSet());

        List<Integer> roleIds = roles.stream()
                .filter(item -> Optional.ofNullable(item).isPresent())
                .map(Role::getId)
                .collect(Collectors.toList());
        List<UrlResource> resources = urlResourceService.findByRoleIds(roleIds);
        Set<String> permissionStrs = resources.stream()
                .filter(item -> Optional.ofNullable(item).isPresent())
                .map(UrlResource::getUrl)
                .collect(Collectors.toSet());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        
        info.setRoles(roleStrs);
        info.setStringPermissions(permissionStrs);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        Object principal = token.getPrincipal();
        if (!Optional.ofNullable(principal).isPresent()) {
            throw new UnknownAccountException("用户名不能为空");
        }
        String username = token.getPrincipal().toString();
        if (StringUtils.isEmpty(username)) {
            throw new UnknownAccountException("用户名不能为空");
        }
        User user = userService.findByUsername(username);
        if (!Optional.ofNullable(user).isPresent()) {
            throw new UnknownAccountException("未知的用户名【" + username + "】");
        }
        
        // 获取用户信息
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                user, user.getPassword(), getName());
        info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
        return info;
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    public void clearAllAuthenticationCache() {
        getAuthenticationCache().clear();
    }

    public void clearAllAuthorizationCache() {
        getAuthorizationCache().clear();
    }

    public void clearAll() {
        clearAllAuthenticationCache();
        clearAllAuthorizationCache();
    }
}
