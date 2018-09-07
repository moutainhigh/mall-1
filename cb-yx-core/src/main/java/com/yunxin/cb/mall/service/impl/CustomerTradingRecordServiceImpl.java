package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.CustomerTradingRecord;
import com.yunxin.cb.mall.mapper.CustomerTradingRecordMapper;
import com.yunxin.cb.mall.service.CustomerTradingRecordService;
import com.yunxin.cb.mall.vo.CustomerTradingRecordVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerTradingRecordServiceImpl implements CustomerTradingRecordService {

    @Resource
    private CustomerTradingRecordMapper customerTradingRecordMapper;

    /**
     * 获取交易记录
     * @author      likang
     * @param customerId
     * @return      java.util.List<com.yunxin.cb.mall.entity.CustomerTradingRecord>
     * @exception
     * @date        2018/8/2 14:19
     */
    @Override
    public List<CustomerTradingRecordVO> getCustomerTradingRecordByCustomerId(Integer customerId){
        List<CustomerTradingRecordVO> listVO = new ArrayList<>();
        List<CustomerTradingRecord> list = customerTradingRecordMapper.selectByCustomerId(customerId);
        list.stream().forEach(p ->{
            CustomerTradingRecordVO customerTradingRecordVO = new CustomerTradingRecordVO();
            BeanUtils.copyProperties(p, customerTradingRecordVO);
            listVO.add(customerTradingRecordVO);
        });
        return listVO;
    }

    /**
     * 获取交易的详情
     * @author      likang
     * @param tradeRecordId
    * @param customerId
     * @return      com.yunxin.cb.mall.entity.CustomerTradingRecord
     * @exception
     * @date        2018/8/2 14:22
     */
    @Override
    public CustomerTradingRecord getCustomerTradingRecordByCustomerIdAndPrimaryKey(Integer tradeRecordId,Integer customerId){
       return customerTradingRecordMapper.selectByPrimaryKeyAndCustomerId(tradeRecordId,customerId);
    }
}
