package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.Catalog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface CatalogMapper {
    /**
     * 查询所有商品大分类
     * @return
     */
    @Select({
            "select  c.CATALOG_ID from catalog c WHERE c.CATALOG_ID != c.PARENT_CATALOG_ID and c.PARENT_CATALOG_ID =1 and c.ENABLED =1"
    })
    @Results({
            @Result(column="CATALOG_ID", property="catalogId", jdbcType=JdbcType.INTEGER, id=true),
    })
    List<Catalog> selectAll();
    /**
     * 根据商品ID查询商品属于哪一分类
     */
    @Select({
            "SELECT b.CATALOG_ID,b.PARENT_CATALOG_ID FROM `commodity` a left join CATALOG b on a.CATALOG_ID = b.CATALOG_ID WHERE a.COMMODITY_ID = #{commodityId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="CATALOG_ID", property="catalogId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="PARENT_CATALOG_ID", property="parentCatalogId", jdbcType=JdbcType.INTEGER, id=true),
    })
    Catalog selectByCommodityId(int commodityId);
    /**
     * 查询分类的父分类
     */
    @Select({
            "select  c.CATALOG_ID,c.PARENT_CATALOG_ID from catalog c WHERE c.CATALOG_ID = #{parentCatalogId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="CATALOG_ID", property="catalogId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="PARENT_CATALOG_ID", property="parentCatalogId", jdbcType=JdbcType.INTEGER, id=true),
    })
    Catalog selectByParentCatalogId(int parentCatalogId);
}
