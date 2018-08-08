package com.yunxin.cb.insurance.dao;

import com.yunxin.cb.insurance.entity.InsuranceOrderLog;
import com.yunxin.cb.insurance.meta.InsuranceOrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InsuranceOrderLogDao  extends JpaRepository<InsuranceOrderLog, Integer>, JpaSpecificationExecutor<InsuranceOrderLog> {
    @Modifying
    @Query("update InsuranceOrderLog c set c.orderState=?1 where c.insuranceOrder.orderId = ?2")
    void updInsuranceOrderLogState(InsuranceOrderState orderState, int orderId);

    @Query("select c from InsuranceOrderLog c left join c.customer d where d.customerId <> ?1 and d.levelCode like ?2 and c.orderState=?3")
    List<InsuranceOrderLog> findInsuranceOrderLogByLevelCode(int customerId,String levelCode,InsuranceOrderState orderState);
}
