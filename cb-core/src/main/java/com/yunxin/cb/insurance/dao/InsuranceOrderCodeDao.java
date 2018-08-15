package com.yunxin.cb.insurance.dao;


import com.yunxin.cb.insurance.entity.InsuranceOrderCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by wangteng
 */
public interface InsuranceOrderCodeDao extends JpaRepository<InsuranceOrderCode, Integer>, JpaSpecificationExecutor<InsuranceOrderCode> {

    @Query("select  a from InsuranceOrderCode a where a.codeNo=?1")
    InsuranceOrderCode findInsuranceOrderCodeByCodeNo(String codeNo);

    @Query("select  a from InsuranceOrderCode a where a.useed=0")
    List<InsuranceOrderCode> getInsuranceOrderCodeByUseed();

    @Modifying
    @Query("update InsuranceOrderCode set useed=1 where codeNo=?1")
    void updateInsuranceOrderCode(String codeNo);

}
