package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.FinancialLoanRepayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * 功能描述:返现管理DAO
 *
 * @auther yangzhen
 * @date 2018/9/4 18:28
 */
public interface FinancialLoanRepaymentDao extends JpaRepository<FinancialLoanRepayment, Integer>, JpaSpecificationExecutor<FinancialLoanRepayment> {

}
