package com.yunxin.cb.mall.web.scheduler;

import com.yunxin.cb.mall.service.ICommodityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ElasticSearchSynchronTask {

    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchSynchronTask.class);

    @Resource
    private ICommodityService commodityService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void synchronCommoidy(){
        logger.info("synchronCommoidy start");
        commodityService.syncESCommodity();
        logger.info("synchronCommoidy end");
    }
}
