package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.mapper.CustomerMapper;
import com.yunxin.cb.mall.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerMapper customerMapper;

    public Customer getCustomerById(int customerId) {
        return customerMapper.selectByPrimaryKey(customerId);
    }

    @Override
    public int updatePaymentPasswordByCustomerId(int customerId, String paymentPassword) {
        return customerMapper.updatePaymentPasswordByCustomerId(customerId, paymentPassword);
    }

    @Override
    public int updateAuthFlagByCustomerId(Integer customerId, Integer authFlag) {
        return customerMapper.updateAuthFlagByCustomerId(customerId,authFlag);
    }
}
