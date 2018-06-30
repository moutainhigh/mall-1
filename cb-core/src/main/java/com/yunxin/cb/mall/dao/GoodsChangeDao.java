package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.GoodsChange;
import com.yunxin.cb.mall.entity.meta.GoodsChangeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface GoodsChangeDao extends JpaRepository<GoodsChange, Integer>, JpaSpecificationExecutor<GoodsChange> {

    @Query("select gc from GoodsChange gc left join fetch gc.customer c left join fetch gc.orderItem od where gc.goodsChangeId=?1")
    public GoodsChange getGoodsChangeById(int goodsChangeId);

    @Modifying
    @Query("update GoodsChange g set g.status=?1, g.changeCount=?3 where g.goodsChangeId=?2")
    public void updateGoodsChange(GoodsChangeStatus status, int goodsChangeId, int goodsChangeCounts);

//	@Query("select count(*) from GoodsChange gc where gc.goodsChangeId=?1")
//	public Long findgoodsChangeCountsById(int goodsChangeId);


//    @Query("from GoodsChange gc where gc.rfid=?1")
//    public  List<GoodsChange> getGoodsChangeByRfid(String rfid);


    @Modifying
    @Query("update GoodsChange g set g.status=?1 where g.goodsChangeId=?2")
    void updateByIdAndStatus(GoodsChangeStatus status, int goodsChangeId);


}
