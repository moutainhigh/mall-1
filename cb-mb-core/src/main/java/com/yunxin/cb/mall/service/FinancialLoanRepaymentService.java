package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinancialLoanRepayment;

import java.math.BigDecimal;

public interface FinancialLoanRepaymentService {

    /**
     * @Author chenpeng
     * @Param repayAmount 自动还款金额
     * @Param customerId 用户id
     * @Param FinancialLoanRepayment.Type 还款类型
     * @Param transactionNo 单号(保险单号或订单号或报账单)
     * @Description 自动还款(返利和报账还款)
     * @Return 还款后剩余金额
     * @Date 2018/9/5 19:05
     **/
    BigDecimal autoRepay(BigDecimal repayAmount, Integer customerId, FinancialLoanRepayment.Type type, String transactionNo);


    /**
     * @Author chenpeng
     * @Description 手动还款（负债）
     * @Return 还款后剩余金额
     * @Date 2018/9/5 19:05 
     **/
    BigDecimal repay(BigDecimal repayAmount, Integer customerId);

}
