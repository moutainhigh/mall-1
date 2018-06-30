/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Attribute;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author j000101
 */
public interface AttributeDao extends JpaRepository<Attribute, Integer>, JpaSpecificationExecutor<Attribute>, BaseDao<Attribute> {


    Attribute findByAttributeName(String attrName);

    List<Attribute> findByAttributeIdIn(int[] ids);

    @Query("select ag.groupId from Attribute attr left join attr.attributeGroup ag where attr.attributeId in ?1")
    List<Integer> findGroupIdsByAttributeIdIn(int[] attributeIds);

    @EntityGraph(value = "Attribute.attributeGroup", type = EntityGraph.EntityGraphType.LOAD)
    Attribute findByAttributeId(int attributeId);
}
