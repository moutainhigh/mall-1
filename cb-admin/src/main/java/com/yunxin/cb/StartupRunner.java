package com.yunxin.cb;

import com.yunxin.cb.console.service.imp.SecurityService;
import com.yunxin.cb.search.restful.RestfulFactory;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.cb.security.SecurityProvider;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: gonglei
 * @Date: 2018/2/7 上午10:53
 * @Description:
 **/
@Component
@Order(value = 1)
public class StartupRunner implements CommandLineRunner {

    protected static Logger logger = Logger.getLogger(StartupRunner.class);

    @Value("${application.searchBaseUrl}")
    private String searchBaseUrl;

    @Resource
    private SecurityService securityService;


    @Override
    public void run(String... strings) throws Exception {
        //初始化后台管理员账号
        initAdminAccountIfNecessary();
        //全局加载资源表
        loadPrivileges();

        RestfulFactory.getInstance().init(searchBaseUrl);
        //后台图片存放目录
//        servletContext.setAttribute("PIC_PATH", servletContext.getInitParameter(SecurityConstants.PIC_PATH));
    }

    /**
     * 如有必要，则初始化默认账号
     */
    private void initAdminAccountIfNecessary() {
        securityService.initAdminAccount();
    }

    /**
     * 全局加载资源表
     */
    private void loadPrivileges() throws Exception {
        ((SecurityProvider) securityService).loadPrivileges();
    }

}