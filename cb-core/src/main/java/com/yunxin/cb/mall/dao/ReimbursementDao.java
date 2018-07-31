package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReimbursementDao extends JpaRepository<Reimbursement, Integer>, JpaSpecificationExecutor<Reimbursement>{
}
