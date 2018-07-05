package com.yunxin.cb.insurance.dao;


import com.yunxin.cb.insurance.entity.InsuranceProduct;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.*;

/**
 * Created by tanggangyi
 */
public interface InsuranceProductDao extends JpaRepository<InsuranceProduct, Integer>, JpaSpecificationExecutor<InsuranceProduct> {



}
