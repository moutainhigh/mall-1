package com.yunxin.cb.security;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;


/**
 * 访问决策管理
 *
 * @author moxin E-mail: moxin@microlinktech.net
 * @version 17/11/16 下午2:39
 */
@Service
public class AccessDecisionManagerImpl implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (CollectionUtils.isEmpty(configAttributes)) {
            // 请求的资源不需要任何权限，放行
            return;
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (SecurityConstants.SUPER_ROLE.equals(authority.getAuthority())) {
                // 如果是系统默认超级管理员角色，则放行
                return;
            }
        }
        boolean allow = configAttributes.stream()
                .anyMatch(configAttribute -> authorities.stream()
                        .anyMatch(grantedAuthority ->
                                Objects.equals(grantedAuthority.getAuthority(), configAttribute.getAttribute())));
        if (!allow) {
            // 用户无权访问
            throw new AccessDeniedException("no right");
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
