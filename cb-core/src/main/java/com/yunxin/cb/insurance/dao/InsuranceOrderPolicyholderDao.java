package com.yunxin.cb.insurance.dao;


import com.yunxin.cb.insurance.entity.InsuranceOrderInsured;
import com.yunxin.cb.insurance.entity.InsuranceOrderPolicyholder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by dengchenggang
 */
public interface InsuranceOrderPolicyholderDao extends JpaRepository<InsuranceOrderPolicyholder, Integer>, JpaSpecificationExecutor<InsuranceOrderPolicyholder> {



}
