package com.zheng.springboot.shiro.comps;

import com.zheng.springboot.shiro.dao.RoleDao;
import com.zheng.springboot.shiro.dao.UrlResourceDao;
import com.zheng.springboot.shiro.domain.Role;
import com.zheng.springboot.shiro.domain.UrlResource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * shiro权限动态刷新
 * @Author zhenglian
 * @Date 2018/6/16 10:46
 */
@Component
public class ShiroFilterChainManager {
    @Resource
    private DefaultFilterChainManager manager;
    @Resource
    private RoleDao roleDao;
    @Resource
    private UrlResourceDao urlResourceDao;

    private static final String AUTH_ROLES = "roles";
    private static final String AUTU_PERMS = "perms";
    
    /**
     * 缓存shiro默认的拦截器链
     * 这里必须是linkedHashMap,不然从配置文件读取的权限乱套会有问题
     */
    private LinkedHashMap<String, NamedFilterList> filterChains;

    /**
     * 获取配置文件中配置的默认拦截器链
     * 如/login.jsp, /logout, /**等
     */
    @PostConstruct
    public void init() {
        filterChains = new LinkedHashMap<>(manager.getFilterChains());
    }

    /**
     * 更新所有url的权限信息
     */
    public void initFilterChain() {
        List<UrlResource> resources = urlResourceDao.findAll();
        initFilterChain(resources);
    }

    /**
     * 更新指定url的权限信息
     * @param resources
     */
    public void initFilterChain(List<UrlResource> resources) {
        if (CollectionUtils.isEmpty(resources)) {
            return;
        }

        manager.getFilterChains().clear();
        if (MapUtils.isNotEmpty(filterChains)) {
            manager.getFilterChains().putAll(filterChains);
        }

        resources.stream()
                .filter(item -> Optional.ofNullable(item).isPresent())
                .forEach(item -> {
                    Integer resourceId = item.getId();
                    String url = item.getUrl();
                    
                    initRoles(url, resourceId);
                    initPerms(url);
                });
    }

    /**
     * 初始化url对应的权限
     * @param url
     */
    private void initPerms(String url) {
        if (StringUtils.isEmpty(url)) {
            return;
        }
        manager.addToChain(url, AUTU_PERMS, url);
    }

    /**
     * 初始化url对应的角色
     * @param url
     * @param resourceId
     */
    private void initRoles(String url, Integer resourceId) {
        if (StringUtils.isEmpty(url)) {
            return;
        }
        if (!Optional.ofNullable(resourceId).isPresent() || resourceId <= 0) {
            return;
        }
        List<Role> roles = roleDao.findByResourceId(resourceId);
        if (CollectionUtils.isEmpty(roles)) {
            return;
        }
        List<String> roleStrs = roles.stream()
                .filter(role -> Optional.ofNullable(role).isPresent())
                .map(Role::getName)
                .collect(Collectors.toList());
        manager.addToChain(url, AUTH_ROLES, StringUtils.join(roleStrs, ","));
    }

}
