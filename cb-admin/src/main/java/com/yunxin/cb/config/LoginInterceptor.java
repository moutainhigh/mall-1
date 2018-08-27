package com.yunxin.cb.config;

import com.yunxin.cb.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.yunxin.cb.mall.web.SessionCnst.LOGIN_SUCCESS;
import static com.yunxin.cb.mall.web.SessionCnst.USER_ID;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

    @Resource
    private RedisService redisService ;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        /**判断当前用户有没有在redis里面*/
        HttpSession session = request.getSession();
        int userId=0;
        if(session.getAttribute(USER_ID)!=null){
            userId = (int)session.getAttribute(USER_ID);
        }
        List<Integer> list = (List<Integer>)redisService.getKey(LOGIN_SUCCESS);
        //跳转到登录页
        if(null!=list&&!list.contains(userId)){
            response.sendRedirect(request.getContextPath() + "/login");
        }else if(list==null){
            response.sendRedirect(request.getContextPath() + "/login");
        }
        return true;
    }
}
