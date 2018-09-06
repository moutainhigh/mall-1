package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.FinancialLoan;
import com.yunxin.cb.mall.entity.meta.LoanState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

/**
 * @title: 贷款记录Dao
 * @date: 2018/8/10 14:40
 */
public interface FinancialLoanDao extends JpaRepository<FinancialLoan, Integer>, JpaSpecificationExecutor<FinancialLoan> {
    @Query("select c from FinancialLoan c left join fetch c.customer left join fetch c.bank where c.loanId=?1")
    public FinancialLoan getFinancialLoanDetailById(int loanId);

    @Modifying
    @Query("update FinancialLoan c set c.state = ?1 , c.transferTime =?3 where c.loanId=?2")
    public void updateFinancialLoanStateById(LoanState state, int loanId, Date date);

    @Modifying
    @Query("update FinancialLoan c set c.state = ?1 , c.auditTime =?3 ,c.auditRemark = ?4 where c.loanId=?2")
    public void updateFinancialLoan(LoanState state, int loanId, Date date,String auditRemark);
}