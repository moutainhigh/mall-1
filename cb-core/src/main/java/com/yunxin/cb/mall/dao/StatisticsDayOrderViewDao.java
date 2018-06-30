/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.view.StatisticsDayOrderView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author chenxing
 */
public interface StatisticsDayOrderViewDao extends JpaRepository<StatisticsDayOrderView, Integer>, JpaSpecificationExecutor<StatisticsDayOrderView> {


    /**
     * 每天的总订单量
     *
     * @param year
     * @param month
     * @return
     */
    @Query("select orderView from StatisticsDayOrderView orderView where orderView.year=?1 and orderView.month=?2 order by orderView.createTime asc")
    public List<StatisticsDayOrderView> findDayOrder(int year, int month);


}

