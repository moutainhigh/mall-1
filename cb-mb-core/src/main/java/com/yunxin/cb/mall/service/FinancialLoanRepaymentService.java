package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinancialLoanRepayment;
import com.yunxin.cb.mall.vo.FinancialLoanRepaymentVO;

import java.math.BigDecimal;
import java.util.List;

public interface FinancialLoanRepaymentService {

    /**
     * @Author chenpeng
     * @Description 自动还款(返利和报账还款)
     * @Date 2018/9/5 19:05
     **/
    void autoRepay(BigDecimal repayAmount, Integer customerId, FinancialLoanRepayment.Type type);

    /**
     * @Author chenpeng
     * @Description 手动还款（负债）
     * @Date 2018/9/5 19:05 
     **/
    void repay(BigDecimal repayAmount, Integer customerId);

    List<FinancialLoanRepaymentVO> getByCustomerId(int customerId);

    FinancialLoanRepaymentVO getById(int repaymentId);
}
