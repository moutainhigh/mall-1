package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.FinancialLogBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @Author chenpeng
 * @Description 账单 dao
 * @Date 2018/9/10 15:56 
 **/
public interface FinancialLogBillDao extends JpaRepository<FinancialLogBill, Integer>, JpaSpecificationExecutor<FinancialLogBill> {

}
