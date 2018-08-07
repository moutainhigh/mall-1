package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinacialWallet;

public interface FinacialWalletService {

    /**
     * 添加钱包信息
     * @author      likang
     * @param finaciaWallet
     * @return      com.yunxin.cb.mall.entity.FinacialWallet
     * @exception
     * @date        2018/8/7 11:54
     */
    public FinacialWallet addFinaciaWallet(FinacialWallet finaciaWallet);


    /**
     * 获取用户钱包信息
     * @author      likang
     * @param customerId
     * @return      com.yunxin.cb.mall.entity.FinacialWallet
     * @exception
     * @date        2018/8/7 11:55
     */
    public FinacialWallet getFinacialWalletByCustomerId(int customerId);


    /**
     * 更新用户钱包信息
     * @author      likang
     * @param finaciaWallet
     * @return      com.yunxin.cb.mall.entity.FinacialWallet
     * @exception
     * @date        2018/8/7 11:56
     */
    public FinacialWallet updateFinacialWallet(FinacialWallet finaciaWallet);

}
