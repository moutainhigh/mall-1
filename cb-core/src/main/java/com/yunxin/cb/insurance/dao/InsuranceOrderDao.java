package com.yunxin.cb.insurance.dao;


import com.yunxin.cb.insurance.entity.InsuranceOrder;
import com.yunxin.cb.insurance.meta.InsuranceOrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by wangteng
 */
public interface InsuranceOrderDao extends JpaRepository<InsuranceOrder, Integer>, JpaSpecificationExecutor<InsuranceOrder> {

    @Query("select c from InsuranceOrder c left join fetch c.insuranceProduct  left join fetch c.customer left join fetch c.insuranceOrderInsured left join fetch c.insuranceOrderPolicyholder left join fetch c.insuranceOrderPolicyholderBank left join fetch c.insuranceOrderBeneficiarys left join fetch c.insuranceOrderInformedMatters left join fetch c.insuranceOrderOffsite where c.orderId=?1")
    public InsuranceOrder getInsuranceOrderDetailById(int orderId);
    @Query("select c from InsuranceOrder c left join fetch c.insuranceProduct  left join fetch c.customer d where d.customerId=?1 and c.orderState=1 order by c.createTime desc ")
    public List<InsuranceOrder> findOrderPriceByCustomerId(int customerId);
    @Modifying
    @Query("update InsuranceOrder c set c.orderState=?1 where c.orderId = ?2")
    void updInsuranceOrderState(InsuranceOrderState orderState,int orderId);

    @Query("select c from InsuranceOrder c left join fetch c.insuranceProduct  left join fetch c.customer left join fetch c.insuranceOrderInsured left join fetch c.insuranceOrderPolicyholder left join fetch c.insuranceOrderPolicyholderBank left join fetch c.insuranceOrderBeneficiarys left join fetch c.insuranceOrderInformedMatters ioim left join fetch ioim.insuranceInformedMatter left join fetch c.insuranceOrderOffsite where c.orderCode=?1")
    public InsuranceOrder getInsuranceOrderDetailByOrderCode(String orderCode);
}
