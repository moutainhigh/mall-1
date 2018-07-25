package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.CustomerTradingRecordDao;
import com.yunxin.cb.mall.entity.CustomerTradingRecord;
import com.yunxin.cb.mall.service.ICustomerTradingRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author wangteng
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CustomerTradingRecordService implements ICustomerTradingRecordService {
    @Resource
    private CustomerTradingRecordDao customerTradingRecordDao;
    @Override
    public void addCustomerTradingRecord(CustomerTradingRecord customerTradingRecord) {
        customerTradingRecordDao.save(customerTradingRecord);
    }
}
