package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinancialLoanBill;
import com.yunxin.cb.util.page.PageFinder;

public interface FinancialLoanBillService {

    PageFinder<FinancialLoanBill> page(Integer customerId, Integer pageNo, Integer pageSize);
}
