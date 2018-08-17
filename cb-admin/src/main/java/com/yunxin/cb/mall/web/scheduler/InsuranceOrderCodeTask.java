package com.yunxin.cb.mall.web.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * wangteng邮件提醒导入保险合同号
 */
@Component
public class InsuranceOrderCodeTask {
    private static final Logger logger = LoggerFactory.getLogger(InsuranceOrderCodeTask.class);

//    @Resource
//    private IInsuranceOrderCodeService iInsuranceOrderCodeService;
//    @Scheduled(cron = "0 0 0/1 * * ?")
//    public void insuranceOrderCode(){
//        logger.info("insuranceOrderCode start");
//        try {
//            iInsuranceOrderCodeService.Reminding();
//        }catch (Exception e){
//            logger.error("insuranceOrderCode failed",e);
//        }
//
//        logger.info("insuranceOrderCode end");
//    }
}
