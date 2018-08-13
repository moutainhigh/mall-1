package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinacialWallet;
import com.yunxin.cb.mall.entity.meta.WithdrawType;
import com.yunxin.cb.search.vo.ResponseResult;

import java.math.BigDecimal;

public interface IFinaciaWalletService {

    public FinacialWallet addFinaciaWallet(FinacialWallet fw, int type, BigDecimal amount);

    public FinacialWallet updateFinacialWallet(FinacialWallet fw);

    /**
     * @title: 处理用户返现接口
     * @param: [customerId, money, type, remark]
     * @return: com.yunxin.cb.search.vo.ResponseResult
     * @auther: eleven
     * @date: 2018/8/13 11:27
     */
    public ResponseResult processCustomerMoney(Integer customerId, BigDecimal money, WithdrawType type, String remark) throws RuntimeException;
    public FinacialWallet getFinacialWalletByCustomerId(int customerId);
}
