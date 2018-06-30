package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Payment;
import com.yunxin.cb.mall.entity.meta.PayState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author sheh
 */
public interface PayDao extends JpaRepository<Payment, Integer>, JpaSpecificationExecutor<Payment> {

    @Query("select p from Payment p where p.payType=0 and p.payFlowCode=?1")
    Payment findByCode(String payFlowCode);

    @Query("select p from Payment p inner join fetch p.order o left join fetch p.customer where p.payType=0 and  o.orderCode =?1")
    Payment getPayByOrderCode(String orderCode);

    @Query("select p from Payment p left join fetch p.order left join fetch p.customer where p.payId=?1")
    Payment findByPayId(int payId);

    @Query("select p from Payment p left join fetch p.order o left join fetch p.customer where p.payState=?1 and o.orderCode=?2")
    Payment getPaymentByPayStateAndOrderCode(PayState state, String orderCode);

    @Query("select p from Payment p inner join fetch p.order o where o.orderId =?1")
    Payment getPayByOrderId(int orderId);

    @Query("select p from Payment p where p.payType=1 and p.payState=?1 and p.batchNo=?2")
    List<Payment> getPaymentsByBatchNo(PayState state, String batchNo);

//    @Query("select p from Payment p left join fetch p.order ord where ord.orderId=?1")
//    List<Payment> findByOrderId(int orderId);
}
