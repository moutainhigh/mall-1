package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Reimbursement;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
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
}
