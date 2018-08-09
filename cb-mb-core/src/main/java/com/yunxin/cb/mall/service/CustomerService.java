package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Customer;

public interface CustomerService {

    Customer getCustomerById(int customerId);

    /**
     * 根据用户id修改交易密码
     * @param customerId
     * @param paymentPassword
     * @return
     */
    int updatePaymentPasswordByCustomerId(int customerId, String paymentPassword);

}
