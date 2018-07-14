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
import java.util.Iterator;
import java.util.Objects;

import static com.yunxin.cb.security.SecurityConstants.SUPER_ROLE;

@Service
public class AccessDecisionManagerImpl implements AccessDecisionManager {
    public AccessDecisionManagerImpl() {
    }

    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (!CollectionUtils.isEmpty(configAttributes)) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            Iterator var5 = authorities.iterator();

            GrantedAuthority authority;
            do {
                if (!var5.hasNext()) {
                    boolean allow = configAttributes.stream().anyMatch((configAttribute) -> {
                        return authorities.stream().anyMatch((grantedAuthority) -> {
                            return Objects.equals(grantedAuthority.getAuthority(), configAttribute.getAttribute());
                        });
                    });
                    if (!allow) {
                        throw new AccessDeniedException("no right");
                    }

                    return;
                }

                authority = (GrantedAuthority)var5.next();
            } while(!SUPER_ROLE.equals(authority.getAuthority()));

        }
    }

    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }
}