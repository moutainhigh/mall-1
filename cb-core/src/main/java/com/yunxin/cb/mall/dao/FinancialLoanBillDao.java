package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.FinancialLoanBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FinancialLoanBillDao extends JpaRepository<FinancialLoanBill, Integer>, JpaSpecificationExecutor<FinancialLoanBill> {
}
