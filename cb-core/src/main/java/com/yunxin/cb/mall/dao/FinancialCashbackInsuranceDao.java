package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.FinancialCashbackInsurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FinancialCashbackInsuranceDao extends JpaRepository<FinancialCashbackInsurance, Integer>, JpaSpecificationExecutor<FinancialCashbackInsurance> {
}
