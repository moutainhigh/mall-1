/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.view.StatisticsMonthOrderPaidView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author chenxing
 */
public interface StatisticsMonthOrderPaidViewDao extends JpaRepository<StatisticsMonthOrderPaidView, Integer>, JpaSpecificationExecutor<StatisticsMonthOrderPaidView> {


    /**
     * 每天的已付款总订单量
     *
     * @param year
     * @return
     */
    @Query("select orderView from StatisticsMonthOrderPaidView orderView where orderView.year=?1 order by orderView.month asc")
    public List<StatisticsMonthOrderPaidView> findMonthOrder(int year);


}

