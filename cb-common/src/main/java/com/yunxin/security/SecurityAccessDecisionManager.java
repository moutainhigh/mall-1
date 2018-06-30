/**
 *
 */
package com.yunxin.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


/**
 * @author keymean 判断用户是否能访问所请求资源
 */
public class SecurityAccessDecisionManager implements AccessDecisionManager {

    private static final Logger logger = LoggerFactory.getLogger(SecurityAccessDecisionManager.class);

    @Override
    public void decide(Authentication authentication, Object obj, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        if (configAttributes == null) {
            // 请求的资源不需要任何权限
            if (logger.isDebugEnabled()) {
                logger.debug("decide(Authentication, Object, Collection<ConfigAttribute>) - end");
            }
            return;
        }
        // 用户所拥有的权限
        Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : grantedAuthorities) {
            // 如果是超级管理员，则return；
			if (grantedAuthority.getAuthority().equals("SUPER_ROLE")) {
				return;
			}
        }
        if (logger.isDebugEnabled()){
            logger.debug("正在访问的url是："+obj.toString());
        }
        for (ConfigAttribute configAttribute : configAttributes) {
            //访问所请求资源所需要的权限
            String needPermission = configAttribute.getAttribute();
            logger.debug("needRole is："+needPermission);
            // 访问所请求资源所需的权限
            String roleCode = ((SecurityConfig) configAttribute).getAttribute();
            for (GrantedAuthority grantedAuthority : grantedAuthorities) {
                // 访问所请求资源所需的权限与用户所拥有的权限匹配，则return；
                if (roleCode.equals(grantedAuthority.getAuthority())) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("判断到，needRole 是"+needPermission+",用户的角色是:"+grantedAuthority.getAuthority()+"，授权数据相匹配");
                        if (logger.isDebugEnabled()) {
                            logger.debug("decide(Authentication, Object, Collection<ConfigAttribute>) - end");
                        }
                    }
                    return;
                }
            }
        }
        // １、所请求资源不需要权限控制２、用户拥有访问该资源的权限；除了前面两种情况，否则抛出无权限异常；
        throw new AccessDeniedException("no right");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

}
