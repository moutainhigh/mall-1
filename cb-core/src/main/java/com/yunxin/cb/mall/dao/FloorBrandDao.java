package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.FloorBrand;
import com.yunxin.cb.mall.entity.HomeFloor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FloorBrandDao extends JpaRepository<FloorBrand, Integer>, JpaSpecificationExecutor<FloorBrand> {

    @Modifying
    @Query("delete from FloorBrand p  where p.homeFloor=?1")
    public void emptyByHomeFloor(HomeFloor homeFloor);
}
