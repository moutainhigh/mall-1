package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.HistoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * wangteng
 */
public interface HistoryRecordDao  extends JpaRepository<HistoryRecord, Integer>, JpaSpecificationExecutor<HistoryRecord> {

    @Query(" select c.recordId from HistoryRecord c where c.customer.customerId=?1 and c.createTime between ?2 and ?3 group by c.productId")
    List<HistoryRecord> countHistoryRecordByCustomer(int customerId, Date startDate,Date endDate);
    long countByCustomer(Customer customer);

}
