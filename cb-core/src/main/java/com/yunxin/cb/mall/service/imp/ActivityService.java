package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.ActivityCommodityDao;
import com.yunxin.cb.mall.dao.ActivityDao;
import com.yunxin.cb.mall.dao.CategoryDao;
import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.ActivityState;
import com.yunxin.cb.mall.service.IActivityService;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.AttributeReplication;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.LogicUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ActivityService implements IActivityService {

    @Resource
    private ActivityDao activityDao;

    @Resource
    private ActivityCommodityDao activityCommodityDao;

    @Resource
    private CategoryDao categoryDao;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Activity> pageActivities(final PageSpecification query) {
        query.setCustomSpecification(new CustomSpecification<Activity>() {
            @Override
            public void addConditions(Root<Activity> root, CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                query.orderBy(builder.desc(root.get(Activity_.activityId)));
            }

        });
        return activityDao.findAll(query, query.getPageRequest());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<Activity> pageActivitiesForSite(final PageSpecification query) {
        query.setCustomSpecification(new CustomSpecification<Activity>() {
            @Override
            public void addConditions(Root<Activity> root, CriteriaQuery<?> query, CriteriaBuilder builder,
                                      List<Predicate> predicates) {
                Path<Boolean> delPath = root.get(Activity_.enabled);
                predicates.add(builder.equal(delPath, true));
                Path<ActivityState> statusPath = root.get(Activity_.activityState);
                predicates.add(builder.equal(statusPath, ActivityState.TAKE_EFFECT));
                Path<Date> deadLinePath = root.get(Activity_.endTime);
                predicates.add(builder.greaterThan(deadLinePath, new Date()));
                Path<Date> startTimePath = root.get(Activity_.startTime);
                predicates.add(builder.lessThanOrEqualTo(startTimePath, new Date()));
                query.orderBy(builder.desc(root.get(Activity_.endTime)));
            }

        });
        return activityDao.findAll(query, query.getPageRequest());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Page<ActivityCommodity> pageActivityCommoditiesForSite(PageSpecification<ActivityCommodity> query, int activityId) {
        query.setCustomSpecification(new CustomSpecification<ActivityCommodity>() {
            @Override
            public void buildFetch(Root<ActivityCommodity> root) {
                Fetch<ActivityCommodity, ActivityCommodityId> fetch = root.fetch(ActivityCommodity_.id, JoinType.INNER);
                root.fetch(ActivityCommodity_.activity, JoinType.INNER).fetch(Activity_.ruleCondition, JoinType.LEFT);
                root.fetch(ActivityCommodity_.commodity, JoinType.INNER);
            }

            @Override
            public void addConditions(Root<ActivityCommodity> root, CriteriaQuery<?> query, CriteriaBuilder builder, List<Predicate> predicates) {
                Path<Integer> aIdPath = root.get(ActivityCommodity_.id).get(ActivityCommodityId_.activityId);
                predicates.add(builder.equal(aIdPath, activityId));
            }
        });
        Page<ActivityCommodity> page = activityCommodityDao.findAll(query, query.getPageRequest());
        return page;
    }

    @Override
    public Activity addActivity(Activity activity) throws EntityExistException {
        if (!activityDao.isOrUnique(activity, Activity_.activityName, Activity_.activityCode)) {
            throw new EntityExistException("活动名或活动号已存在");
        }
        activity.setEnabled(true);
        activity.setActivityState(ActivityState.WAIT_TAKE_EFFECT);
        activity.setCreateTime(new Date());

        // 存categories
        Set<Category> categories = new HashSet<>();
        for (int activityId : activity.getActivityCategoryIds()) {
            categories.add(categoryDao.findOne(activityId));
        }
        activity.setCategories(categories);
        return activityDao.save(activity);
    }

    @Override
    public void removeActivityById(int activityId) {
        activityDao.delete(activityId);
    }

    @Override
    public Activity updateActivity(Activity activity) throws EntityExistException {
        if (!activityDao.isOrUnique(activity, Activity_.activityName, Activity_.activityCode)) {
            throw new EntityExistException("活动名或活动号已存在");
        }
        Activity oldActivity = activityDao.findOne(activity.getActivityId());
        AttributeReplication.copying(activity, oldActivity, Activity_.activityName, Activity_.activityAlias, Activity_.activityCode, Activity_.startTime,
                Activity_.endTime, Activity_.activityType, Activity_.activityScope, Activity_.limitAmount, Activity_.limitAmountSize, Activity_.remark);

        // 存categories
        Set<Category> categories = new HashSet<>();
        for (int activityId : activity.getActivityCategoryIds()) {
            categories.add(categoryDao.findOne(activityId));
        }
        activity.setCategories(categories);
        return oldActivity;
    }


    @Override
    public void updatePicPath(int activityId, String picPath) {
        Activity activity = activityDao.findOne(activityId);
        activity.setPicPath(picPath);
    }

    @Override
    public void updateActivityStatus(int activityId, ActivityState status) {
        Activity activity = activityDao.findOne(activityId);
        activityDao.updateActivityStatus(activity.getActivityCode(), status);
    }


    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Activity findByActivityId(int activityId) {
        return activityDao.findOne(activityId);
    }

    @Override
    public void updateStatusAuto() {
        activityDao.updateStatusAuto(new Date());
    }

    @Override
    public boolean effectOrDiscontinueActivity(int activityId, ActivityState activityState) {
        Activity activity = activityDao.findOne(activityId);
        if (!activity.isEnabled()) {
            return false;
        }
        if (activity.getActivityState() == activityState) {
            return false;
        }
        activity.setActivityState(activityState);
        return true;

    }

    @Override
    public boolean addActivityCommodities(int activityId, int[] selectedCommodityId, int[] limitAmountSize) {
        if (null != selectedCommodityId && selectedCommodityId.length > 0) {
            List<ActivityCommodity> actComs = activityCommodityDao.getActivityCommoditiesByActivityId(activityId);
            if (LogicUtils.isNotNullAndEmpty(actComs)) {
                activityCommodityDao.delete(actComs);
            }

            for (int i = 0; i < selectedCommodityId.length; i++) {
                ActivityCommodityId id = new ActivityCommodityId(activityId, selectedCommodityId[i]);
                ActivityCommodity activityCommodity = new ActivityCommodity();
                activityCommodity.setActivity(activityDao.findOne(activityId));
                activityCommodity.setCommodity(new Commodity(selectedCommodityId[i]));
                activityCommodity.setId(id);
                activityCommodity.setLimitAmountSize(limitAmountSize[i]);
                activityCommodityDao.save(activityCommodity);
            }
            return true;
        }
        return false;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<ActivityCommodity> getActivityCommoditiesByActivityId(int activityId) {
        return activityCommodityDao.getActivityCommoditiesByActivityId(activityId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Activity getEffectActivityById(int activityId) {
        Date date = new Date();
        return activityDao.getEffectActivityById(activityId, date, date);
    }
}
