package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Activity;
import com.yunxin.cb.mall.entity.meta.ActivityState;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ActivityDao extends JpaRepository<Activity, Integer>, JpaSpecificationExecutor<Activity>, BaseDao<Activity> {

    Activity findByActivityNameAndEnabled(String activityName, boolean b);

    @Query("select  a from Activity a where a.activityName=?1 and a.activityId<>?2")
    Activity findByActivityNameAndIdNot(String activityName, int activityId);

    @Query("select  a from Activity a left join fetch a.ruleCondition r where  a.activityId=?1 and a.startTime <=?2 and a.endTime >=?3 and a.activityState=1")
    Activity getEffectActivityById(int activityId, Date currentDate1, Date currentDate2);

    @Modifying
    @Query("update Activity a set a.activityState=?2 where a.activityCode=?1")
    void updateActivityStatus(String activityCode, ActivityState status);

    Activity findByActivityCode(String code);


    Activity findByActivityNameAndEnabledAndActivityStateNotIn(String activityName, boolean b, List<ActivityState> statuss);

    @Query("update Activity  a set a.activityState=4 where a.endTime<?1")
    @Modifying
    void updateStatusAuto(Date date);

//    Activity findByActivityIdAndStatusAndDelAndDeadLineGressThan(int activityId, ActivityState becomeEffective, boolean b, Date date);

    @Query("select a from Activity a where a.activityId=?1 and a.activityState=?2 and a.enabled=?3 and a.endTime>?4")
    Activity findByActivityIdAndStatusAndEnabledAndDeadLineGreaterThan(int activityId, ActivityState becomeEffective, boolean enabled, Date date);
}
