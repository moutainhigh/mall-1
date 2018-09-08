package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinancialLoanBill;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

public interface IFinancialLoanBillService {

    /**
     * @title: 分页查询个人还款记录
     * @param: [serviceRuleQuery]
     * @auther: pengcong
     * @date: 2018/8/10 14:46
     */
    public Page<FinancialLoanBill> pageServiceFinancialLoanBill(PageSpecification<FinancialLoanBill> pageSpecification,int customerId);
}
