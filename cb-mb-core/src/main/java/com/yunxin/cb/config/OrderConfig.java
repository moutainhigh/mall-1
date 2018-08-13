package com.yunxin.cb.config;

/**
 * Created by gonglei on 16/1/31.
 */
public enum OrderConfig {

    ORDER_OVER_TIME(1),//订单超时时间，单位小时 1
    ORDER_RECEIVED_TIME(1),//订单自动收货时间，单位周 1
    ORDER_COMPLETE_TIME(1),//订单自动收货完成时间，单位周 1
    ;

    private Integer time;

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    OrderConfig(Integer time){
        this.time = time;
    }
}
