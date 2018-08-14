package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.FinacialInsuCashbackLog;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

public interface IFinacialInsuCashbackLogService {
    /**
     * 分页
     * @param query
     * @return
     */
    Page<FinacialInsuCashbackLog> pageFinacialInsuCashbackLog(PageSpecification<FinacialInsuCashbackLog> query);
}
