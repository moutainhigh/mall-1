package com.yunxin.cb.security;



import com.yunxin.cb.console.service.imp.SecurityService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author moxin E-mail: moxin@microlinktech.net
 * @version 17/11/10 上午10:36
 */
@Component
public class LogoutHandlerImpl implements LogoutHandler {

    @Resource
    private SecurityService securityService;


    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication == null) {
            return;
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        //<editor-fold desc="记录注销日志信息">
//        SystemLog systemLog = new SystemLog();
//        systemLog.setUserName(userDetails.getUsername());
//
//        // 查找并记录User ID
//        User user = securityService.findUserByName(userDetails.getUsername());
//        if (user != null) {
//            systemLog.setUserId(user.getUserId());
//        }
//
//        String ip = RequestIpUtils.getIp(request);
//        if (StringUtils.isNotBlank(ip)
//                && !"unknown".equalsIgnoreCase(ip)) {
//            systemLog.setIp(ip);
//        }
//        //</editor-fold>
//
//        systemLog.setOperationType(OperationType.LOGOUT);
//        logService.addSystemLog(systemLog);
    }
}
