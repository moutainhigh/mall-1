package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.HomeFloor;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HomeFloorDao extends JpaRepository<HomeFloor, Integer>, JpaSpecificationExecutor<HomeFloor>, BaseDao<HomeFloor> {

    @Modifying
    @Query("update HomeFloor a set a.enabled = ?1 where a.floorId=?2")
    void enableHomeFloorById(boolean enabled, int floorId);

    @Query("select hf from HomeFloor hf left join fetch hf.floorCategories fct left join fetch fct.category left join fetch hf.floorCommodities fcm left join fetch fcm.commodity where hf.floorId = ?1")
    HomeFloor fetchAllById(int floorId);


//	@Modifying
//	@Query("update HomeFloor s set s.defaults=?3,s.type=?2 where s.serviceRuleId=?1")
//	public void setDefaultRuleById(int id, FreeRepairCleanRecordType type, boolean defaults);

    //
    @Query("select distinct s from HomeFloor s left join fetch s.floorCommodities fc left join fetch fc.commodity c left join fetch s.floorCategories fcat left join fetch fcat.category where s.enabled=true and c.commodityState=1 and c.publishState=1 order by s.sortOrder asc")
    public List<HomeFloor> findEnabledHomeFloors();

}
