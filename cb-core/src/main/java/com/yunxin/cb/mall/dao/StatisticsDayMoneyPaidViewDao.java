/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.view.StatisticsDayMoneyPaidView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author chenxing
 */
public interface StatisticsDayMoneyPaidViewDao extends JpaRepository<StatisticsDayMoneyPaidView, Integer>, JpaSpecificationExecutor<StatisticsDayMoneyPaidView> {


    /**
     * 每天的已付款金额
     *
     * @param year
     * @param month
     * @return
     */
    @Query("select orderView from StatisticsDayMoneyPaidView orderView where orderView.year=?1 and orderView.month=?2 order by orderView.createTime asc")
    public List<StatisticsDayMoneyPaidView> findDayMoney(int year, int month);


}

