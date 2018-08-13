package com.yunxin.cb.mall.web.scheduler;

import com.yunxin.cb.mall.service.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 订单定时完成（如果没评价默认好评）
 */
@Component
public class OrderCompleteTask {

    private static final Logger logger = LoggerFactory.getLogger(OrderCompleteTask.class);

    @Resource
    private IOrderService orderService;

    //@Scheduled(cron = "0 0 0 * * ?")
    public void orderComplete(){
        logger.info("orderComplete start");
        orderService.completedOrders();
        logger.info("orderComplete end");
    }
}
