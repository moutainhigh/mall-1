package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.ActivityCommodity;
import com.yunxin.cb.mall.entity.ActivityCommodityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Aidy_He on 16/2/23.
 */
public interface ActivityCommodityDao extends JpaRepository<ActivityCommodity, ActivityCommodityId>, JpaSpecificationExecutor<ActivityCommodity> {

    @Query("select ac from ActivityCommodity ac left join fetch ac.activity a left join fetch ac.commodity c  where a.activityId=?1")
    List<ActivityCommodity> getActivityCommoditiesByActivityId(final int activityId);

    @Query("select ac from ActivityCommodity ac left join fetch ac.activity a left join fetch ac.commodity c  where a.activityId=?1 and c.commodityId =?2")
    ActivityCommodity getActivityCommodityByActIdAndComId(final int activityId, final int commodityId);

    @Query("select ac from ActivityCommodity ac left join fetch ac.activity a left join fetch ac.commodity c  where c.commodityId=?1 and a.startTime <=?2 and a.endTime >=?3 and a.activityState=1")
    List<ActivityCommodity> getActivityCommoditiesByCommodityId(final int commodityId, Date currentDate1, Date currentDate2);

    @Query("select ac from ActivityCommodity ac left join fetch ac.activity a left join fetch ac.commodity c left join fetch a.ruleCondition r where c.commodityId in ?1 and a.startTime <=?2 and a.endTime >=?3 and a.activityState=1")
    List<ActivityCommodity> getActivityCommoditiesByCommodityIds(final Set<Integer> commodityId, Date currentDate1, Date currentDate2);
}
