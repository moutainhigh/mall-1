/**
 *
 */
package com.yunxin.cb.monitor.dao;

import com.yunxin.cb.monitor.entity.Concent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author qulei
 */
public interface ConcentDao extends JpaRepository<Concent, Integer>, JpaSpecificationExecutor<Concent> {


    public Concent findByConcentCode(String concentCode);

    @Query("select distinct c from Concent c left join fetch c.devices d where c.customer.customerId=?1")
    public List<Concent> findByCustomerIdFetchDevices(int customerId);

}

