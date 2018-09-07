package com.yunxin.cb.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yunxin.cb.orm.CustomerContextHolder;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

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


    /**
     * 得到一个完整URL（包含参数）
     * @param request
     * @param urlIsTrue     是否包含URL,true包含,false不包含
     * @return
     */
    public static String getFullUrlIsTrue(HttpServletRequest request,boolean urlIsTrue) {
        StringBuffer url = new StringBuffer();
        int i = 0;
        if(urlIsTrue) {
            url.append(request.getRequestURL());
        }
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String key = (String) paramNames.nextElement();
            String[] paramValues = request.getParameterValues(key);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                //if (paramValue.length() != 0) {
                if (i++ == 0) {
                    url.append("?" + key + "=" + paramValue);
                } else {
                    url.append("&" + key + "=" + paramValue);
                }
                //}
            }
        }
        return url.toString();
    }

    /**
     * 得到一个完整URL（包含参数）
     * @param request
     * @return
     */
    public static String getFullUrl(HttpServletRequest request) {
        return getFullUrlIsTrue(request,true);
    }

    /**
     * 得到一个完整请求参数
     * @param request
     * @return
     */
    public static String getFullParam(HttpServletRequest request) {
        return getFullUrlIsTrue(request,false);
    }
}
