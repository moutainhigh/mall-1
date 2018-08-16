package com.yunxin.cb.security.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunxin.cb.jwt.JwtUtil;
import com.yunxin.cb.jwt.Token;
import com.yunxin.cb.meta.Result;
import com.yunxin.cb.orm.CustomerContextHolder;
import com.yunxin.cb.rest.BaseResource;
import com.yunxin.cb.security.annotation.IgnoreAuthentication;
import com.yunxin.cb.vo.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;

import static com.yunxin.cb.jwt.JwtUtil.HEADER_STRING;
import static com.yunxin.cb.jwt.JwtUtil.TOKEN_PREFIX;


/**
 * Created by paul on 2017-6-24.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //用户访问日志
        logger.info("AppAccess info: clientIp=" + getIpAddr(request) + " access_url=" + request.getRequestURI() + " attime=" + new Date().toString()
                + " Bymethod= " + request.getMethod() + ",user-Agent='" + request.getHeader("user-Agent") + "'");
        logger.info("请求参数：" + BaseResource.getFullParam(request));//打印请求参数  add by lxc  2018-08-16
        if (!request.getMethod().equals("OPTIONS")) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            IgnoreAuthentication ignoreAuthentication = handlerMethod.getBeanType().getAnnotation(IgnoreAuthentication.class);

            if (ignoreAuthentication == null)
                ignoreAuthentication = handlerMethod.getMethod().getAnnotation(IgnoreAuthentication.class);

            String authHeader = request.getHeader(HEADER_STRING);
            if (ignoreAuthentication != null){
                //如果用户有token，就保存
                if (authHeader != null){
                    authHeader = authHeader.replace(TOKEN_PREFIX, "");
                    Token token = JwtUtil.getToken(authHeader);
                    int customerId = token.getAccountId();
                    CustomerContextHolder.setCustomerId(customerId);
                }
                // don't need token
                return true;
            }


            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            if ((authHeader == null) ||
                    !authHeader.startsWith(TOKEN_PREFIX)) {
                ObjectMapper mapper = new ObjectMapper();
                String mapJakcson = mapper.writeValueAsString(new ResponseResult(Result.NOT_LOGIN, "缺少token信息"));
                PrintWriter writer = response.getWriter();
                writer.print(mapJakcson);
                return false;
            }

            try {
                authHeader = authHeader.replace(TOKEN_PREFIX, "");
                Token token = JwtUtil.getToken(authHeader);

                int customerId = token.getAccountId();
//                Customer customer =customerService.getCustomerById(customerId);
//                if (customer == null){
//                    PrintWriter writer = response.getWriter();
//                    ObjectMapper mapper = new ObjectMapper();
//                    String mapJakcson = mapper.writeValueAsString(new ResponseResult(Result.NOT_LOGIN, "token信息错误,token用户信息与系统用户信息不对应"));
//                    writer.print(mapJakcson);
//                    return false;
//                }

                CustomerContextHolder.setCustomerId(customerId);
                return true;
            } catch (final Exception e) {
                logger.error("鉴权失败", e);
                ObjectMapper mapper = new ObjectMapper();
                String mapJakcson = mapper.writeValueAsString(new ResponseResult(Result.NOT_LOGIN, "token信息错误，解析token出错或者token已经过期"));
                PrintWriter writer = response.getWriter();
                writer.print(mapJakcson);
                return false;
            }
        }
        return true;
    }


    public String getIpAddr(HttpServletRequest request) {

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
