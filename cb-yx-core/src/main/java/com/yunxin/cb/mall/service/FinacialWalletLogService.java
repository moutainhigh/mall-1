package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinacialWalletLog;

public interface FinacialWalletLogService {

       /**
        * 添加钱包日志信息
        * @author      likang
        * @param finacialWalletLog
        * @return      com.yunxin.cb.mall.entity.FinacialWalletLog
        * @exception
        * @date        2018/8/7 11:59
        */
       public FinacialWalletLog addFinacialWalletLog(FinacialWalletLog finacialWalletLog);


       /**
        * 查询钱包日志信息
        * @author      likang
        * @param customerId
       * @param version
        * @return      com.yunxin.cb.mall.entity.FinacialWalletLog
        * @exception
        * @date        2018/8/7 15:05
        */
       public FinacialWalletLog getFinacialWalletLog(int customerId,int version);
}
