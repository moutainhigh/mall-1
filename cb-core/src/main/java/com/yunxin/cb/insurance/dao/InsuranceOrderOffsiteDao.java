package com.yunxin.cb.insurance.dao;


import com.yunxin.cb.insurance.entity.InsuranceOrderOffsite;
import com.yunxin.cb.insurance.meta.InsuranceOrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by dengchenggang
 */
public interface InsuranceOrderOffsiteDao extends JpaRepository<InsuranceOrderOffsite, Integer>, JpaSpecificationExecutor<InsuranceOrderOffsite> {



}
