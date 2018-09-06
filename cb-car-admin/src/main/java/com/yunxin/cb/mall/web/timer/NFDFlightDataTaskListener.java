package com.yunxin.cb.mall.web.timer;

import javax.servlet.ServletContextEvent;

/**
 * Created by x001393 on 2014/11/20.
 */
public class NFDFlightDataTaskListener  {

    public void contextInitialized(ServletContextEvent event) {
        new TimerManager();
    }

    public void contextDestroyed(ServletContextEvent event) {
    }

}