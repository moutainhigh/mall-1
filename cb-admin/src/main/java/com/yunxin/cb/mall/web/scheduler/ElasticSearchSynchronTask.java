package com.yunxin.cb.mall.web.scheduler;

import com.yunxin.cb.mall.service.ICommodityService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ElasticSearchSynchronTask {

    @Resource
    private ICommodityService commodityService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void synchronCommoidy(){
        commodityService.syncESCommodity();
    }
}
