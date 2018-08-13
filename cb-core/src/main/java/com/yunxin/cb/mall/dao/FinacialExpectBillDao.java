package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Feedback;
import com.yunxin.cb.mall.entity.FinacialExpectBill;
import com.yunxin.cb.mall.entity.FinacialWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface FinacialExpectBillDao extends JpaRepository<FinacialExpectBill, Integer>, JpaSpecificationExecutor<FinacialExpectBill> {

}
