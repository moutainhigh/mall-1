package com.yunxin.cb.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author keymean
 */
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        String target = request.getParameter("target");
        if ("pda".equals(target)) {
            // 手机登录失败
//            DataResult dataResult = new DataResult(false, "用户或密码错误");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().print(JSONUtils.toJSONString(dataResult));
        } else {
            // 网页登录失败
            String loginPathString = request.getContextPath() + "/index.do?error=true";
            response.sendRedirect(loginPathString);
        }
    }
}
