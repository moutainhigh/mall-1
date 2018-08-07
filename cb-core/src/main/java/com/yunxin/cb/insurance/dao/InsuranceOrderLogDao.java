package com.yunxin.cb.insurance.dao;

import com.yunxin.cb.insurance.entity.InsuranceOrderLog;
import com.yunxin.cb.insurance.meta.InsuranceOrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface InsuranceOrderLogDao  extends JpaRepository<InsuranceOrderLog, Integer>, JpaSpecificationExecutor<InsuranceOrderLog> {
    @Modifying
    @Query("update InsuranceOrderLog c set c.orderState=?1 where c.insuranceOrder.orderId = ?2")
    void updInsuranceOrderLogState(InsuranceOrderState orderState, int orderId);
}
