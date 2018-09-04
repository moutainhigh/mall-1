package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.FinancialCashbackLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FinancialCashbackLogDao extends JpaRepository<FinancialCashbackLog, Integer>, JpaSpecificationExecutor<FinancialCashbackLog> {
}
