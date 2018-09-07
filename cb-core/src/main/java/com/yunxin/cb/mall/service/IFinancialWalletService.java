package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinancialWallet;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

public interface IFinancialWalletService {

    /**
     * @title: 分页查询负债记录
     * @param: [serviceRuleQuery]
     * @auther: pengcong
     * @date: 2018/8/10 14:46
     */
    public Page<FinancialWallet> pageServiceFinancialWallet(PageSpecification<FinancialWallet> pageSpecification);
}
