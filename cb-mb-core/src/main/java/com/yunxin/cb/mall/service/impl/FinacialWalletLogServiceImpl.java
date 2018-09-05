//package com.yunxin.cb.mall.service.impl;
//
//import com.yunxin.cb.mall.entity.FinancialWalletLog;
//import com.yunxin.cb.mall.mapper.FinacialWalletLogMapper;
//import com.yunxin.cb.mall.service.FinacialWalletLogService;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
//@Service
//public class FinacialWalletLogServiceImpl implements FinacialWalletLogService {
//
//    @Resource
//    private FinacialWalletLogMapper finacialWalletLogMapper;
//    private static final Log log = LogFactory.getLog(FinacialWalletLogServiceImpl.class);
//    /**
//     * 添加钱包日志信息
//     * @author      likang
//     * @param financialWalletLog
//     * @return      com.yunxin.cb.mall.entity.FinancialWalletLog
//     * @exception
//     * @date        2018/8/7 11:59
//     */
//    @Override
//    public FinancialWalletLog addFinacialWalletLog(FinancialWalletLog financialWalletLog){
//        log.info("add:"+ financialWalletLog);
//        if(finacialWalletLogMapper.selectByCustomerIdAndVersion(financialWalletLog.getCustomerId(), financialWalletLog.getVersion())==null){
//            finacialWalletLogMapper.insert(financialWalletLog);
//        }
//        return financialWalletLog;
//    }
//
//    /**
//     * 查询钱包日志信息
//     * @author      likang
//     * @param customerId
//    * @param version
//     * @return      com.yunxin.cb.mall.entity.FinancialWalletLog
//     * @exception
//     * @date        2018/8/7 15:05
//     */
//    public FinancialWalletLog getFinacialWalletLog(int customerId, int version){
//        return finacialWalletLogMapper.selectByCustomerIdAndVersion(customerId,version);
//    }
//
//}
