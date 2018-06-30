package com.yunxin.cb.security;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Aidy_He
 */
public class SecurityInterceptorFilter extends AbstractSecurityInterceptor implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityInterceptorFilter.class);

    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException,
            ServletException {
        if (logger.isDebugEnabled()) {
            logger.debug("SecurityInterceptorFilter.doFilter()- start");
        }
        FilterInvocation invocation = new FilterInvocation(arg0, arg1, arg2);
        invoke(invocation);
        if (logger.isDebugEnabled()) {
            logger.debug("SecurityInterceptorFilter.doFilter()- end");
        }
    }

    public void invoke(FilterInvocation invocation) throws IOException, ServletException {
        InterceptorStatusToken token = super.beforeInvocation(invocation);
        try {
            invocation.getChain().doFilter(invocation.getHttpRequest(), invocation.getHttpResponse());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public Class<? extends Object> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
        return securityMetadataSource;
    }

    public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }

}
