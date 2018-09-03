package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.FinacialCreditLineBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FinacialCreditLineBillDao extends JpaRepository<FinacialCreditLineBill, Integer>, JpaSpecificationExecutor<FinacialCreditLineBill> {

}
