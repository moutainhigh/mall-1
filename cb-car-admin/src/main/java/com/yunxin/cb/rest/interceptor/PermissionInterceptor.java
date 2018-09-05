package com.yunxin.cb.rest.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yunxin.cb.mall.entity.UserInfo;
import com.yunxin.cb.rest.common.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PermissionInterceptor extends HandlerInterceptorAdapter{
    private final Logger log = LoggerFactory.getLogger(PermissionInterceptor.class);

    private final static Set<String> HTML_EXCLUDE_PATHS = new HashSet<String>();

    //不需要拦截的url
    static{
        HTML_EXCLUDE_PATHS.add("login.do");
        HTML_EXCLUDE_PATHS.add("index.do");
        HTML_EXCLUDE_PATHS.add("leftPage.do");
        HTML_EXCLUDE_PATHS.add("loginOut.do");
        HTML_EXCLUDE_PATHS.add("loginInfo.do");
    }

    /**
     * 在业务处理器处理请求之前被调用
     * 如果返回false
     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
     * 如果返回true
     *    执行下一个拦截器,直到所有的拦截器都执行完毕
     *    再执行被拦截的Controller
     *    然后进入拦截器链,
     *    从最后一个拦截器往回执行所有的postHandle()
     *    接着再从最后一个拦截器往回执行所有的afterCompletion()
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        log.info("==============执行顺序: 1、preHandle================");
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        url = url.substring(1, url.length());

        log.info("requestUri:"+requestUri);
        log.info("contextPath:"+contextPath);
        log.info("url:"+url);
        UserInfo user = SessionUtil.getLoginSysUser(request);
        //不需要拦截的url
        if(HTML_EXCLUDE_PATHS.contains(url)){
            return true;
        }

        if(user == null){
            //如果是ajax
            boolean isAjaxRequest = this.isAjaxRequest((HttpServletRequest)request);
            //ajax请求session过期
            if(isAjaxRequest) {
                ((HttpServletResponse)response).setHeader("session_is_timeout", "1");
                return false;
            }
            log.info("Interceptor：跳转到login页面！");
            //request.getRequestDispatcher("/main/login").forward(request, response);
            response.sendRedirect(contextPath + "/login.do");
            return false;
        }else{
            return true;
        }
    }

    /**
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
     * 可在modelAndView中加入数据，比如当前时间
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("==============执行顺序: 2、postHandle================");
    }

    /**
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
     *
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.info("==============执行顺序: 3、afterCompletion================");
    }

    /**
     * 判断是否ajax请求
     * @param request
     * @return
     */
    private boolean isAjaxRequest(HttpServletRequest request) {
        boolean res = false;
        String requestType = request.getHeader("X-Requested-With");
        if("XMLHttpRequest".equalsIgnoreCase(requestType)) {
            res = true;
        }
        return res;
    }
}
