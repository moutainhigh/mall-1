package com.yunxin.cb;


import com.yunxin.cb.mall.restful.RestfulFactory;
import com.yunxin.cb.pay.entity.AliPayBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;



/**
 * @Author: gonglei
 * @Date: 2018/2/7 上午10:53
 * @Description:
 **/
@Component
@Order(value = 1)
public class StartupRunner implements CommandLineRunner {

    protected static Logger logger = LoggerFactory.getLogger(StartupRunner.class);

    @Value("${application.searchBaseUrl}")
    private String searchBaseUrl;

    @Autowired
    private AliPayBean aliPayBean;


    @Override
    public void run(String... strings) throws Exception {
        RestfulFactory.getInstance().init(searchBaseUrl);
        aliPayBean.init();
    }


}