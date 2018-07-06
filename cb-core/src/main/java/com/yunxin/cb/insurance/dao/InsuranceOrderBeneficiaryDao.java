package com.yunxin.cb.insurance.dao;


import com.yunxin.cb.insurance.entity.InsuranceOrderBeneficiary;
import com.yunxin.cb.insurance.entity.InsuranceOrderInsured;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by dengchenggang
 */
public interface InsuranceOrderBeneficiaryDao extends JpaRepository<InsuranceOrderBeneficiary, Integer>, JpaSpecificationExecutor<InsuranceOrderBeneficiary> {



}
