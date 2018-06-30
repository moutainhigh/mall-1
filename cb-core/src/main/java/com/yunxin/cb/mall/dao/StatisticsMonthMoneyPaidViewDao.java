/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.view.StatisticsMonthMoneyPaidView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author chenxing
 */
public interface StatisticsMonthMoneyPaidViewDao extends JpaRepository<StatisticsMonthMoneyPaidView, Integer>, JpaSpecificationExecutor<StatisticsMonthMoneyPaidView> {


    /**
     * 每月的已付款金额
     *
     * @param year
     * @return
     */
    @Query("select orderView from StatisticsMonthMoneyPaidView orderView where orderView.year=?1 order by orderView.month asc")
    public List<StatisticsMonthMoneyPaidView> findMonthMoney(int year);


}

