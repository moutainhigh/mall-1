package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.CustomerTradingRecord;

/**
 * @author wangteng
 */
public interface ICustomerTradingRecordService {
    /**
     *  新增交易记录
     * @param customerTradingRecord
     */
      public  void addCustomerTradingRecord(CustomerTradingRecord customerTradingRecord);
}
