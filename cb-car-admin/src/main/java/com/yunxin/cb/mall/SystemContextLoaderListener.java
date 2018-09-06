/**
 *
 */
package com.yunxin.cb.mall;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * @author 龚磊
 */
public class SystemContextLoaderListener extends ContextLoaderListener {

    /**
     * spring上下文环境
     */
    private static ApplicationContext applicationContext;

    /**
     * servlet上下文环境
     */
    private static ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent event) {


    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        if (applicationContext != null) {

        }

        super.contextDestroyed(event);
    }


    /**
     * @return the applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @return the servletContext
     */
    public static ServletContext getServletContext() {
        return servletContext;
    }

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

}
