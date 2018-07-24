package com.yunxin.cb.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yunxin.cb.orm.CustomerContextHolder;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
public class BaseResource {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取请求中token参数解析的用户ID
     * @return
     */
    protected int getCustomerId(){
        return CustomerContextHolder.getCustomerId();
    }

    /**
     * 获取IP地址
     *
     * @param request
     * @return
     */
    protected String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
