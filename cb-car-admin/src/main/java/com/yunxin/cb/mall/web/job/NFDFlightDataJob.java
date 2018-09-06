package com.yunxin.cb.mall.web.job;

import com.yunxin.cb.mall.service.IOrderService;
import com.yunxin.core.util.LogicUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by w001499 on 2014/11/21.
 */
public class NFDFlightDataJob  {
    private static final Logger log = LoggerFactory.getLogger(NFDFlightDataJob.class);
    private IOrderService orderFormService;

    public void execute(){
        try {
            log.info("Quarz Job NFDFlight...");
            if(LogicUtils.isNull(orderFormService)){
//                orderFormService = (IOrderFormService) SpringContextUtil.getBean("orderFormService");
//                orderFormService.changOrderFormStatus();
            }
        } catch (Exception e) {
            log.info("-------------定时检查订单是否过时,出现异常！--------------");
        }
    }
}
