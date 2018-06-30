package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.view.StatisticsDayOrderPaidView;
import com.yunxin.cb.mall.view.StatisticsDayOrderView;
import com.yunxin.cb.mall.view.StatisticsMonthOrderPaidView;
import com.yunxin.cb.mall.view.StatisticsMonthOrderView;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by chenxing on 16/4/12.
 */
public interface IStatisticsOrderService {

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<StatisticsDayOrderView> getDayOrder(int year, int month);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<StatisticsDayOrderPaidView> getDayOrderPaid(int year, int month);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<StatisticsMonthOrderView> getMonthOrder(int year);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<StatisticsMonthOrderPaidView> getMonthOrderPaid(int year);
}
