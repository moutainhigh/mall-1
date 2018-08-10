package com.yunxin.cb.mall.service.imp;

import com.yunxin.cb.mall.dao.StatisticsDayBillViewDao;
import com.yunxin.cb.mall.service.IStatisticsBillService;
import com.yunxin.cb.mall.view.StatisticsDayBillView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 账单统计
 */
@Service
@Transactional
public class StatisticsBillService implements IStatisticsBillService {

    @Autowired
    private StatisticsDayBillViewDao statisticsDayBillViewDao;

    @Override
    public List<StatisticsDayBillView> getDayBill(int year, int month) {

        return statisticsDayBillViewDao.findDayBill(year, month);
    }

    @Override
    public List<StatisticsDayBillView> getMonthBill(int year) {
        return statisticsDayBillViewDao.findMonthBill(year);
    }

}
