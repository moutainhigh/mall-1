package com.yunxin.cb.insurance.service;

import com.yunxin.cb.insurance.entity.InsuranceLog;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

/**
 * @author wangteng
 *
 */
public interface IInsuranceLogService {
    /**
     * 分页
     * @param query
     * @return
     */
    Page<InsuranceLog> pageInsuranceLog(PageSpecification<InsuranceLog> query);
}
