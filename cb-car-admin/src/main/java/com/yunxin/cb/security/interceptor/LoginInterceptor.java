package com.yunxin.cb.security.interceptor;

import com.yunxin.cb.mall.entity.UserInfo;
import com.yunxin.cb.rest.mall.action.main.IndexController;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Title:
 * @Auther: eleven
 * @Date: 2018/9/7 11:21
 * @Description:
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static String[] nologin_filter_uri = new String[]{"login.do","doLogin.do","logout.do"};

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        for (String uri : nologin_filter_uri) {
            if (request.getRequestURI().endsWith(uri)) {
                return true;
            }
        }
        HttpSession session = request.getSession();
        UserInfo userInfo=(UserInfo)session.getAttribute(IndexController.LOGIN_SESSION);
        if(userInfo==null){
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        return true;
    }
}
