package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinancialLoanRepayment;
import com.yunxin.cb.rb.entity.meta.LoanRepaymentType;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public interface IFinancialLoanRepaymentService {

    /**
     * 功能描述:分页查询还款记录
     *
     * @param
     * @return
     * @auther yangzhen
     * @date 2018/9/4 15:12
     */
    Page<FinancialLoanRepayment> pageServiceFinancialLoanRepayment(PageSpecification<FinancialLoanRepayment> pageSpecification, Integer loanRepayMentType);

    /**
     * @Author chenpeng
     * @Param repayAmount 自动还款金额
     * @Param customerId 用户id
     * @Param LoanRepaymentType 还款类型
     * @Param transactionNo 单号(保险单号或订单号或报账单)
     * @Description 自动还款(返利和报账还款)
     * @Return 还款后剩余金额
     * @Date 2018/9/5 19:05
     **/
    BigDecimal autoRepay(BigDecimal repayAmount, Integer customerId, LoanRepaymentType type, String transactionNo);

}
