/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.CatalogAttribute;
import com.yunxin.cb.mall.entity.CatalogAttributeGroup;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author j000101
 */
public interface CatalogAttributeDao extends JpaRepository<CatalogAttribute, Integer>, JpaSpecificationExecutor<CatalogAttribute>, BaseDao<CatalogAttribute> {

    List<CatalogAttribute> findByCatalogAttributeGroup_GroupIdOrderBySortOrderAsc(int groupId);

    @Modifying
    @Query("delete from CatalogAttribute attr where attr.catalogAttributeGroup=?1")
    void deleteByGroup(CatalogAttributeGroup catalogAttributeGroup);

    @Query("select attr.attributeId from CatalogAttribute attr left join attr.catalogAttributeGroup ag where ag.groupId = ?1")
    List<Integer> findAttributeIdByGroupid(int groupId);

    @Query("select ag.groupId from CatalogAttribute attr left join attr.catalogAttributeGroup ag where attr.attributeId in ?1")
    List<Integer> findGroupIdsByAttributeIdIn(int[] attributeIds);

    @Query("select attr from CatalogAttribute attr left join fetch attr.catalogAttributeGroup ag where attr.attributeId = ?1")
    CatalogAttribute fetchAttributeGroupByAttributeId(int attributeId);
}
