package com.yunxin.cb.mall.web.job;

import com.yunxin.cb.mall.web.thread.ChangeCouponScopeStatusThread;

/**
 * Created by k001389 on 2015/2/11.
 */
public class ChangeCouponScopeAuto {
    public void execute( )  {
        ChangeCouponScopeStatusThread changeCouponScopeStatusThread = new ChangeCouponScopeStatusThread();
        changeCouponScopeStatusThread.run();
    }
}
