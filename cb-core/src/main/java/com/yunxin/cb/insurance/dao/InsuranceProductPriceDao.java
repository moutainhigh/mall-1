package com.yunxin.cb.insurance.dao;


import com.yunxin.cb.insurance.entity.InsuranceProduct;
import com.yunxin.cb.insurance.entity.InsuranceProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by tanggangyi
 */
public interface InsuranceProductPriceDao extends JpaRepository<InsuranceProductPrice, Integer>, JpaSpecificationExecutor<InsuranceProductPrice> {

    @Modifying
    @Query("delete from InsuranceProductPrice attr where attr.insuranceProduct=?1")
    void deleteByProduct(InsuranceProduct insuranceProduct);
}
