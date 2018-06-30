/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Supplier;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author gonglei
 */
public interface SupplierDao extends JpaRepository<Supplier, Integer>, JpaSpecificationExecutor<Supplier>, BaseDao<Supplier> {


    List<Supplier> findByEnabledOrderBySupplierNameAsc(boolean enabled);

    @Modifying
    @Query("update Supplier c set c.enabled = ?1 where c.supplierId=?2")
    void enabledSupplierById(boolean enabled, int supplierId);

}
