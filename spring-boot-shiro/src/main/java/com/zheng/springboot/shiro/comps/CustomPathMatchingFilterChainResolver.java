package com.zheng.springboot.shiro.comps;

import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 动态url权限控制需要修改路径匹配
 * 将多个匹配的拦截器链生成代理返回(默认是生成第一个匹配的)
 * @Author zhenglian
 * @Date 2018/6/16 15:29
 */
@Component
public class CustomPathMatchingFilterChainResolver extends PathMatchingFilterChainResolver {
    private CustomDefaultFilterChainManager customDefaultFilterChainManager;
    public void setCustomDefaultFilterChainManager(
            CustomDefaultFilterChainManager customDefaultFilterChainManager) {
        this.customDefaultFilterChainManager = customDefaultFilterChainManager;
        setFilterChainManager(customDefaultFilterChainManager);
    }

    public FilterChain getChain(ServletRequest request,
                                ServletResponse response, FilterChain originalChain) {
        FilterChainManager filterChainManager = getFilterChainManager();
        if (!filterChainManager.hasChains()) {
            return null;
        }

        String requestURI = getPathWithinApplication(request);

        List<String> chainNames = new ArrayList<>();
        // the 'chain names' in this implementation are actually path patterns
        // defined by the user. We just use them
        // as the chain name for the FilterChainManager's requirements
        for (String pathPattern : filterChainManager.getChainNames()) {

            // If the path does match, then pass on to the subclass
            // implementation for specific checks:
            if (pathMatches(pathPattern, requestURI)) {
                chainNames.add(pathPattern);
            }
        }

        if (chainNames.size() == 0) {
            return null;
        }

        return customDefaultFilterChainManager.proxy(originalChain, chainNames);
    }
}
