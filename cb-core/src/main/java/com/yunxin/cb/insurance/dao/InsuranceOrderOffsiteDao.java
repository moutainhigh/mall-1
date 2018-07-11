package com.yunxin.cb.insurance.dao;


import com.yunxin.cb.insurance.entity.InsuranceOrderOffsite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by dengchenggang
 */
public interface InsuranceOrderOffsiteDao extends JpaRepository<InsuranceOrderOffsite, Integer>, JpaSpecificationExecutor<InsuranceOrderOffsite> {



}
