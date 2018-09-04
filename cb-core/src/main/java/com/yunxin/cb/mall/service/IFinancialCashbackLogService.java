package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinancialCashbackLog;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

public interface IFinancialCashbackLogService {
    /**
     * 分页
     * @param query
     * @return
     */
    Page<FinancialCashbackLog> pageFinancialCashbackLog(PageSpecification<FinancialCashbackLog> query);
}
