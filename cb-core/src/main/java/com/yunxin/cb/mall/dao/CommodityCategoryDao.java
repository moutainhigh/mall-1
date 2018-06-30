package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.entity.Category;
import com.yunxin.cb.mall.entity.Commodity;
import com.yunxin.cb.mall.entity.CommodityCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by gonglei on 16/1/22.
 */
public interface CommodityCategoryDao extends JpaRepository<CommodityCategory, Integer>, JpaSpecificationExecutor<CommodityCategory> {

    /**
     * 查询同类销量最高的商品
     *
     * @param commodity
     * @return
     */
    @Query("select comm from CommodityCategory cc left join cc.commodity comm where cc.category in (select ctd.category from CommodityCategory ctd where ctd.commodity=?1) order by comm.saleNum desc")
    List<Commodity> findByCategoryCommodityCategoryOrderBySaleNum(Commodity commodity, Pageable pageable);

    CommodityCategory findTopByCategoryAndCommodity_CommodityId(Category category, int commodityId);

    Long countByCategoryAndCommodity_CommodityId(Category category, int commodityId);

}
