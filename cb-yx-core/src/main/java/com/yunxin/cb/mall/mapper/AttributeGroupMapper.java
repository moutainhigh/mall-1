package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.AttributeGroup;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface AttributeGroupMapper {
    @Delete({
        "delete from attribute_group",
        "where GROUP_ID = #{groupId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer groupId);

    @Insert({
        "insert into attribute_group (GROUP_ID, CREATE_TIME, ",
        "GROUP_NAME, SHOW_AS_IMAGE, ",
        "COMMODITY_ID)",
        "values (#{groupId,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{groupName,jdbcType=VARCHAR}, #{showAsImage,jdbcType=BIT}, ",
        "#{commodityId,jdbcType=INTEGER})"
    })
    int insert(AttributeGroup record);

    @Select({
        "select",
        "GROUP_ID, CREATE_TIME, GROUP_NAME, SHOW_AS_IMAGE, COMMODITY_ID",
        "from attribute_group",
        "where GROUP_ID = #{groupId,jdbcType=INTEGER} order by GROUP_ID"
    })
    @Results({
        @Result(column="GROUP_ID", property="groupId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="GROUP_NAME", property="groupName", jdbcType=JdbcType.VARCHAR),
        @Result(column="SHOW_AS_IMAGE", property="showAsImage", jdbcType=JdbcType.BIT),
        @Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER)
    })
    AttributeGroup selectByPrimaryKey(Integer groupId);

    @Select({
        "select",
        "GROUP_ID, CREATE_TIME, GROUP_NAME, SHOW_AS_IMAGE, COMMODITY_ID",
        "from attribute_group"
    })
    @Results({
        @Result(column="GROUP_ID", property="groupId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="GROUP_NAME", property="groupName", jdbcType=JdbcType.VARCHAR),
        @Result(column="SHOW_AS_IMAGE", property="showAsImage", jdbcType=JdbcType.BIT),
        @Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER)
    })
    List<AttributeGroup> selectAll();

    @Select({
            "select",
            "GROUP_ID, CREATE_TIME, GROUP_NAME, SHOW_AS_IMAGE, COMMODITY_ID",
            "from attribute_group where COMMODITY_ID = #{commodityId}"
    })
    @Results({
            @Result(column="GROUP_ID", property="groupId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="GROUP_NAME", property="groupName", jdbcType=JdbcType.VARCHAR),
            @Result(column="SHOW_AS_IMAGE", property="showAsImage", jdbcType=JdbcType.BIT),
            @Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER),
            @Result(column = "GROUP_ID",property = "attributes",
                    many = @Many(select = "com.yunxin.cb.mall.dao.AttributeMapper.selectByGroupId"))
    })
    List<AttributeGroup> getAttributeGroupsByCommodityId(int commodityId);

    @Update({
        "update attribute_group",
        "set CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "GROUP_NAME = #{groupName,jdbcType=VARCHAR},",
          "SHOW_AS_IMAGE = #{showAsImage,jdbcType=BIT},",
          "COMMODITY_ID = #{commodityId,jdbcType=INTEGER}",
        "where GROUP_ID = #{groupId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AttributeGroup record);
}