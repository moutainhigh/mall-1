package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.CommodityCategory;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface CommodityCategoryMapper {
    @Delete({
        "delete from commodity_category",
        "where COCA_ID = #{cocaId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer cocaId);

    @Insert({
        "insert into commodity_category (COCA_ID, RECOMMEND_VALUE, ",
        "CATEGORY_ID, COMMODITY_ID)",
        "values (#{cocaId,jdbcType=INTEGER}, #{recommendValue,jdbcType=INTEGER}, ",
        "#{categoryId,jdbcType=INTEGER}, #{commodityId,jdbcType=INTEGER})"
    })
    int insert(CommodityCategory record);

    @Select({
        "select",
        "COCA_ID, RECOMMEND_VALUE, CATEGORY_ID, COMMODITY_ID",
        "from commodity_category",
        "where COCA_ID = #{cocaId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="COCA_ID", property="cocaId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="RECOMMEND_VALUE", property="recommendValue", jdbcType=JdbcType.INTEGER),
        @Result(column="CATEGORY_ID", property="categoryId", jdbcType=JdbcType.INTEGER),
        @Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER)
    })
    CommodityCategory selectByPrimaryKey(Integer cocaId);

    @Select({
        "select",
        "COCA_ID, RECOMMEND_VALUE, CATEGORY_ID, COMMODITY_ID",
        "from commodity_category"
    })
    @Results({
        @Result(column="COCA_ID", property="cocaId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="RECOMMEND_VALUE", property="recommendValue", jdbcType=JdbcType.INTEGER),
        @Result(column="CATEGORY_ID", property="categoryId", jdbcType=JdbcType.INTEGER),
        @Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER)
    })
    List<CommodityCategory> selectAll();

    @Update({
        "update commodity_category",
        "set RECOMMEND_VALUE = #{recommendValue,jdbcType=INTEGER},",
          "CATEGORY_ID = #{categoryId,jdbcType=INTEGER},",
          "COMMODITY_ID = #{commodityId,jdbcType=INTEGER}",
        "where COCA_ID = #{cocaId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CommodityCategory record);

    @Select({
            "select",
            "COCA_ID, RECOMMEND_VALUE, CATEGORY_ID, COMMODITY_ID",
            "from commodity_category",
            "where CATEGORY_ID = #{categoryId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="COCA_ID", property="cocaId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="RECOMMEND_VALUE", property="recommendValue", jdbcType=JdbcType.INTEGER),
            @Result(column="CATEGORY_ID", property="categoryId", jdbcType=JdbcType.INTEGER),
            @Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER)
    })
    List<CommodityCategory> selectByCategoryId(Integer categoryId);
}