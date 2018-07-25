/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.AttributeGroup;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author z001075
 */
public interface AttributeGroupDao extends JpaRepository<AttributeGroup, Integer>, JpaSpecificationExecutor<AttributeGroup>, BaseDao<AttributeGroup> {

    @EntityGraph(value = "AttributeGroup.attributes", type = EntityGraph.EntityGraphType.LOAD)
    AttributeGroup findByGroupId(int groupId);

    @Query("select distinct pg from AttributeGroup pg left join fetch pg.attributes c where pg.commodity.commodityId=?1 order by pg.groupName asc")
    List<AttributeGroup> findByCommodity_CommodityIdOrderByGroupNameAsc(int commodityId);

    @Query("select distinct pg from AttributeGroup pg left join fetch pg.attributes c where pg.commodity.commodityId=?1 order by pg.groupId asc")
    List<AttributeGroup> findByCommodity_CommodityIdOrderByGroupIdAsc(int commodityId);

    AttributeGroup findByGroupName(String groupName);

    AttributeGroup findByGroupNameAndGroupIdNot(String groupName, int groupId);

    @Query("select distinct pg from AttributeGroup pg where pg.groupName=?1 and pg.commodity.commodityId=?2")
    AttributeGroup checkUniqueByGroupNameAndCommodityId(String groupName, int commodityId);


}
