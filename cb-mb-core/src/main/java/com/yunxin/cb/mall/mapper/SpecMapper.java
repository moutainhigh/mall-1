package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.Spec;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface SpecMapper {
    @Delete({
        "delete from spec",
        "where SPEC_ID = #{specId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer specId);

    @Insert({
        "insert into spec (SPEC_ID, REMARK, ",
        "SPEC_NAME, CATALOG_ID)",
        "values (#{specId,jdbcType=INTEGER}, #{remark,jdbcType=VARCHAR}, ",
        "#{specName,jdbcType=VARCHAR}, #{catalogId,jdbcType=INTEGER})"
    })
    int insert(Spec record);

    @Select({
        "select",
        "SPEC_ID, REMARK, SPEC_NAME, CATALOG_ID",
        "from spec",
        "where SPEC_ID = #{specId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="SPEC_ID", property="specId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="SPEC_NAME", property="specName", jdbcType=JdbcType.VARCHAR),
        @Result(column="CATALOG_ID", property="catalogId", jdbcType=JdbcType.INTEGER)
    })
    Spec selectByPrimaryKey(Integer specId);

    @Select({
        "select",
        "SPEC_ID, REMARK, SPEC_NAME, CATALOG_ID",
        "from spec"
    })
    @Results({
        @Result(column="SPEC_ID", property="specId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="SPEC_NAME", property="specName", jdbcType=JdbcType.VARCHAR),
        @Result(column="CATALOG_ID", property="catalogId", jdbcType=JdbcType.INTEGER)
    })
    List<Spec> selectAll();

    @Update({
        "update spec",
        "set REMARK = #{remark,jdbcType=VARCHAR},",
          "SPEC_NAME = #{specName,jdbcType=VARCHAR},",
          "CATALOG_ID = #{catalogId,jdbcType=INTEGER}",
        "where SPEC_ID = #{specId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Spec record);
}