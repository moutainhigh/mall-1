package com.yunxin.cb.insurance.dao;

import com.yunxin.cb.insurance.entity.InsuranceEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InsuranceEmailDao  extends JpaRepository<InsuranceEmail, Integer>, JpaSpecificationExecutor<InsuranceEmail> {
}
