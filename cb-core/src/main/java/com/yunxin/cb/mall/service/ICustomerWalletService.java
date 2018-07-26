package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.CustomerWallet;
import com.yunxin.cb.mall.entity.meta.BusinessType;

/**
 * @author wangteng
 */
public interface ICustomerWalletService {
    /**
     * 获取钱包
     * @param customerId
     * @return
     */
    public CustomerWallet findCustomerWallet(int customerId);

    /**
     * 更新钱包
     * @param customerId
     * @param ratios
     * @param remark
     * @param businessType
     * @return
     */
    public CustomerWallet updateCustomerWallet(int customerId,Double ratios,String remark,BusinessType businessType);
//
//    /**
//     * 更新授信额度
//     * @param customerWallet
//     * @return
//     */
//    public CustomerWallet updateExpectedReturnAmount(CustomerWallet customerWallet);




}
