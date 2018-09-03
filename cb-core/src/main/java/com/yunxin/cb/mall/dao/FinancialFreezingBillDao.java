package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.FinancialFreezingBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FinancialFreezingBillDao extends JpaRepository<FinancialFreezingBill, Integer>, JpaSpecificationExecutor<FinancialFreezingBill> {

}
