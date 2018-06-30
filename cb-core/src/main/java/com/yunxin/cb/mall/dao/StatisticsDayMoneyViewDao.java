/**
 *
 */
package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.view.StatisticsDayMoneyView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author chenxing
 */
public interface StatisticsDayMoneyViewDao extends JpaRepository<StatisticsDayMoneyView, Integer>, JpaSpecificationExecutor<StatisticsDayMoneyView> {


    /**
     * 每天的总订单金额
     *
     * @param year
     * @param month
     * @return
     */
    @Query("select orderView from StatisticsDayMoneyView orderView where orderView.year=?1 and orderView.month=?2 order by orderView.createTime asc")
    public List<StatisticsDayMoneyView> findDayMoney(int year, int month);


}

