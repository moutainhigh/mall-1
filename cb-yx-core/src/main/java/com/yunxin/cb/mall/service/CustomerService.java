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

    /**
     * @title: 根据用户id修改认证状态
     * @param: [customerId, authFlag]
     * @return: int
     * @auther: eleven
     * @date: 2018/8/9 14:34
     */
    int updateAuthFlagByCustomerId(Integer customerId,Integer authFlag);

}
