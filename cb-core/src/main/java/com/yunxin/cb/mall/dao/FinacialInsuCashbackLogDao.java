package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.FinacialInsuCashbackLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FinacialInsuCashbackLogDao extends JpaRepository<FinacialInsuCashbackLog, Integer>, JpaSpecificationExecutor<FinacialInsuCashbackLog> {
}
