package com.yunxin.cb.rest;

import com.yunxin.cb.common.utils.CachedUtil;
import com.yunxin.cb.orm.CustomerContextHolder;
import com.yunxin.cb.util.VerificationCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    /**
     * 校验验证码
     *
     */
    protected String verificationCode(String mobile, String code) {
        //校验验证码
        VerificationCode verificationCode = (VerificationCode) CachedUtil.getInstance().getContext(mobile);
        //验证码不存在
        if (verificationCode == null){
            return "验证码不存在";
        }
        //验证码超过5分钟，失效
        if ((System.currentTimeMillis() - verificationCode.getSendTime()) > 300000) {
            return "验证码失效";
        }
        //验证码错误
        if (!verificationCode.getCode().equals(code)) {
            return "验证码错误";
        }
        return null;
    }
}
