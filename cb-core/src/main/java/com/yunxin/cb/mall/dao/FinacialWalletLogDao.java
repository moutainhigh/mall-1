package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.FinacialWalletLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FinacialWalletLogDao extends JpaRepository<FinacialWalletLog, Integer>, JpaSpecificationExecutor<FinacialWalletLog> {

}
