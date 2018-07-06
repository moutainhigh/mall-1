package com.yunxin.cb.insurance.dao;


import com.yunxin.cb.insurance.entity.InsuranceOrderBeneficiary;
import com.yunxin.cb.insurance.entity.InsuranceOrderInformedMatter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by dengchenggang
 */
public interface InsuranceOrderInformedMatterDao extends JpaRepository<InsuranceOrderInformedMatter, Integer>, JpaSpecificationExecutor<InsuranceOrderInformedMatter> {



}
