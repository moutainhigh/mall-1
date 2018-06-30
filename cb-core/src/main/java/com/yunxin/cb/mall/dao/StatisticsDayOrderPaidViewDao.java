/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.view.StatisticsDayOrderPaidView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author chenxing
 */
public interface StatisticsDayOrderPaidViewDao extends JpaRepository<StatisticsDayOrderPaidView, Integer>, JpaSpecificationExecutor<StatisticsDayOrderPaidView> {


    /**
     * 每天的已付款总订单量
     *
     * @param year
     * @param month
     * @return
     */
    @Query("select orderView from StatisticsDayOrderPaidView orderView where orderView.year=?1 and orderView.month=?2 order by orderView.createTime asc")
    public List<StatisticsDayOrderPaidView> findDayOrder(int year, int month);


}

