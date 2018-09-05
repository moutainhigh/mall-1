package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinancialLoanRepayment;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

public interface IFinancialLoanRepaymentService {

    /**
     * 功能描述:分页查询还款记录
     *
     * @param
     * @return
     * @auther yangzhen
     * @date 2018/9/4 15:12
     */
    Page<FinancialLoanRepayment> pageServiceFinancialLoanRepayment(PageSpecification<FinancialLoanRepayment> pageSpecification,Integer loanRepayMentType);

}
