package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Combination;
import com.yunxin.cb.mall.entity.meta.PublishState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * Created by x001393 on 2014/11/10.
 */
public interface CombinationDao extends JpaRepository<Combination, Integer>, JpaSpecificationExecutor<Combination> {

    //@Query("select com from Combination com left join fetch com.combinedCommodity  left join fetch com.combinedCommodity.catalog left join fetch com.combinedCommodity.brand where com.commodity.commodityId=?1")
    List<Combination> findByCommodity_CommodityId(int commodityId);

    @Query("select com from Combination com left join fetch com.combinedCommodity ccm where com.commodity.commodityId=?1 and ccm.publishState=?2")
    List<Combination> findByCommodity_CommodityId(int commodityId, PublishState publishState);

    @Query("select count(com.combinationId) from Combination com where com.commodity.commodityId =?1 and com.combinedCommodity.commodityId =?2")
    long countByCommodityIdAndCombinedCommodityId(int commodityId, int combinedCommodityId);
}
