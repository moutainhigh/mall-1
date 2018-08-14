package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.HistoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * wangteng
 */
public interface HistoryRecordDao  extends JpaRepository<HistoryRecord, Integer>, JpaSpecificationExecutor<HistoryRecord> {
    long countByCustomer(Customer customer);
}
