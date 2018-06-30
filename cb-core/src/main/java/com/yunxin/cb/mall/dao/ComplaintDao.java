/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Complaint;
import com.yunxin.cb.mall.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author qulei
 */
public interface ComplaintDao extends JpaRepository<Complaint, Integer>, JpaSpecificationExecutor<Complaint> {

    List<Complaint> findByCustomer(Customer customer, Pageable pageable);

    @Query("select com from Complaint com left join fetch com.customer order by com.createTime desc ")
    List<Complaint> findByOrderCreateTime(Pageable pageable);

}
