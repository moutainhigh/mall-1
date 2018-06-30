package com.yunxin.cb.mall.web.job;

import com.yunxin.cb.mall.web.thread.ChangeActivityStatusThread;

/**
 * Created by k001389 on 2014/9/15.
 */
public class ChangeActivityStatusAuto {


    public void execute( )  {
        ChangeActivityStatusThread changeActivityStatusThread = new ChangeActivityStatusThread();
        changeActivityStatusThread.run();
    }
}
