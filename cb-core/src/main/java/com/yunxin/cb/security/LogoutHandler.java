package com.yunxin.cb.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by Administrator on 2015/1/19.
 */
public class LogoutHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 移除Session
        request.getSession().removeAttribute(SecurityConstants.LOGIN_SESSION);
        request.getSession().removeAttribute(SecurityConstants.USER_RESCS);
        Enumeration<String> allStr = request.getSession().getAttributeNames();
        for (Enumeration<String> e = allStr; e.hasMoreElements(); ) {
            request.getSession().removeAttribute(e.nextElement());
        }
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            String cookieName = cookies[i].getName();
            if (cookieName.equals("COOKIE_LOGIN_USERNAME")) {
                cookies[i].setValue(null);
                cookies[i].setMaxAge(0);
                response.addCookie(cookies[i]);
            }
        }
        String loginPathString = request.getContextPath() + "/index.do";
        response.sendRedirect(loginPathString);
    }
}
