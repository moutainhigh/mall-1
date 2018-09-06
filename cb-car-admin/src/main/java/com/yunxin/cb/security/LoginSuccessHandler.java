package com.yunxin.cb.security;


import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.console.service.imp.SecurityService;
import com.yunxin.cb.redis.RedisService;
import org.apache.commons.lang.StringUtils;
import org.apache.http.conn.util.InetAddressUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.yunxin.cb.mall.web.SessionCnst.*;
import static com.yunxin.cb.security.SecurityConstants.USER_PRIVILEGES;


/**
 * 登录认证成功处理
 *
 * @author moxin E-mail: com.gpcyun
 * @version 17/11/3 下午4:59
 */
@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @javax.annotation.Resource
    private SecurityService securityService;

    @Resource
    private RedisService redisService;


    public void setRedisLoginSuccess(int userId){
        List<Integer> list = null;
        if(redisService.getKey(LOGIN_SUCCESS) != null){
            list = (List<Integer>)redisService.getKey(LOGIN_SUCCESS);
        }else{
            list = new ArrayList<>();
        }
        if(!list.contains(userId)){
            list.add(userId);
        }
        redisService.updateRedisByKey(LOGIN_SUCCESS,list);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = securityService.getUserByName(userDetails.getUsername());

        String ipAddr = recordLog(request, user);
        HttpSession session = request.getSession();
        List<Privilege> userRescs = ((SecurityProvider) securityService).loadUserPrivileges(authentication);
        session.setAttribute(USER_PRIVILEGES, userRescs);
        session.setAttribute(SecurityConstants.LOGIN_SESSION, user);
        session.setAttribute(ROLE_NAMES, user.getRoleNames());
        session.setAttribute(USER_NAME, user.getUserName());
        session.setAttribute(USER_LAST_TIME, user.getLastTime());
        session.setAttribute(USER_IP, ipAddr);
        session.setAttribute(USER_CREATE_TIME, user.getCreateTime());
        session.setAttribute(SecurityConstants.LOGIN_SELLER,user.getSeller());

        session.setAttribute(USER_ID, user.getUserId());
        setRedisLoginSuccess(user.getUserId());
        setAlwaysUseDefaultTargetUrl(true);
        setDefaultTargetUrl("/console/dashboard.do");

//        if (null != userRescs) {
//            // 找出用户可访问的第一个页面
//            for (Privilege userResc : userRescs) {
//                boolean isBreak = false;
//                if (userResc.getChildren() != null) {
//                    for (Privilege resource : userResc.getChildren()) {
//                        isBreak = isTargetUrl(resource);
//                        if (isBreak) {
//                            break;
//                        }
//                    }
//                } else {
//                    isBreak = isTargetUrl(userResc);
//                }
//                if (isBreak) {
//                    break;
//                }
//            }
//
//        }

        super.onAuthenticationSuccess(request, response, authentication);
    }

    /**
     * 是否是路径请求
     *
     * @param resource
     * @return
     */
    private boolean isTargetUrl(Privilege resource) {
        String value = resource.getValue();
        if (StringUtils.isNotBlank(value)
                && !value.startsWith("http")) {
            setDefaultTargetUrl(value);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 记录登录日志
     *
     * @param request
     * @param user
     */
    private String recordLog(HttpServletRequest request, User user) {
        // 更新管理员登录时间
        securityService.updateLoginTime(user.getUserId(), new Date());

        String ipAddr = request.getRemoteAddr();
        //设置localhost登录的ip为"127.0.0.1"
        if (InetAddressUtils.isIPv4Address(ipAddr) || InetAddressUtils.isIPv6Address(ipAddr)) {
            if ("0:0:0:0:0:0:0:1".equals(ipAddr)) {
                ipAddr = "127.0.0.1";
            }
        } else {
            ipAddr = "127.0.0.1";
        }
        return ipAddr;
//
//        /**
//         * 添加登录日志
//         */
//        LoginRecord loginRecord = new LoginRecord();
//        loginRecord.setLoginTime(new Date());
//        loginRecord.setUserId(user.getUserId());
//        loginRecord.setUserName(user.getUserName());
//        loginRecord.setIp(ipAddr);
//        securityService.createLoginRecord(loginRecord);
    }
}
