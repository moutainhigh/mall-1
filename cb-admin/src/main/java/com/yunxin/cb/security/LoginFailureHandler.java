package com.yunxin.cb.security;

import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author keymean
 */
@Component
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String loginPathString = "/login";
        super.setDefaultFailureUrl(loginPathString);
        super.onAuthenticationFailure(request, response, handleLoginDisableMessage(exception));
    }

    /**
     * 处理security框架返回的登录错误信息
     *
     * @param exception
     */
    private AuthenticationException handleLoginDisableMessage(AuthenticationException exception) {
        if (exception == null) {
            return null;
        }
        if (exception instanceof LockedException ||
                exception instanceof DisabledException ||
                exception instanceof AccountExpiredException ||
                exception instanceof CredentialsExpiredException) {
            return new DisabledException("您的账户被禁用！");
        } else {
            return new BadCredentialsException("用户名或密码错误");
        }
    }
}