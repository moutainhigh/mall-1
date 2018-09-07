package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.FinancialWalletLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FinancialWalletLogDao extends JpaRepository<FinancialWalletLog, Integer>, JpaSpecificationExecutor<FinancialWalletLog> {

}
