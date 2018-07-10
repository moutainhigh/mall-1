package com.yunxin.cb.insurance.service;

import com.yunxin.cb.insurance.entity.InsuranceOrder;
import com.yunxin.cb.mall.entity.meta.InsuranceOrderState;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @author wangteng
 *
 */
public interface IInsuranceOrderService {
    /**
     * 分页
     * @param query
     * @return
     */
    Page<InsuranceOrder> pageInsuranceOrder(PageSpecification<InsuranceOrder> query);

    /**
     * 获取详情
     * @param orderId
     * @return
     */
    public InsuranceOrder getInsuranceOrderDetailById(int orderId);

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

    /**
     * 修改状态
     * @param orderId
     * @return
     */
    boolean updInsuranceOrderState(int orderId,InsuranceOrderState orderState);

    /**
     * 获取事项
     * @param orderId
     * @return
     */
    List<Map<String,Object>> findMatter(int orderId);

    /**
     * 根据订单编号查询订单
     *
     * @param orderCode
     * @return
     */
    public InsuranceOrder getInsuranceOrderDetailByOrderCode(String orderCode);

}
