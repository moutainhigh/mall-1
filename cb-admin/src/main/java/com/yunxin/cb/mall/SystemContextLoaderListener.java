/**
 *
 */
package com.yunxin.cb.mall;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yunxin.cb.config.ProvinceConfig;
import com.yunxin.cb.security.Resource;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.util.ProvinceHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

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
        servletContext = event.getServletContext();
        super.contextInitialized(event);
        applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);

        //将省市区文件中的数据存储至缓存中
        ProvinceHelper.init(servletContext);

        ProvinceConfig provinceConfig= (ProvinceConfig) applicationContext.getBean("provinceConfig");
        try {
            provinceConfig.init(servletContext);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        String systemConfigFilePath = servletContext.getInitParameter("systemConfig");
//        GlobalConfig.loadConfig(servletContext.getRealPath(systemConfigFilePath));

        //后台图片存放目录
        servletContext.setAttribute("PIC_PATH", servletContext.getInitParameter(SecurityConstants.PIC_PATH));

        //加载资源菜单

        List<Resource> resources = loadResources();
        servletContext.setAttribute(SecurityConstants.RESOURCES, resources);

    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        if (applicationContext != null) {

        }

        super.contextDestroyed(event);
    }

    /**
     * 从资源XML文件中获取所有资源信息
     *
     * @return
     */
    private List<Resource> loadResources() {
        List<Resource> resources = null;
        Gson gson = new Gson();//new一个Gson对象
        try {
            resources = gson.fromJson(new InputStreamReader(this.getClass().getResourceAsStream("/resources.json"), "UTF-8"), new TypeToken<List<Resource>>(){}.getType());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return resources;
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
