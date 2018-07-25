package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.CustomerTradingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerTradingRecordDao extends JpaRepository<CustomerTradingRecord, Integer>, JpaSpecificationExecutor<CustomerTradingRecord> {
}
