package com.yunxin.cb.rb.dao;

import com.yunxin.cb.rb.entity.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReimbursementDao extends JpaRepository<Reimbursement, Integer>, JpaSpecificationExecutor<Reimbursement>{
}
