package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.StatisticsDayMoneyPaidViewDao;
import com.yunxin.cb.mall.dao.StatisticsDayMoneyViewDao;
import com.yunxin.cb.mall.dao.StatisticsMonthMoneyPaidViewDao;
import com.yunxin.cb.mall.dao.StatisticsMonthMoneyViewDao;
import com.yunxin.cb.mall.service.IStatisticsMoneyService;
import com.yunxin.cb.mall.view.StatisticsDayMoneyPaidView;
import com.yunxin.cb.mall.view.StatisticsDayMoneyView;
import com.yunxin.cb.mall.view.StatisticsMonthMoneyPaidView;
import com.yunxin.cb.mall.view.StatisticsMonthMoneyView;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单统计
 * Created by chenxing on 16/4/12.
 */
@Service
@Transactional
public class StatisticsMoneyService implements IStatisticsMoneyService {

    @Resource
    private StatisticsDayMoneyViewDao statisticsDayMoneyViewDao;

    @Resource
    private StatisticsDayMoneyPaidViewDao statisticsDayMoneyPaidViewDao;

    @Resource
    private StatisticsMonthMoneyViewDao statisticsMonthMoneyViewDao;

    @Resource
    private StatisticsMonthMoneyPaidViewDao statisticsMonthMoneyPaidViewDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<StatisticsDayMoneyView> getDayMoney(int year, int month) {

        return statisticsDayMoneyViewDao.findDayMoney(year, month);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<StatisticsDayMoneyPaidView> getDayMoneyPaid(int year, int month) {

        return statisticsDayMoneyPaidViewDao.findDayMoney(year, month);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<StatisticsMonthMoneyView> getMonthMoney(int year) {

        return statisticsMonthMoneyViewDao.findMonthMoney(year);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<StatisticsMonthMoneyPaidView> getMonthMoneyPaid(int year) {

        return statisticsMonthMoneyPaidViewDao.findMonthMoney(year);
    }
}
