/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.CatalogAttributeGroup;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author z001075
 */
public interface CatalogAttributeGroupDao extends JpaRepository<CatalogAttributeGroup, Integer>, JpaSpecificationExecutor<CatalogAttributeGroup>, BaseDao<CatalogAttributeGroup> {

    @Query("select pg from CatalogAttributeGroup pg left join fetch pg.catalog left join fetch pg.catalogAttributes where pg.groupId=?1")
    CatalogAttributeGroup findByIdFetchAll(int groupId);

    @Query("select distinct pg from CatalogAttributeGroup pg left join fetch pg.catalogAttributes c where pg.catalog.catalogId=?1 ")
    List<CatalogAttributeGroup> findAttributeGroupFetchAttributesByCategoryId(int categoryId);

    CatalogAttributeGroup findByGroupName(String groupName);

}
