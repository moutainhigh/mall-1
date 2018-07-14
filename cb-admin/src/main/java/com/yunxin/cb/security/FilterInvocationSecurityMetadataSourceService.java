package com.yunxin.cb.security;


import com.yunxin.cb.console.service.imp.SecurityService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.yunxin.cb.security.SecurityConstants.SUPER_ROLE;


/**
 * @author moxin E-mail: moxin@microlinktech.net
 * @version 17/11/16 上午10:58
 */
@Service
public class FilterInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {


    @javax.annotation.Resource
    private SecurityService securityService;



    private final String[] ignoredUrls = {
            "/index.do**",
            "/login.do**",
            "/ajax/**",
            "/bootstrap/**",
            "/css/**",
            "/data/**",
            "/editor/**",
            "/fontawesome/**",
            "/highcharts/**",
            "/images/**",
            "/js/**",
            "/kendo/**",
            "/templates/**",
            "/upload/**",
            "/app/**",
    };

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) object).getRequest();

        boolean ignored = Arrays.stream(ignoredUrls)
                .anyMatch(url -> new AntPathRequestMatcher(url).matches(request));
        if (ignored) {
            // 该请求资源不需要权限
            return null;
        }

        // 找到当前请求的资源
        Privilege currentResc = ((SecurityProvider)securityService).getPrivilegeByRequest(request);
        if (currentResc != null) {
            try{
                parseMenuCode(currentResc, request);
            }catch (Exception ex){
                //ignore
            }
        }

        // 找出请求资源需要的权限
        Collection<ConfigAttribute> configAttributes = ((SecurityProvider)securityService).filterConfigAttributes(request);

        // 默认情况下，如果该请求资源所需权限为null或者空集合，spring security就认为该资源为公共资源，不验证登录信息
        // 这里进行处理，如果请求资源对应权限为null或者空，则手工设置权限为登录用户所具备的权限
        // 如果没有登录信息，则设置该资源需要的权限为系统超级管理员权限
        if (CollectionUtils.isEmpty(configAttributes)) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null
                    && !Objects.equals(authentication.getPrincipal(), "anonymousUser")) {
                return authentication.getAuthorities().stream()
                        .map(authorities -> new SecurityConfig(authorities.getAuthority()))
                        .collect(Collectors.toList());
            }
            return Collections.singleton(new SecurityConfig(SUPER_ROLE));
        }

        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * 解析当前请求的菜单编码，将一级、二级、三级菜单的编码绑定到Request中，便于在UI展开被选中的菜单
     * @param resource
     * @param request
     */
    private void parseMenuCode(Privilege resource, HttpServletRequest request) {
        String code = resource.getCode();
        switch (code.length()) {
            //一级菜单
            case 1:
                request.setAttribute(SecurityConstants.FIRST_MENU, code);
                break;
            //二级菜单
            case 2:
                request.setAttribute(SecurityConstants.FIRST_MENU, code.substring(0, 1));
                request.setAttribute(SecurityConstants.SECOND_MENU, code.substring(0, 2));
                break;
            //三级菜单
            default:
                request.setAttribute(SecurityConstants.FIRST_MENU, code.substring(0, 1));
                request.setAttribute(SecurityConstants.SECOND_MENU, code.substring(0, 2));
                request.setAttribute(SecurityConstants.THREE_MENU, code.substring(0, 3));
                break;
        }
    }
}
