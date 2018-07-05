package com.yunxin.cb.insurance.service;

import com.yunxin.cb.insurance.entity.InsuranceOrder;

import java.util.List;

/**
 *  create by dengchenggang
 */

public interface IInsuranceOrderService {

    /**
     * 根据用户ID查询保险订单列表
     * @return
     */
    List<InsuranceOrder> getInsuranceOrderByCustomer();

    /**
     * 添加保险订单
     * @param insuranceOrder
     * @return
     */
    InsuranceOrder addInsuranceOrder(InsuranceOrder insuranceOrder);

}
