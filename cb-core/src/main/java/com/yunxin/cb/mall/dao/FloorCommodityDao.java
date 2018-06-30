package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.FloorCommodity;
import com.yunxin.cb.mall.entity.HomeFloor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FloorCommodityDao extends JpaRepository<FloorCommodity, Integer>, JpaSpecificationExecutor<FloorCommodity> {

    @Modifying
    @Query("delete from FloorCommodity p  where p.homeFloor=?1")
    public void emptyByHomeFloor(HomeFloor homeFloor);
}
