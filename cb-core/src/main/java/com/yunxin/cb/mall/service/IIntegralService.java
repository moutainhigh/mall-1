package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.Integral;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

public interface IIntegralService {

    public Page<Integral> pageIntegrals(PageSpecification<Integral> query);

    Page<Integral> pageIntegrals(PageSpecification<Integral> query, Customer customer);

    public Integral addIntegral(Integral integral);

    /***
     * 计算订单可获积分
     * @param customerId 客户ID
     * @param calculateValue 购物金额
     * @return
     */
    public int getOrderCalculateIntegral(int customerId, double calculateValue);

    /***
     * 注册赠送积分
     * @return
     */
    public int getRegisterCalculateIntegral();
}
