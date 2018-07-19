package com.yunxin.cb.config;

import com.yunxin.cb.console.service.imp.SecurityService;

import com.yunxin.cb.security.LoginFailureHandler;
import com.yunxin.cb.security.LoginSuccessHandler;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.annotation.Resource;

/**
 * @author gonglei
 * @version 2.0
 * @since 2.0
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private LogoutHandler logoutHandler;

    @Resource
    private SecurityService securityService;

    @Resource
    private LoginSuccessHandler loginSuccessHandler;

    @Resource
    private LoginFailureHandler loginFailureHandler;

    @Resource
    private AccessDecisionManager accessDecisionManager;

    @Resource
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    @Bean
    public FilterSecurityInterceptor filterSecurityInterceptor() {
        FilterSecurityInterceptor interceptor = new FilterSecurityInterceptor();
        interceptor.setAccessDecisionManager(accessDecisionManager);
        interceptor.setSecurityMetadataSource(securityMetadataSource);
        return interceptor;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/rest/**");
        web.ignoring().mvcMatchers("/assets/**");
        web.privilegeEvaluator(new DefaultWebInvocationPrivilegeEvaluator(filterSecurityInterceptor()));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();

        http.authorizeRequests().anyRequest().authenticated();

        // 禁用CSRF
        http.csrf().disable();

        // 自定义登录
        http.formLogin()
                .loginPage("/index")
                .failureUrl("/login?error")
                .successHandler(loginSuccessHandler)
                .permitAll()
                .failureHandler(loginFailureHandler);

        // 自定义注销
        http.logout()
                .logoutSuccessUrl("/login?logout")
                .addLogoutHandler(logoutHandler)
                .permitAll()
                .invalidateHttpSession(true);

        // session管理
        http.sessionManagement()
                .sessionFixation()
                .changeSessionId();
//                .maximumSessions(1)
//                .expiredUrl("/login");

        http.addFilterBefore(filterSecurityInterceptor(), FilterSecurityInterceptor.class);
    }
}