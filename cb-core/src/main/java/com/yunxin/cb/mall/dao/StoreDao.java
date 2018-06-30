/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Store;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author z001075
 */
public interface StoreDao extends JpaRepository<Store, Integer>, JpaSpecificationExecutor<Store>, BaseDao<Store> {

    Store findTopByStoreCode(String depositoryCode);

    Store findTopByStoreName(String depositoryName);

    public Store findByStoreNameAndStoreIdNot(String depositoryName, int depositoryId);


}
