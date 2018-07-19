package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.Attribute;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface AttributeMapper {
    @Delete({
        "delete from attribute",
        "where ATTRIBUTE_ID = #{attributeId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer attributeId);

    @Insert({
        "insert into attribute (ATTRIBUTE_ID, ATTRIBUTE_NAME, ",
        "IMAGE_PATH, SORT_ORDER, ",
        "GROUP_ID)",
        "values (#{attributeId,jdbcType=INTEGER}, #{attributeName,jdbcType=VARCHAR}, ",
        "#{imagePath,jdbcType=VARCHAR}, #{sortOrder,jdbcType=SMALLINT}, ",
        "#{groupId,jdbcType=INTEGER})"
    })
    int insert(Attribute record);

    @Select({
        "select",
        "ATTRIBUTE_ID, ATTRIBUTE_NAME, IMAGE_PATH, SORT_ORDER, GROUP_ID",
        "from attribute",
        "where ATTRIBUTE_ID = #{attributeId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ATTRIBUTE_ID", property="attributeId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ATTRIBUTE_NAME", property="attributeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="IMAGE_PATH", property="imagePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="SORT_ORDER", property="sortOrder", jdbcType=JdbcType.SMALLINT),
        @Result(column="GROUP_ID", property="groupId", jdbcType=JdbcType.INTEGER),
        @Result(column="GROUP_ID", property="attributeGroup", jdbcType=JdbcType.INTEGER,
                one = @One(select = "com.yunxin.cb.mall.mapper.AttributeGroupMapper.selectByPrimaryKey"))
    })
    Attribute selectByPrimaryKey(Integer attributeId);

    @Select({
        "select",
        "ATTRIBUTE_ID, ATTRIBUTE_NAME, IMAGE_PATH, SORT_ORDER, GROUP_ID",
        "from attribute"
    })
    @Results({
        @Result(column="ATTRIBUTE_ID", property="attributeId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ATTRIBUTE_NAME", property="attributeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="IMAGE_PATH", property="imagePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="SORT_ORDER", property="sortOrder", jdbcType=JdbcType.SMALLINT),
        @Result(column="GROUP_ID", property="groupId", jdbcType=JdbcType.INTEGER)
    })
    List<Attribute> selectAll();

    @Update({
        "update attribute",
        "set ATTRIBUTE_NAME = #{attributeName,jdbcType=VARCHAR},",
          "IMAGE_PATH = #{imagePath,jdbcType=VARCHAR},",
          "SORT_ORDER = #{sortOrder,jdbcType=SMALLINT},",
          "GROUP_ID = #{groupId,jdbcType=INTEGER}",
        "where ATTRIBUTE_ID = #{attributeId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Attribute record);
}