package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.ReimbursementProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReimbursementProcessDao  extends JpaRepository<ReimbursementProcess, Integer>, JpaSpecificationExecutor<ReimbursementProcess> {
}

