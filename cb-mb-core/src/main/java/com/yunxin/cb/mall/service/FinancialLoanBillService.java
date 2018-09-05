package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinancialLoanBill;
import com.yunxin.cb.mall.vo.FinancialLoanBillVO;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;

import java.util.List;

public interface FinancialLoanBillService {

    FinancialLoanBillVO addFinacialLiabilitiesBill(FinancialLoanBillVO vo);

    List<FinancialLoanBillVO> getFinacialLiabilitiesBillByCustomerId(int customerId);

    public PageFinder<FinancialLoanBill> page(Query q);
}
