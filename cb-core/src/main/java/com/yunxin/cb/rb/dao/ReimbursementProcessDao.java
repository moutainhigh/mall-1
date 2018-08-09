package com.yunxin.cb.rb.dao;

import com.yunxin.cb.rb.entity.ReimbursementProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReimbursementProcessDao  extends JpaRepository<ReimbursementProcess, Integer>, JpaSpecificationExecutor<ReimbursementProcess> {
}

