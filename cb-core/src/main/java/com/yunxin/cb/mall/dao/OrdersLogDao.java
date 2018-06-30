package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.OrderLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author x001393
 */
public interface OrdersLogDao extends JpaRepository<OrderLog, Integer>, JpaSpecificationExecutor<OrderLog> {


}
