package com.yunxin.cb.config;


import com.yunxin.cb.jwt.JwtUtil;
import com.yunxin.cb.jwt.Token;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.yunxin.cb.jwt.JwtUtil.HEADER_STRING;
import static com.yunxin.cb.jwt.JwtUtil.TOKEN_PREFIX;


/**
 * @author moxin E-mail: moxin@microlinktech.net
 * @version 17/11/28 下午3:00
 */
public class RestTokenFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        String authHeader = request.getHeader(HEADER_STRING);
        if ((authHeader == null) ||
                !authHeader.startsWith(TOKEN_PREFIX)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try {
            authHeader = authHeader.replace(TOKEN_PREFIX, "");
            Token token = JwtUtil.getToken(authHeader);
            if(true) {
//            if ((request.getServletPath().startsWith("/rest/school/") && token.getAccountType() == AccountType.TEACHER)
//                    || request.getServletPath().startsWith("/rest/parent/") && token.getAccountType() == AccountType.PARENT) {
//                request.getSession().setAttribute(BellmConstants.ACCOUNT_ID, token.getAccountId());
//                request.getSession().setAttribute(BellmConstants.STAGING_ID, token.getStagingId());
//                request.getSession().setAttribute(BellmConstants.ZONE_ID, token.getZoneId());
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } catch (final Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        chain.doFilter(req, res);
    }
}
