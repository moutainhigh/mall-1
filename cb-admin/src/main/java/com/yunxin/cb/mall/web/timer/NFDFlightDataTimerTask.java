package com.yunxin.cb.mall.web.timer;



import com.yunxin.cb.mall.service.IOrderService;
import com.yunxin.core.util.LogicUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by x001393 on 2014/11/20.
 */
public class NFDFlightDataTimerTask  {

    private static final Logger log = LoggerFactory.getLogger(NFDFlightDataTimerTask.class);

    private IOrderService orderFormService;


    public void run() {
        try {
        if(LogicUtils.isNull(orderFormService)){
            //orderFormService = (IOrderFormService) SpringContextUtil.getBean("orderFormService");
            orderFormService.cancelTimeOutOrders();
        }
    } catch (Exception e) {
        log.info("-------------定时检查订单是否过时,出现异常！--------------");
    }
}
}
