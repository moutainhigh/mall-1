package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Activity;
import com.yunxin.cb.mall.entity.ActivityCommodity;
import com.yunxin.cb.mall.entity.meta.ActivityState;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IActivityService {

    public Page<Activity> pageActivities(final PageSpecification query);

    public Page<Activity> pageActivitiesForSite(final PageSpecification query);

    Page<ActivityCommodity> pageActivityCommoditiesForSite(PageSpecification<ActivityCommodity> query, int activityId);

    public Activity addActivity(Activity activity) throws EntityExistException;

    void updatePicPath(int activityId, String picPath);

    public void removeActivityById(int activityId);


    public Activity updateActivity(Activity activity) throws EntityExistException;

    public void updateActivityStatus(int activityId, ActivityState status);


    public void updateStatusAuto();

    /**
     * 根据活动id查询Activity
     *
     * @param activityId
     * @return
     */
    Activity findByActivityId(int activityId);

    /**
     * 活动生效或终止
     *
     * @param activityId
     * @param activityState
     * @return
     */
    public boolean effectOrDiscontinueActivity(int activityId, ActivityState activityState);

    /***
     * 活动关联商品
     * @param activityId
     * @param selectedCommodityId
     * @param limitAmountSize
     * @return
     */
    public boolean addActivityCommodities(int activityId, int[] selectedCommodityId, int[] limitAmountSize);

    /***
     * 根据活动ID获取活动下的所有商品
     * @param activityId
     * @return
     */
    List<ActivityCommodity> getActivityCommoditiesByActivityId(int activityId);

    /***
     * 根据活动ID获取当前状态为并且时间范围有效的活动对象
     * @param activityId
     * @return
     */
    public Activity getEffectActivityById(int activityId);
}
