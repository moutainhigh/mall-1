package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.OrderLoanApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
* @author gws
* @date 2018/7/24 20:12
* @param
* @return
*/
public interface OrderLoanApplyDao extends JpaRepository<OrderLoanApply, Integer>, JpaSpecificationExecutor<OrderLoanApply> {

    @Query("select ola from OrderLoanApply ola left join fetch ola.customer where ola.loanId=?1")
    public OrderLoanApply getOrderLoanApplyAndCustomerByLoanId(int loanId);
}
