package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.view.StatisticsDayBillView;

import java.util.List;

/**
 */
public interface IStatisticsBillService {

    List<StatisticsDayBillView> getDayBill(int year, int month);

    List<StatisticsDayBillView> getMonthBill(int year);
}
