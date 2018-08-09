/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.view.StatisticsDayBillView;
import com.yunxin.cb.mall.view.StatisticsDayOrderPaidView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 */
public interface StatisticsDayBillViewDao extends JpaRepository<StatisticsDayBillView, Integer>, JpaSpecificationExecutor<StatisticsDayBillView> {


    /**
     * 每天的账单
     *
     * @param year
     * @param month
     * @return
     */
    @Query("select billView from StatisticsDayBillView billView where billView.year=?1 and billView.month=?2 order by billView.createTime asc")
    List<StatisticsDayBillView> findDayBill(int year, int month);

    /**
     * 每月的账单
     *
     * @param year
     * @return
     */
    @Query(nativeQuery = true,
            value = "select bill_id,create_time,`year`,`month`,`day`,`type`,sum(amount) as amount from statistics_day_bill_view where `year`=?1 group by `month`,`type` order by `month` asc"
            )

    List<StatisticsDayBillView> findMonthBill(int year);


}

