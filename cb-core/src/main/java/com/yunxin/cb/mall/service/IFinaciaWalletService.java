package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinacialWallet;

import java.math.BigDecimal;

public interface IFinaciaWalletService {

    public FinacialWallet addFinaciaWallet(FinacialWallet fw, int type, BigDecimal amount);

    public FinacialWallet updateFinacialWallet(FinacialWallet fw);
    public FinacialWallet getFinacialWalletByCustomerId(int customerId);
}
