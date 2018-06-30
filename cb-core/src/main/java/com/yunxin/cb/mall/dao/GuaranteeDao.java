package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Guarantee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GuaranteeDao extends JpaRepository<Guarantee, Integer>, JpaSpecificationExecutor<Guarantee> {
    @Query("select f from Guarantee f left join fetch f.orderItem left join fetch f.customer where f.teeId=?1")
    public Guarantee findFreeRepairCleanById(int freeRepairCleanId);

    @Modifying
    @Query("update Guarantee f set f.freeCleanCounts =?1 where f.teeId = ?2")
    public void updateFreeCleanCountById(int freeCleanCount, int freeRepairCleanId);

    @Modifying
    @Query("update Guarantee f set f.freeRepairCounts =?1 where f.teeId = ?2")
    public void updateFreeRepairCountById(int freeRepairCount, int freeRepairCleanId);

}
