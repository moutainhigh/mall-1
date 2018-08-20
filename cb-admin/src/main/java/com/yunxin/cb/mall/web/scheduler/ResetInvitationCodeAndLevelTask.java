package com.yunxin.cb.mall.web.scheduler;

import com.yunxin.cb.mall.service.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 重置邀请码与等级编码
 */
@Component
public class ResetInvitationCodeAndLevelTask {

    private static final Logger logger = LoggerFactory.getLogger(ResetInvitationCodeAndLevelTask.class);

    @Resource
    private ICustomerService iCustomerService;

    @Scheduled(cron = "0 0 0/1 * * ?")
    public void resetInvitationCodeAndLevel(){
        logger.info("resetInvitationCodeAndLevel start");
        //重置邀请码
        iCustomerService.resetInvitationCode();
        //重置等级编码
        iCustomerService.resetLevelCodeCode();
        logger.info("resetInvitationCodeAndLevel end");
    }
}
