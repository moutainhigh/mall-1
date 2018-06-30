package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.view.StatisticsDayMoneyPaidView;
import com.yunxin.cb.mall.view.StatisticsDayMoneyView;
import com.yunxin.cb.mall.view.StatisticsMonthMoneyPaidView;
import com.yunxin.cb.mall.view.StatisticsMonthMoneyView;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by chenxing on 16/4/12.
 */
public interface IStatisticsMoneyService {

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<StatisticsDayMoneyView> getDayMoney(int year, int month);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<StatisticsDayMoneyPaidView> getDayMoneyPaid(int year, int month);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<StatisticsMonthMoneyView> getMonthMoney(int year);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<StatisticsMonthMoneyPaidView> getMonthMoneyPaid(int year);
}
