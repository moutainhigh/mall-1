package com.yunxin.cb.mall.web.thread;

import com.yunxin.cb.mall.service.IActivityService;
import com.yunxin.core.util.LogicUtils;
import com.yunxin.cb.mall.service.IActivityService;

/**
 * Created by k001389 on 2014/9/15.
 */
public class ChangeActivityStatusThread implements Runnable {

    private IActivityService activityService;

    public ChangeActivityStatusThread() {
        getActivityService();
    }

    public IActivityService getActivityService() {
        if(LogicUtils.isNull(activityService)) {
            //activityService = (ActivityService) SpringContextUtil.getBean("activityService");
        }
        return activityService;
    }

    public void setActivityService(IActivityService activityService) {
        this.activityService = activityService;
    }

    @Override
    public void run() {
        activityService.updateStatusAuto();

    }
}
