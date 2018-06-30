package com.yunxin.core.web.tags;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import java.util.Locale;


/**
 * Created by gonglei on 2015/3/18.
 */
public class GeneralFunctions {

    private static MessageSource messageSource;

    public static String message(String message,PageContext pageContext){
        return messages(message, null, pageContext);
    }

    public static String messages(String message,Object[] objects,PageContext pageContext){
        HttpServletRequest request= (HttpServletRequest) pageContext.getRequest();
        if (messageSource==null){
            ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
            messageSource= (MessageSource) ac.getBean("messageSource");
        }
        Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
        String msg =messageSource.getMessage(message, objects, locale);
        return msg;
    }
}
