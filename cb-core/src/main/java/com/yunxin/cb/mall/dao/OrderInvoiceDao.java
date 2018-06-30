/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.OrderInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author z001075
 */
public interface OrderInvoiceDao extends JpaRepository<OrderInvoice, Integer>, JpaSpecificationExecutor<OrderInvoice> {


}

