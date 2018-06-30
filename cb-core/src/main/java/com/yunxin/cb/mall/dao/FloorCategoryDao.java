package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.FloorCategory;
import com.yunxin.cb.mall.entity.HomeFloor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FloorCategoryDao extends JpaRepository<FloorCategory, Integer>, JpaSpecificationExecutor<FloorCategory> {

    @Modifying
    @Query("delete from FloorCategory p  where p.homeFloor=?1")
    public void emptyByHomeFloor(HomeFloor homeFloor);
}
