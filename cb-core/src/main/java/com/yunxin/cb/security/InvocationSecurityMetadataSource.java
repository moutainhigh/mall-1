/**
 *
 */
package com.yunxin.cb.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.*;

/**
 * @author keymean 加载所有资源权限关系，并在请求时返回请求资源所需的权限
 */
public class InvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource, ServletContextAware {

    private UrlMatcher urlMatcher = new AntUrlPathMatcher();

    private SecurityHolder securityHolder;

    private ServletContext servletContext;

    private Map<Resource, Collection<ConfigAttribute>> resourceMap;

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object obj) throws IllegalArgumentException {
        if (resourceMap == null) {
            loadResourceDefine();
        }
        String requestUrl = ((FilterInvocation) obj).getRequestUrl();
        for (Resource res : resourceMap.keySet()) {
            // 判断请求的URL是否需要权限访问（在resourceMap中存在该URL则需要进行权限控制）
            if (urlMatcher.pathMatchesUrl(requestUrl, res.getValue())) {
                // 返回访问该URL所需要的权限
                return resourceMap.get(res);
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

    /**
     * 加载所有的资源角色关系定义
     */
    public void loadResourceDefine() {
        // 获得所有资源
        List<Resource> resources = (List<Resource>) servletContext.getAttribute(SecurityConstants.RESOURCES);
        List<Privilege> privileges = securityHolder.loadPrivilegesDefine();
        resourceMap = new HashMap<>();
        buildResourceDefine(resources, privileges);
    }

    /**
     * 递归生成权限配置
     *
     * @param resources
     * @param privileges
     * @return
     */
    private void buildResourceDefine(List<Resource> resources, List<Privilege> privileges) {
        for (Resource resource : resources) {
            Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
            // 根据资源获得对应权限
            for (Privilege privilege : privileges) {
                if (privilege.getResourceCode().equals(resource.getCode())) {
                    ConfigAttribute configAttribute = new SecurityConfig(privilege.getRoleCode());
                    configAttributes.add(configAttribute);
                }
            }
            List<Resource> children = resource.getChildren();
            if (children != null && children.size() > 0) {
                buildResourceDefine(children, privileges);
            }
            resourceMap.put(resource, configAttributes);
        }
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void setSecurityHolder(SecurityHolder securityHolder) {
        this.securityHolder = securityHolder;
    }
}

