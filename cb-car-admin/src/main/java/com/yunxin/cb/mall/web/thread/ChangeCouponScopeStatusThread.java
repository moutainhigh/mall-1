package com.yunxin.cb.mall.web.thread;

import com.yunxin.cb.mall.service.ICouponService;
import com.yunxin.core.util.LogicUtils;
import com.yunxin.cb.mall.service.ICouponService;

/**
 * Created by k001389 on 2015/2/11.
 */
public class ChangeCouponScopeStatusThread implements Runnable {

    private ICouponService couponService;

    public ChangeCouponScopeStatusThread() {
        getCouponService();
    }

    public ChangeCouponScopeStatusThread(ICouponService couponService) {
        this.couponService = couponService;
    }

    public ICouponService getCouponService() {
        if(LogicUtils.isNull(couponService)) {
            //couponService = (CouponService)SpringContextUtil.getBean("couponService");
        }
        return couponService;
    }

    public void setCouponService(ICouponService couponService) {
        this.couponService = couponService;
    }

    @Override
    public void run() {
        couponService.updateStatusAuto();
    }
}
