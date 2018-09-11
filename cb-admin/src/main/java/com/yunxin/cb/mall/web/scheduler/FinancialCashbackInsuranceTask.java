package com.yunxin.cb.mall.web.scheduler;

import com.yunxin.cb.mall.entity.FinancialCashbackInsurance;
import com.yunxin.cb.mall.service.IFinancialCashbackInsuranceService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 保险返利
 */
@Component
public class FinancialCashbackInsuranceTask {

    private static final Logger logger = LoggerFactory.getLogger(FinancialCashbackInsuranceTask.class);

    @Resource
    private IFinancialCashbackInsuranceService financialCashbackInsurance;

    /**
     * @Author chenpeng
     * @Description 每年的1月1日0时0分触发 保险返利
     * @Date 2018/9/11 17:12 
     **/
    @Scheduled(cron = "0 0 0 1 1 ?")
    public void processInsuranceCashback() {

        logger.info("processInsuranceCashback start");

        // 1.获取今年要返利的记录
        List<FinancialCashbackInsurance> cashbackInsurances = financialCashbackInsurance.getByProcessing();
        if (CollectionUtils.isEmpty(cashbackInsurances)) {
            logger.info("processInsuranceCashback end");
            return;
        }

        for (FinancialCashbackInsurance cashbackInsurance : cashbackInsurances) {
            try {
                logger.info("cashbackInsurance={} processing ", cashbackInsurance.getInsuranceNo());
                financialCashbackInsurance.processInsuranceCashback(cashbackInsurance);
            } catch (Exception e) {
                logger.info("cashbackInsurance=" + cashbackInsurance.getInsuranceNo() + " processing fail", e);
            }
        }

        logger.info("processInsuranceCashback end");
    }
}
