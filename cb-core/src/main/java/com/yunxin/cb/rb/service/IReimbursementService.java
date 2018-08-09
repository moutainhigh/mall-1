package com.yunxin.cb.rb.service;

import com.yunxin.cb.mall.entity.OrderItem;
import com.yunxin.cb.rb.entity.Reimbursement;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author wangteng
 *
 */
public interface IReimbursementService {
    /**
     * 分页
     * @param query
     * @return
     */
    Page<Reimbursement> pageReimbursement(PageSpecification<Reimbursement> query);
    /**
     * 查询订单
     */
    List<OrderItem> queryOrderItemByIds(int reimbursementId);

    /**
     * 获取一条
     * @param reimbursementId
     * @return
     */
    Reimbursement getReimbursement(int reimbursementId);
}
