package com.yunxin.cb.insurance.dao;

import com.yunxin.cb.insurance.entity.InsuranceInformedMatter;
import com.yunxin.cb.insurance.entity.InsuranceInformedMatterGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InsuranceInformedMatterGroupDao extends JpaRepository<InsuranceInformedMatterGroup, Integer>, JpaSpecificationExecutor<InsuranceInformedMatterGroup> {

    @Query("select c from InsuranceInformedMatterGroup c   where c.groupId=?1")
    public InsuranceInformedMatterGroup getInsuranceInformedMatterGroup(int grouId);


    @Query("select c from InsuranceInformedMatterGroup c   where c.enabled=?1")
    public List<InsuranceInformedMatterGroup> findList(int enabled);
}
