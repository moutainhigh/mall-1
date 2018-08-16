package com.yunxin.cb.mall.web.scheduler;

import com.yunxin.cb.insurance.service.IInsuranceOrderCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * wangteng邮件提醒导入保险合同号
 */
@Component
public class InsuranceOrderCodeTask {
    private static final Logger logger = LoggerFactory.getLogger(InsuranceOrderCodeTask.class);

    @Resource
    private IInsuranceOrderCodeService iInsuranceOrderCodeService;
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void insuranceOrderCode(){
        logger.info("insuranceOrderCode start");
        try {
            iInsuranceOrderCodeService.Reminding();
        }catch (Exception e){
            logger.error("insuranceOrderCode failed",e);
        }

        logger.info("insuranceOrderCode end");
    }
}
