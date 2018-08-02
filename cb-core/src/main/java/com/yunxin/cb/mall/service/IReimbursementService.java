package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Reimbursement;
import com.yunxin.cb.mall.entity.ReimbursementOrder;
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
    List<ReimbursementOrder> queryOrderByIds(int reimbursementId);
}
