package com.yunxin.cb.insurance.dao;

import com.yunxin.cb.insurance.entity.InsuranceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
 * Created by wangteng
 */
public interface InsuranceLogDao  extends JpaRepository<InsuranceLog, Integer>, JpaSpecificationExecutor<InsuranceLog> {
}
