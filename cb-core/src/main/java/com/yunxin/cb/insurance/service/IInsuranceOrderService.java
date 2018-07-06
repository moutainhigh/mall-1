package com.yunxin.cb.insurance.service;

import com.yunxin.cb.insurance.entity.InsuranceOrder;
import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Page<InsuranceOrder> pageInsuranceOrder(PageSpecification<InsuranceOrder> query);

    /**
     * 获取详情
     * @param orderId
     * @return
     */
    public InsuranceOrder getInsuranceOrderDetailById(int orderId);
}
