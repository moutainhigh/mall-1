package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinancialWallet;
import com.yunxin.cb.mall.entity.meta.WithdrawType;
import com.yunxin.cb.search.vo.ResponseResult;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public interface IFinancialWalletService {

    /**
     * @title: 分页查询负债记录
     * @param: [serviceRuleQuery]
     * @auther: pengcong
     * @date: 2018/8/10 14:46
     */
    public Page<FinancialWallet> pageServiceFinancialWallet(PageSpecification<FinancialWallet> pageSpecification);

    public FinancialWallet addFinaciaWallet(FinancialWallet fw, int type, BigDecimal amount);

    public FinancialWallet updateFinacialWallet(FinancialWallet fw);

    /**
     * @title: 处理用户返现接口
     * @param: [customerId, money, type, remark]
     * @return: com.yunxin.cb.search.vo.ResponseResult
     * @auther: eleven
     * @date: 2018/8/13 11:27
     */
    public ResponseResult processCustomerMoney(Integer customerId, BigDecimal money, WithdrawType type, String remark) throws RuntimeException;
    public FinancialWallet getFinacialWalletByCustomerId(int customerId);
}
