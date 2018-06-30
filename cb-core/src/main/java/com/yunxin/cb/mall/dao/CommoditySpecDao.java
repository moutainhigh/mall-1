package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.entity.CommoditySpec;
import com.yunxin.cb.mall.entity.CommoditySpecId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by gonglei on 16/1/19.
 */
public interface CommoditySpecDao extends JpaRepository<CommoditySpec, CommoditySpecId>, JpaSpecificationExecutor<CommoditySpec> {

    @Query("select cs from CommoditySpec cs left join fetch cs.commodity com left join fetch cs.spec where com.catalog.catalogId=?1")
    List<CommoditySpec> getCommoditySpecsByCatalogId(final int catalogId);

    @Query("select cs from CommoditySpec cs left join fetch cs.commodity com left join fetch cs.spec where com.commodityId=?1")
    List<CommoditySpec> getCommoditySpecsByCommodityId(final int commodityId);

    @Query("select cs from CommoditySpec cs left join fetch cs.spec where cs.commodity=?1")
    List<CommoditySpec> getCommoditySpecsByCommodity(Commodity commodity);
}
