package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.CustomerTradingRecord;
import com.yunxin.cb.mall.vo.CustomerTradingRecordVO;

import java.util.List;

public interface CustomerTradingRecordService {

       List<CustomerTradingRecordVO> getCustomerTradingRecordByCustomerId(Integer customerId);

       CustomerTradingRecord getCustomerTradingRecordByCustomerIdAndPrimaryKey(Integer tradeRecordId,Integer customerId);

}
