package com.yunxin.cb.rb.dao;

import com.yunxin.cb.rb.entity.Reimbursement;
import com.yunxin.cb.rb.entity.meta.ReimbursementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReimbursementDao extends JpaRepository<Reimbursement, Integer>, JpaSpecificationExecutor<Reimbursement>{

    @Modifying
    @Query("update Reimbursement c set c.orderState=?1 where c.reimbursementId = ?2")
    void updateReimbursementState(ReimbursementType orderState,int reimbursementId);

    @Query("select c from Reimbursement c left join fetch c.customer left join fetch c.reimbursementProcess where c.reimbursementId = ?1")
    Reimbursement getReimbursement(int reimbursementId);
}
