package com.yunxin.cb.rb.dao;

import com.yunxin.cb.rb.entity.ReimbursementOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ReimbursementOrderDao  extends JpaRepository<ReimbursementOrder, Integer>, JpaSpecificationExecutor<ReimbursementOrder> {

    @Query("select c from ReimbursementOrder c left join fetch c.orderItem left join fetch c.reimbursement d where d.reimbursementId=?1")
    public List<ReimbursementOrder> getReimbursementOrderItemById(int reimbursementId);

    /**
     * @Description:            根据报账id查找报账总金额
     * @author: lxc
     * @param reimbursementId   报账id
     * @Return java.math.BigDecimal:
     * @DateTime: 2018/8/10 19:55
     */
    @Query("select sum(rro.amount) from ReimbursementOrder rro LEFT JOIN  rro.reimbursement rr  where rr.reimbursementId = ?1 ")
    BigDecimal getReimburseAmountByReimburseId(int reimbursementId);

    @Query("select c from ReimbursementOrder c left join fetch c.orderItem f left join fetch c.reimbursement d where d.reimbursementId=?1 and f.itemId=?2")
    public ReimbursementOrder getReimbursementOrderItem(int reimbursementId,int itemId);
}
