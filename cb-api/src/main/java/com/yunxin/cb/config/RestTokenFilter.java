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
            request.getSession().setAttribute("customerId", token.getAccountId());
            request.getSession().setAttribute("mobile", token.getMobile());
        } catch (final Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        chain.doFilter(req, res);
    }
}
