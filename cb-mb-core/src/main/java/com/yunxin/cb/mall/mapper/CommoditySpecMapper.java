package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.CommoditySpec;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface CommoditySpecMapper {
    @Delete({
        "delete from commodity_spec",
        "where COMMODITY_ID = #{commodityId,jdbcType=INTEGER}",
          "and SPEC_ID = #{specId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(@Param("commodityId") Integer commodityId, @Param("specId") Integer specId);

    @Insert({
        "insert into commodity_spec (COMMODITY_ID, SPEC_ID, ",
        "VALUE)",
        "values (#{commodityId,jdbcType=INTEGER}, #{specId,jdbcType=INTEGER}, ",
        "#{value,jdbcType=VARCHAR})"
    })
    int insert(CommoditySpec record);

    @Select({
        "select",
        "COMMODITY_ID, SPEC_ID, VALUE",
        "from commodity_spec",
        "where COMMODITY_ID = #{commodityId,jdbcType=INTEGER}",
          "and SPEC_ID = #{specId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="SPEC_ID", property="specId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="VALUE", property="value", jdbcType=JdbcType.VARCHAR)
    })
    CommoditySpec selectByPrimaryKey(@Param("commodityId") Integer commodityId, @Param("specId") Integer specId);

    @Select({
        "select",
        "COMMODITY_ID, SPEC_ID, VALUE",
        "from commodity_spec"
    })
    @Results({
        @Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="SPEC_ID", property="specId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="VALUE", property="value", jdbcType=JdbcType.VARCHAR)
    })
    List<CommoditySpec> selectAll();

    @Update({
        "update commodity_spec",
        "set VALUE = #{value,jdbcType=VARCHAR}",
        "where COMMODITY_ID = #{commodityId,jdbcType=INTEGER}",
          "and SPEC_ID = #{specId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CommoditySpec record);
}