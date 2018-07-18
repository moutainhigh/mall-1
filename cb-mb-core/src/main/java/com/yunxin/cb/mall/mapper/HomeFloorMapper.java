package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.HomeFloor;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
@Mapper
public interface HomeFloorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table home_floor
     *
     * @mbg.generated Wed Jul 18 14:33:48 CST 2018
     */
    @Delete({
        "delete from home_floor",
        "where FLOOR_ID = #{floorId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer floorId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table home_floor
     *
     * @mbg.generated Wed Jul 18 14:33:48 CST 2018
     */
    @Insert({
        "insert into home_floor (FLOOR_ID, CATEGORY_AMOUNT, ",
        "COMMODITY_AMOUNT, ENABLED, ",
        "FLOOR_LAYOUT, FLOOR_NAME, ",
        "ICON_PATH, IMAGE_PATH, ",
        "REMARK, SORT_ORDER, ",
        "BRAND_AMOUNT)",
        "values (#{floorId,jdbcType=INTEGER}, #{categoryAmount,jdbcType=INTEGER}, ",
        "#{commodityAmount,jdbcType=INTEGER}, #{enabled,jdbcType=BIT}, ",
        "#{floorLayout,jdbcType=INTEGER}, #{floorName,jdbcType=VARCHAR}, ",
        "#{iconPath,jdbcType=VARCHAR}, #{imagePath,jdbcType=VARCHAR}, ",
        "#{remark,jdbcType=VARCHAR}, #{sortOrder,jdbcType=INTEGER}, ",
        "#{brandAmount,jdbcType=INTEGER})"
    })
    int insert(HomeFloor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table home_floor
     *
     * @mbg.generated Wed Jul 18 14:33:48 CST 2018
     */
    @Select({
        "select",
        "FLOOR_ID, CATEGORY_AMOUNT, COMMODITY_AMOUNT, ENABLED, FLOOR_LAYOUT, FLOOR_NAME, ",
        "ICON_PATH, IMAGE_PATH, REMARK, SORT_ORDER, BRAND_AMOUNT",
        "from home_floor",
        "where FLOOR_ID = #{floorId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="FLOOR_ID", property="floorId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CATEGORY_AMOUNT", property="categoryAmount", jdbcType=JdbcType.INTEGER),
        @Result(column="COMMODITY_AMOUNT", property="commodityAmount", jdbcType=JdbcType.INTEGER),
        @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
        @Result(column="FLOOR_LAYOUT", property="floorLayout", jdbcType=JdbcType.INTEGER),
        @Result(column="FLOOR_NAME", property="floorName", jdbcType=JdbcType.VARCHAR),
        @Result(column="ICON_PATH", property="iconPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="IMAGE_PATH", property="imagePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="SORT_ORDER", property="sortOrder", jdbcType=JdbcType.INTEGER),
        @Result(column="BRAND_AMOUNT", property="brandAmount", jdbcType=JdbcType.INTEGER)
    })
    HomeFloor selectByPrimaryKey(Integer floorId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table home_floor
     *
     * @mbg.generated Wed Jul 18 14:33:48 CST 2018
     */
    @Select({
        "select",
        "FLOOR_ID, CATEGORY_AMOUNT, COMMODITY_AMOUNT, ENABLED, FLOOR_LAYOUT, FLOOR_NAME, ",
        "ICON_PATH, IMAGE_PATH, REMARK, SORT_ORDER, BRAND_AMOUNT",
        "from home_floor"
    })
    @Results({
        @Result(column="FLOOR_ID", property="floorId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CATEGORY_AMOUNT", property="categoryAmount", jdbcType=JdbcType.INTEGER),
        @Result(column="COMMODITY_AMOUNT", property="commodityAmount", jdbcType=JdbcType.INTEGER),
        @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
        @Result(column="FLOOR_LAYOUT", property="floorLayout", jdbcType=JdbcType.INTEGER),
        @Result(column="FLOOR_NAME", property="floorName", jdbcType=JdbcType.VARCHAR),
        @Result(column="ICON_PATH", property="iconPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="IMAGE_PATH", property="imagePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="SORT_ORDER", property="sortOrder", jdbcType=JdbcType.INTEGER),
        @Result(column="BRAND_AMOUNT", property="brandAmount", jdbcType=JdbcType.INTEGER)
    })
    List<HomeFloor> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table home_floor
     *
     * @mbg.generated Wed Jul 18 14:33:48 CST 2018
     */
    @Update({
        "update home_floor",
        "set CATEGORY_AMOUNT = #{categoryAmount,jdbcType=INTEGER},",
          "COMMODITY_AMOUNT = #{commodityAmount,jdbcType=INTEGER},",
          "ENABLED = #{enabled,jdbcType=BIT},",
          "FLOOR_LAYOUT = #{floorLayout,jdbcType=INTEGER},",
          "FLOOR_NAME = #{floorName,jdbcType=VARCHAR},",
          "ICON_PATH = #{iconPath,jdbcType=VARCHAR},",
          "IMAGE_PATH = #{imagePath,jdbcType=VARCHAR},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "SORT_ORDER = #{sortOrder,jdbcType=INTEGER},",
          "BRAND_AMOUNT = #{brandAmount,jdbcType=INTEGER}",
        "where FLOOR_ID = #{floorId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(HomeFloor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table home_floor
     *
     * @mbg.generated Wed Jul 18 14:33:48 CST 2018
     */
    @Select({
            "select",
            "FLOOR_ID, CATEGORY_AMOUNT, COMMODITY_AMOUNT, ENABLED, FLOOR_LAYOUT, FLOOR_NAME, ",
            "ICON_PATH, IMAGE_PATH, REMARK, SORT_ORDER, BRAND_AMOUNT",
            "from home_floor",
            "where ENABLED = 1"
    })
    @Results({
            @Result(column="FLOOR_ID", property="floorId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="CATEGORY_AMOUNT", property="categoryAmount", jdbcType=JdbcType.INTEGER),
            @Result(column="COMMODITY_AMOUNT", property="commodityAmount", jdbcType=JdbcType.INTEGER),
            @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
            @Result(column="FLOOR_LAYOUT", property="floorLayout", jdbcType=JdbcType.INTEGER),
            @Result(column="FLOOR_NAME", property="floorName", jdbcType=JdbcType.VARCHAR),
            @Result(column="ICON_PATH", property="iconPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="IMAGE_PATH", property="imagePath", jdbcType=JdbcType.VARCHAR),
            @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="SORT_ORDER", property="sortOrder", jdbcType=JdbcType.INTEGER),
            @Result(column="BRAND_AMOUNT", property="brandAmount", jdbcType=JdbcType.INTEGER)
    })
    List<HomeFloor> selectByEnabledAll();
}