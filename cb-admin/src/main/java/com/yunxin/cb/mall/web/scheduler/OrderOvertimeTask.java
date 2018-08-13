package com.yunxin.cb.mall.web.scheduler;

import com.yunxin.cb.mall.service.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 订单超时
 */
@Component
public class OrderOvertimeTask {

    private static final Logger logger = LoggerFactory.getLogger(OrderOvertimeTask.class);

    @Resource
    private IOrderService orderService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void orderOvertime(){
        logger.info("orderOvertime start");
        orderService.cancelTimeOutOrders();
        logger.info("orderOvertime end");
    }
}
