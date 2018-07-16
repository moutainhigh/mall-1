package com.yunxin.cb.insurance.dao;


import com.yunxin.cb.insurance.entity.InsuranceProduct;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.*;

/**
 * Created by tanggangyi
 */
public interface InsuranceProductDao extends JpaRepository<InsuranceProduct, Integer>, JpaSpecificationExecutor<InsuranceProduct> {

       @Query("select i from InsuranceProduct i left join fetch i.insuranceInformedMatters  where i.prodId=?1")
       public InsuranceProduct getInsuranceProductById(int prodId);

       @Query("select i from InsuranceProduct i left join fetch i.insuranceInformedMatters  where i.prodName like '%?1%'")
       public InsuranceProduct getInsuranceProductByName(int prodName);

}
