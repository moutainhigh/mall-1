package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.FinancialCreditLineBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FinancialCreditLineBillDao extends JpaRepository<FinancialCreditLineBill, Integer>, JpaSpecificationExecutor<FinancialCreditLineBill> {

}
