package com.yunxin.cb.insurance.dao;


import com.yunxin.cb.insurance.entity.InsuranceOrderInformedMatter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by dengchenggang
 */
public interface InsuranceOrderInformedMatterDao extends JpaRepository<InsuranceOrderInformedMatter, Integer>, JpaSpecificationExecutor<InsuranceOrderInformedMatter> {

    @Query("select c from InsuranceOrderInformedMatter c left join fetch c.insuranceInformedMatter left join fetch c.insuranceOrder d where d.orderId=?1")
    public List<InsuranceOrderInformedMatter> getInsuranceOrderInformedMatter(int orderId);


}
