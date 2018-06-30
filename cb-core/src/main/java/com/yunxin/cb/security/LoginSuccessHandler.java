package com.yunxin.cb.security;

import com.yunxin.cb.console.entity.Role;
import com.yunxin.cb.console.entity.SystemLog;
import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.console.entity.meta.OperateType;
import com.yunxin.cb.console.service.ISecurityService;
import com.yunxin.core.util.CalendarUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author keymean
 */
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements ServletContextAware {

    @javax.annotation.Resource
    private ISecurityService securityService;

    private ServletContext servletContext;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = securityService.getUserByName(userDetails.getUsername());
        //更新最后登录时间
        user.setLastTime(new Date());
        securityService.updateUserLastTime(user);
        // 添加登录日志
        SystemLog systemLog = new SystemLog();
        systemLog.setCreateTime(new Date());
        systemLog.setUserId(user.getUserId());
        systemLog.setUserName(user.getUserName());
//        systemLog.setIp(request.getRemoteAddr());
        String str = user.getUserName() + " 于 " + CalendarUtils.formatDate(new Date()).toString() + " 登录了该系统！";
        systemLog.setRemark(str);
        systemLog.setOperateType(OperateType.LOGIN);
//        systemLogService.addSystemLog(systemLog);

        HttpSession session = request.getSession();
        session.setAttribute(SecurityConstants.LOGIN_SESSION, user);
        session.setAttribute(SecurityConstants.LOGIN_SELLER, user.getSeller());
        //设置Cookie信息
        String host = request.getServerName();
        Cookie cookie = new Cookie("COOKIE_LOGIN_USERNAME", user.getUserName()); // 保存用户名到Cookie
        cookie.setPath("/");
        cookie.setDomain(host);
        //cookie生命周期为一天
        cookie.setMaxAge(86400);
        response.addCookie(cookie);
        cookie = new Cookie("COOKIE_LOGIN_PASSWORD", user.getPassword());
        cookie.setPath("/");
        cookie.setDomain(host);
        cookie.setMaxAge(86400);
        response.addCookie(cookie);

        // super.onAuthenticationSuccess(request, response, authentication);
        //获取到该用户拥有的权限资源的编码
        List<String> codes = securityService.getRescCodesByUser(user);
        //获取所有权限资源
        List<Resource> rescs = (List<Resource>) servletContext.getAttribute("resources");
        List<Resource> userRescs = loadUserResources(rescs, codes);   //获取所有权限资源
        session.setAttribute(SecurityConstants.USER_RESCS, userRescs);
        String target = request.getParameter("target");
        if ("pda".equals(target)) {
            // 手机登录
            Set<Role> roles = securityService.getRolesByUserId(user.getUserId());
            for (Role role : roles) {
                role.setUsers(null);
                role.setCreateTime(null);
            }
//            DataResult dataResult = new DataResult(roles);
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().print(JSONUtils.toJSONString(dataResult));
        } else {
            // 网页登录
            response.sendRedirect("console/dashboard.do");
        }
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    private List<Resource> loadUserResources(List<Resource> rescs, List<String> codes) {
        List<Resource> userRescs = new ArrayList<>();
        for (Resource resc : rescs) {
            if (codes.contains(resc.getCode())) {
                Resource resource = resc.clone();
                userRescs.add(resource);
                //子资源
                List<Resource> childResources = resc.getChildren();
                if (childResources != null && childResources.size() > 0) {
                    resource.setChildren(loadUserResources(childResources, codes));
                }
            }
        }
        return userRescs;
    }
}
