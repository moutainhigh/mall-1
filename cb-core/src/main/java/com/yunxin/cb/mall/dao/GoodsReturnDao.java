package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.GoodsReturn;
import com.yunxin.cb.mall.entity.meta.GoodsReturnStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface GoodsReturnDao extends JpaRepository<GoodsReturn, Integer>, JpaSpecificationExecutor<GoodsReturn> {

    @Query("select gr from GoodsReturn gr left join fetch gr.customer c left join fetch gr.orderItem od where gr.goodsReturnId=?1")
    public GoodsReturn getGoodsReturnById(int goodsReturnId);

    @Modifying
    @Query("update GoodsReturn g set g.status=?1 where g.goodsReturnId=?2")
    public void updateGoodsReturn(GoodsReturnStatus status, int goodsReturnId);


}
