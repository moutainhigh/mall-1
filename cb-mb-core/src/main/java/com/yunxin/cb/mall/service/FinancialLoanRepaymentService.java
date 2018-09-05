package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.vo.FinancialLoanRepaymentVO;

import java.math.BigDecimal;
import java.util.List;

public interface FinancialLoanRepaymentService {

    void add(BigDecimal repayAmount,int customerId) throws Exception;

    List<FinancialLoanRepaymentVO> getByCustomerId(int customerId);

    FinancialLoanRepaymentVO getById(int repaymentId);
}
