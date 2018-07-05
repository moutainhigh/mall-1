package com.yunxin.cb.insurance.service;

import com.yunxin.cb.insurance.entity.InsuranceProduct;

import java.util.List;

public interface IInsuranceProductService {

    /**
     * 查询保险产品列表
     * @return
     */
    List<InsuranceProduct> getInsuranceProducts();

    /**
     * 添加保险产品
     * @param insuranceProduct
     * @return
     */
    InsuranceProduct addInsuranceProduct(InsuranceProduct insuranceProduct);

}
