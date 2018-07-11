package com.yunxin.cb.insurance.dao;


import com.yunxin.cb.insurance.entity.InsuranceOrderCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by wangteng
 */
public interface InsuranceOrderCodeDao extends JpaRepository<InsuranceOrderCode, Integer>, JpaSpecificationExecutor<InsuranceOrderCode> {

    @Query("select  a from InsuranceOrderCode a where a.codeNo=?1")
    InsuranceOrderCode findInsuranceOrderCodeByCodeNo(String codeNo);
}
