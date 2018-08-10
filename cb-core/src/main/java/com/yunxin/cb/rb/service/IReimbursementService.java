package com.yunxin.cb.rb.service;

import com.yunxin.cb.mall.entity.OrderItem;
import com.yunxin.cb.rb.entity.Reimbursement;
import com.yunxin.cb.rb.entity.meta.ReimbursementType;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
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
     * 获取数据
     * @param reimbursementId
     * @return
     */
    Reimbursement getReimbursement(int reimbursementId);

    /**
     * 审核
     * @param reimbursementId
     * @param reimbursementType
     * @param remarks
     * @param operType
     * @param request
     * @return
     */
    boolean reimbursementAuditing(int reimbursementId, ReimbursementType reimbursementType, String remarks,int operType, HttpServletRequest request);
}
