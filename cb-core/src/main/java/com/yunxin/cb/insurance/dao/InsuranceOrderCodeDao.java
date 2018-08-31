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

    @Query("select  max(a.sort)  from InsuranceOrderCode a ")
    int getInsuranceOrderCodeMaxSort();

    @Query("select  sum(case when  a.useed=0 then 1 else 0 end) as notUseed,sum(case when a.useed=1 then 1 else 0 end) as onUseed from InsuranceOrderCode a where a.sort=?1")
    Object getInsuranceOrderCodeBySort(int sort);

    @Query("select  count(a.codeId) from InsuranceOrderCode a where a.useed=?1 and  a.sort=?2")
    int getInsuranceOrdernotUseedBySort(int useed,int sort);
    @Modifying
    @Query("update InsuranceOrderCode set useed=1 where codeId=?1")
    void updateInsuranceOrderCode(int codeId);

}
