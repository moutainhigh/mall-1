/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.view.StatisticsMonthMoneyView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author chenxing
 */
public interface StatisticsMonthMoneyViewDao extends JpaRepository<StatisticsMonthMoneyView, Integer>, JpaSpecificationExecutor<StatisticsMonthMoneyView> {


    /**
     * 每月的总订单金额
     *
     * @param year
     * @return
     */
    @Query("select orderView from StatisticsMonthMoneyView orderView where orderView.year=?1 order by orderView.month asc")
    public List<StatisticsMonthMoneyView> findMonthMoney(int year);


}

