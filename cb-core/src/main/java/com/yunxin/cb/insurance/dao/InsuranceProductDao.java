package com.yunxin.cb.insurance.dao;


import com.yunxin.cb.insurance.entity.InsuranceProduct;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Created by tanggangyi
 */
public interface InsuranceProductDao extends JpaRepository<InsuranceProduct, Integer>, JpaSpecificationExecutor<InsuranceProduct> {

       @Query("select i from InsuranceProduct i left join fetch i.insuranceInformedMatters m left join fetch m.matterGroup where i.prodId=?1")
       public InsuranceProduct getInsuranceProductById(int prodId);

       @Query("select i from InsuranceProduct i  where i.enabled=1")
       public List<InsuranceProduct> findInsuranceProductList();

       @Modifying
       @Query("update InsuranceProduct iim set iim.enabled =?2 where iim.prodId=?1")
       public void enableInsuranceProductById(int proId,int enabled);


}
