package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Logistic;
import com.yunxin.core.orm.BaseDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogisticDao extends JpaRepository<Logistic, Integer>, JpaSpecificationExecutor<Logistic>, BaseDao<Logistic> {

    @Modifying
    @Query("update Logistic a set a.enabled = ?1 where a.logisticId=?2")
    void enableLogisticById(boolean enabled, int logisticId);

    List<Logistic> findByEnabled(boolean b);

//    @Query("select l from Logistic l left join fetch l.logisticPrices")
//    List<Logistic> getAllLogisticAndPrices();
}
