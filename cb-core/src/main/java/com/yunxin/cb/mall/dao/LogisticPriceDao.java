package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.LogisticPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by gonglei on 16/1/31.
 */
public interface LogisticPriceDao extends JpaRepository<LogisticPrice, Integer>, JpaSpecificationExecutor<LogisticPrice> {

    List<LogisticPrice> findByLogistic_LogisticIdOrderByWeightPriceAsc(int logisticId);

    @Query("select lp from LogisticPrice lp left join fetch lp.logistic l where l.logisticId = ?1")
    List<LogisticPrice> getLogisticPricesByLogisticId(int logisticId);
}
