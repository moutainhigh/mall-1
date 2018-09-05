package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinancialWalletLog;

public interface FinacialWalletLogService {

       /**
        * 添加钱包日志信息
        * @author      likang
        * @param financialWalletLog
        * @return      com.yunxin.cb.mall.entity.FinancialWalletLog
        * @exception
        * @date        2018/8/7 11:59
        */
       public FinancialWalletLog addFinacialWalletLog(FinancialWalletLog financialWalletLog);


       /**
        * 查询钱包日志信息
        * @author      likang
        * @param customerId
       * @param version
        * @return      com.yunxin.cb.mall.entity.FinancialWalletLog
        * @exception
        * @date        2018/8/7 15:05
        */
       public FinancialWalletLog getFinacialWalletLog(int customerId, int version);
}
