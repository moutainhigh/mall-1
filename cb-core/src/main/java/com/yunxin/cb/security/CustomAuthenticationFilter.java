package com.yunxin.cb.security;

import com.yunxin.core.util.LogicUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.TextEscapeUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Aidy_He
 */
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationFilter.class);

    private boolean postOnly = true;
    public static final String VALIDATE_CODE = "validateCode";
    private boolean allowEmptyValidateCode = false;
    public static final String SPRING_SECURITY_LAST_USERNAME_KEY = "spring_security_last_userName";

    private SecurityHolder securityHolder;

    public void setSecurityHolder(SecurityHolder securityHolder) {
        this.securityHolder = securityHolder;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (logger.isDebugEnabled()) {
            logger.debug("attemptAuthentication(HttpServletRequest request, HttpServletResponse response) - start");
        }
        if (postOnly && !request.getMethod().equals("POST")) {
            if (logger.isDebugEnabled()) {
                logger.debug("Authentication method not supported: " + request.getMethod());
            }
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String userName = obtainUsername(request);
        String password = obtainPassword(request);
        if (userName == null) {
            userName = "";
        }
        if (password == null) {
            password = "";
        }
        userName = userName.trim();

        UserDetails user = securityHolder.getUserDetailsByName(userName);

        if (user == null || !user.getPassword().equals(password)) {
            if (logger.isDebugEnabled()) {
                logger.debug("用户名或者密码错误或用户被禁用！");
            }
            throw new AuthenticationServiceException("The userName or password is wrong！");
        } else if (!user.isEnabled()) {
            throw new AuthenticationServiceException("The user has been disabled, please contact the administrator");
        }

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userName,
                password);

        HttpSession session = request.getSession(false);

        if (session != null || getAllowSessionCreation()) {
            if (logger.isDebugEnabled()) {
                logger.debug("Record last login name");
            }
            request.getSession().setAttribute(SPRING_SECURITY_LAST_USERNAME_KEY,
                    TextEscapeUtils.escapeEntities(userName));
        }

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        // check validate code
        String target = request.getParameter("target");
        if (!"pda".equals(target) && !getIsAllowEmptyValidateCode()) {
//			checkValidateCode(request);
        }
        if (logger.isDebugEnabled()) {
            logger.debug("attemptAuthentication(HttpServletRequest request, HttpServletResponse response) - end");
        }
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /***
     * 比较session中的验证码和用户输入的验证码是否相等
     * @param request
     */
    protected void checkValidateCode(HttpServletRequest request) throws AuthenticationServiceException {
        HttpSession session = request.getSession();

        String sessionValidateCode = obtainSessionValidateCode(session);
        //让上一次的验证码失效  
        //session.setAttribute(SecurityConstants.LOGIN_IMAGE_VALID, null);
        String validateCodeParameter = obtainValidateCodeParameter(request);
        if (LogicUtils.isNullOrEmpty(validateCodeParameter) || !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
            throw new AuthenticationServiceException("验证码错误！");
        }
    }

    private String obtainValidateCodeParameter(HttpServletRequest request) {
        Object obj = request.getParameter(VALIDATE_CODE);
        if (logger.isDebugEnabled()) {
            logger.debug("VALIDATE_CODE in request value is" + obj);
        }
        return null == obj ? "" : obj.toString();
    }

    protected String obtainSessionValidateCode(HttpSession session) {
        Object obj = session.getAttribute("");
        if (logger.isDebugEnabled()) {
            logger.debug("LOGIN_IMAGE_VALID in session value is" + obj);
        }
        return null == obj ? "" : obj.toString();
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        Object obj = request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);
        return null == obj ? "" : obj.toString();
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        Object obj = request.getParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);
        return null == obj ? "" : obj.toString();
    }

    public boolean getIsPostOnly() {
        return postOnly;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public boolean getIsAllowEmptyValidateCode() {
        return allowEmptyValidateCode;
    }

    public void setAllowEmptyValidateCode(boolean allowEmptyValidateCode) {
        this.allowEmptyValidateCode = allowEmptyValidateCode;
    }
}
