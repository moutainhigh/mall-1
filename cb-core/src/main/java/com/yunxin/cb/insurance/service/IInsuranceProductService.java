package com.yunxin.cb.insurance.service;

import com.yunxin.cb.insurance.entity.InsuranceProduct;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IInsuranceProductService {


    /**
     * 
     * @param query
     * @return
     */
    public Page<InsuranceProduct> pageInsuranceProduct(PageSpecification<InsuranceProduct> query);

    /**
     * 查询保险产品列表
     * @return
     */
    public List<InsuranceProduct> getInsuranceProducts();

    /**
     * 添加保险产品
     * @param insuranceProduct
     * @return
     */
    public InsuranceProduct addInsuranceProduct(InsuranceProduct insuranceProduct);


    /**
     *
     * @param insuranceProduct
     * @return
     */
    public InsuranceProduct updateInsuranceProduct(InsuranceProduct insuranceProduct);


    /**
     *
     * @param prodId
     * @return
     */
    public InsuranceProduct getInsuranceProductById(int prodId);

    /**
     *
     * @param prodId
     */
    public void removeById(int prodId);


}
