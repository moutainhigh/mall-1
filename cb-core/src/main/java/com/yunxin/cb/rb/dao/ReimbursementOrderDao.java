package com.yunxin.cb.rb.dao;

import com.yunxin.cb.rb.entity.ReimbursementOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReimbursementOrderDao  extends JpaRepository<ReimbursementOrder, Integer>, JpaSpecificationExecutor<ReimbursementOrder> {

    @Query("select c from ReimbursementOrder c left join fetch c.orderItem left join fetch c.reimbursement d where d.reimbursementId=?1")
    public List<ReimbursementOrder> getReimbursementOrderItemById(int reimbursementId);
}
