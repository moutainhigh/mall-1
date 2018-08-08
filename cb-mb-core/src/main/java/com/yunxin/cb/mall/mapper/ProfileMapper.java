package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.Profile;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface ProfileMapper {
    @Delete({
        "delete from profile",
        "where FILE_ID = #{fileId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer fileId);

    @Insert({
        "insert into profile (FILE_ID, PROFILE_NAME, ",
        "FILE_VALUE, IS_PICTURE, ",
        "REMARKS)",
        "values (#{fileId,jdbcType=INTEGER}, #{profileName,jdbcType=VARCHAR}, ",
        "#{fileValue,jdbcType=VARCHAR}, #{isPicture,jdbcType=INTEGER}, ",
        "#{remarks,jdbcType=VARCHAR})"
    })
    int insert(Profile record);

    @Select({
        "select",
        "FILE_ID, PROFILE_NAME, FILE_VALUE, IS_PICTURE, REMARKS",
        "from profile",
        "where FILE_ID = #{fileId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="FILE_ID", property="fileId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="PROFILE_NAME", property="profileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="FILE_VALUE", property="fileValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="IS_PICTURE", property="isPicture", jdbcType=JdbcType.INTEGER),
        @Result(column="REMARKS", property="remarks", jdbcType=JdbcType.VARCHAR)
    })
    Profile selectByPrimaryKey(Integer fileId);

    @Select({
        "select",
        "FILE_ID, PROFILE_NAME, FILE_VALUE, IS_PICTURE, REMARKS",
        "from profile"
    })
    @Results({
        @Result(column="FILE_ID", property="fileId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="PROFILE_NAME", property="profileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="FILE_VALUE", property="fileValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="IS_PICTURE", property="isPicture", jdbcType=JdbcType.INTEGER),
        @Result(column="REMARKS", property="remarks", jdbcType=JdbcType.VARCHAR)
    })
    List<Profile> selectAll();

    @Update({
        "update profile",
        "set PROFILE_NAME = #{profileName,jdbcType=VARCHAR},",
          "FILE_VALUE = #{fileValue,jdbcType=VARCHAR},",
          "IS_PICTURE = #{isPicture,jdbcType=INTEGER},",
          "REMARKS = #{remarks,jdbcType=VARCHAR}",
        "where FILE_ID = #{fileId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Profile record);

    @Select({
            "select",
            "FILE_ID, PROFILE_NAME, FILE_VALUE, IS_PICTURE, REMARKS",
            "from profile",
            "where PROFILE_NAME = #{profileName,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column="FILE_ID", property="fileId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="PROFILE_NAME", property="profileName", jdbcType=JdbcType.VARCHAR),
            @Result(column="FILE_VALUE", property="fileValue", jdbcType=JdbcType.VARCHAR),
            @Result(column="IS_PICTURE", property="isPicture", jdbcType=JdbcType.INTEGER),
            @Result(column="REMARKS", property="remarks", jdbcType=JdbcType.VARCHAR)
    })
    Profile getProfileByName(String profileName);
}