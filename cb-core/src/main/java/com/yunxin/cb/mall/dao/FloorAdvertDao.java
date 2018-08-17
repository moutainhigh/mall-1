package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.FloorAdvert;
import com.yunxin.cb.mall.entity.HomeFloor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FloorAdvertDao extends JpaRepository<FloorAdvert, Integer>, JpaSpecificationExecutor<FloorAdvert> {
    @Modifying
    @Query("delete from FloorBrand p  where p.homeFloor=?1")
    public void emptyByHomeFloor(HomeFloor homeFloor);
}
