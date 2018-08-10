package com.yunxin.cb.insurance.dao;

import com.yunxin.cb.insurance.entity.InsuranceOrderLog;
import com.yunxin.cb.insurance.meta.InsuranceOrderState;
import com.yunxin.cb.mall.entity.meta.PolicyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InsuranceOrderLogDao  extends JpaRepository<InsuranceOrderLog, Integer>, JpaSpecificationExecutor<InsuranceOrderLog> {

    @Modifying
    @Query("update InsuranceOrderLog c set c.orderState=?1 where c.insuranceOrder.orderId = ?2")
    void updInsuranceOrderLogState(InsuranceOrderState orderState, int orderId);

    /**
     * 感恩列表
     * @param customerId
     * @param
     * @param orderState
     * @return
     */
    @Query("select c from InsuranceOrderLog c left join c.customer d where d.recommendCustomer.customerId=?1 and c.orderState=?2 and d.praise=?3  GROUP BY c.customer.customerId  order by c.createTime")
    List<InsuranceOrderLog> findOrderLogByLevelCode(int customerId,InsuranceOrderState orderState,boolean praise);

    @Query("select c from InsuranceOrderLog c left join c.customer d where d.recommendCustomer.customerId = ?1 and c.orderState=?2 and d.policy=?3  GROUP BY c.customer.customerId  order by c.createTime")
    List<InsuranceOrderLog> findInsuranceOrderLogByLevelCode(int customerId,InsuranceOrderState orderState, PolicyType policy);



}
