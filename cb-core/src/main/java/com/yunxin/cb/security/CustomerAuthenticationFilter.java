package com.yunxin.cb.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public CustomerAuthenticationFilter() {
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String requestCaptcha = request.getParameter("validateCode");
        String genCaptcha = (String)request.getSession().getAttribute("loginImageValid");
        this.logger.info("开始校验验证码，生成的验证码为：" + genCaptcha + " ，输入的验证码为：" + requestCaptcha);
        if (!genCaptcha.equals(requestCaptcha)) {
            throw new CaptchaException("验证码错误");
        } else {
            return super.attemptAuthentication(request, response);
        }
    }
}