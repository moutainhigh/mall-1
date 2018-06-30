package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.StatisticsDayOrderPaidViewDao;
import com.yunxin.cb.mall.dao.StatisticsDayOrderViewDao;
import com.yunxin.cb.mall.dao.StatisticsMonthOrderPaidViewDao;
import com.yunxin.cb.mall.dao.StatisticsMonthOrderViewDao;
import com.yunxin.cb.mall.service.IStatisticsOrderService;
import com.yunxin.cb.mall.view.StatisticsDayOrderPaidView;
import com.yunxin.cb.mall.view.StatisticsDayOrderView;
import com.yunxin.cb.mall.view.StatisticsMonthOrderPaidView;
import com.yunxin.cb.mall.view.StatisticsMonthOrderView;
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
public class StatisticsOrderService implements IStatisticsOrderService {

    @Resource
    private StatisticsDayOrderViewDao statisticsDayOrderViewDao;

    @Resource
    private StatisticsDayOrderPaidViewDao statisticsDayOrderPaidViewDao;

    @Resource
    private StatisticsMonthOrderViewDao statisticsMonthOrderViewDao;

    @Resource
    private StatisticsMonthOrderPaidViewDao statisticsMonthOrderPaidViewDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<StatisticsDayOrderView> getDayOrder(int year, int month) {

        return statisticsDayOrderViewDao.findDayOrder(year, month);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<StatisticsDayOrderPaidView> getDayOrderPaid(int year, int month) {

        return statisticsDayOrderPaidViewDao.findDayOrder(year, month);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<StatisticsMonthOrderView> getMonthOrder(int year) {

        return statisticsMonthOrderViewDao.findMonthOrder(year);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<StatisticsMonthOrderPaidView> getMonthOrderPaid(int year) {

        return statisticsMonthOrderPaidViewDao.findMonthOrder(year);
    }
}
