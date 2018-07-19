package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.PriceSection;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface PriceSectionMapper {
    @Delete({
        "delete from price_section",
        "where SECTION_ID = #{sectionId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer sectionId);

    @Insert({
        "insert into price_section (SECTION_ID, ENABLED, ",
        "END_PRICE, REMARK, ",
        "SECTION_NO, START_PRICE)",
        "values (#{sectionId,jdbcType=INTEGER}, #{enabled,jdbcType=BIT}, ",
        "#{endPrice,jdbcType=INTEGER}, #{remark,jdbcType=VARCHAR}, ",
        "#{sectionNo,jdbcType=VARCHAR}, #{startPrice,jdbcType=INTEGER})"
    })
    int insert(PriceSection record);

    @Select({
        "select",
        "SECTION_ID, ENABLED, END_PRICE, REMARK, SECTION_NO, START_PRICE",
        "from price_section",
        "where SECTION_ID = #{sectionId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="SECTION_ID", property="sectionId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
        @Result(column="END_PRICE", property="endPrice", jdbcType=JdbcType.INTEGER),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="SECTION_NO", property="sectionNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="START_PRICE", property="startPrice", jdbcType=JdbcType.INTEGER)
    })
    PriceSection selectByPrimaryKey(Integer sectionId);

    @Select({
        "select",
        "SECTION_ID, ENABLED, END_PRICE, REMARK, SECTION_NO, START_PRICE",
        "from price_section"
    })
    @Results({
        @Result(column="SECTION_ID", property="sectionId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
        @Result(column="END_PRICE", property="endPrice", jdbcType=JdbcType.INTEGER),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="SECTION_NO", property="sectionNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="START_PRICE", property="startPrice", jdbcType=JdbcType.INTEGER)
    })
    List<PriceSection> selectAll();

    @Update({
        "update price_section",
        "set ENABLED = #{enabled,jdbcType=BIT},",
          "END_PRICE = #{endPrice,jdbcType=INTEGER},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "SECTION_NO = #{sectionNo,jdbcType=VARCHAR},",
          "START_PRICE = #{startPrice,jdbcType=INTEGER}",
        "where SECTION_ID = #{sectionId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(PriceSection record);
}