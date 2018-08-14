package com.yunxin.cb.rb.dao;

import com.yunxin.cb.rb.entity.Reimbursement;
import com.yunxin.cb.rb.entity.meta.ReimbursementType;
import com.yunxin.cb.rb.entity.meta.RepaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ReimbursementDao extends JpaRepository<Reimbursement, Integer>, JpaSpecificationExecutor<Reimbursement>{

    @Modifying
    @Query("update Reimbursement c set c.orderState=?1 where c.reimbursementId = ?2")
    void updateReimbursementState(ReimbursementType orderState,int reimbursementId);

    @Modifying
    @Query("update Reimbursement c set c.orderState=?1,c.repaymentAmount=?2,c.actualAccount=?3,c.repaymentType=?4 where c.reimbursementId = ?5")
    void updateReimbursementsState(ReimbursementType orderState, BigDecimal repaymentAmount, BigDecimal actualAccount, RepaymentType repaymentType,int reimbursementId);

    @Query("select c from Reimbursement c left join fetch c.customer left join fetch c.reimbursementProcess where c.reimbursementId = ?1")
    Reimbursement getReimbursement(int reimbursementId);
}
