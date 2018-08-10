package com.yunxin.cb.mall.web.scheduler;

import com.yunxin.cb.mall.service.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 订单定时收货
 */
@Component
public class OrderReceivedTask {

    private static final Logger logger = LoggerFactory.getLogger(OrderReceivedTask.class);

    @Resource
    private IOrderService orderService;

    //@Scheduled(cron = "0 0/1 * * * ?")
    public void orderReceived(){
        logger.info("orderReceived start");
        orderService.confirmReceivedOrders();
        logger.info("orderReceived end");
    }
}
