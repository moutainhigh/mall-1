package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.Attachment;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface AttachmentMapper {
    @Delete({
        "delete from attachment",
        "where ATTACH_ID = #{attachId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer attachId);

    @Insert({
        "insert into attachment (ATTACH_ID, OBJECT_TYPE, ",
        "OBJECT_ID, BUSINESS_SCENARIO, ",
        "INPUT_ID, FILE_PATH, ",
        "FILE_NAME, FILE_TYPE, ",
        "FILE_SIZE, FS_GUID, ",
        "CREATE_TIME, STAFF_ID, ",
        "STAFF_NAME, STATE, ",
        "DESCRIPTION)",
        "values (#{attachId,jdbcType=INTEGER}, #{objectType,jdbcType=VARCHAR}, ",
        "#{objectId,jdbcType=INTEGER}, #{businessScenario,jdbcType=VARCHAR}, ",
        "#{inputId,jdbcType=VARCHAR}, #{filePath,jdbcType=VARCHAR}, ",
        "#{fileName,jdbcType=VARCHAR}, #{fileType,jdbcType=VARCHAR}, ",
        "#{fileSize,jdbcType=INTEGER}, #{fsGuid,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{staffId,jdbcType=INTEGER}, ",
        "#{staffName,jdbcType=VARCHAR}, #{state,jdbcType=VARCHAR}, ",
        "#{description,jdbcType=VARCHAR})"
    })
    int insert(Attachment record);

    @Select({
        "select",
        "ATTACH_ID, OBJECT_TYPE, OBJECT_ID, BUSINESS_SCENARIO, INPUT_ID, FILE_PATH, FILE_NAME, ",
        "FILE_TYPE, FILE_SIZE, FS_GUID, CREATE_TIME, STAFF_ID, STAFF_NAME, STATE, DESCRIPTION",
        "from attachment",
        "where ATTACH_ID = #{attachId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ATTACH_ID", property="attachId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="OBJECT_TYPE", property="objectType", jdbcType=JdbcType.VARCHAR),
        @Result(column="OBJECT_ID", property="objectId", jdbcType=JdbcType.INTEGER),
        @Result(column="BUSINESS_SCENARIO", property="businessScenario", jdbcType=JdbcType.VARCHAR),
        @Result(column="INPUT_ID", property="inputId", jdbcType=JdbcType.VARCHAR),
        @Result(column="FILE_PATH", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="FILE_NAME", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="FILE_TYPE", property="fileType", jdbcType=JdbcType.VARCHAR),
        @Result(column="FILE_SIZE", property="fileSize", jdbcType=JdbcType.INTEGER),
        @Result(column="FS_GUID", property="fsGuid", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="STAFF_ID", property="staffId", jdbcType=JdbcType.INTEGER),
        @Result(column="STAFF_NAME", property="staffName", jdbcType=JdbcType.VARCHAR),
        @Result(column="STATE", property="state", jdbcType=JdbcType.VARCHAR),
        @Result(column="DESCRIPTION", property="description", jdbcType=JdbcType.VARCHAR)
    })
    Attachment selectByPrimaryKey(Integer attachId);

    @Select({
            "select",
            "ATTACH_ID, OBJECT_TYPE, OBJECT_ID, BUSINESS_SCENARIO, INPUT_ID, FILE_PATH, FILE_NAME, ",
            "FILE_TYPE, FILE_SIZE, FS_GUID, CREATE_TIME, STAFF_ID, STAFF_NAME, STATE, DESCRIPTION",
            "from attachment",
            "where OBJECT_TYPE = #{objectType} and OBJECT_ID = #{objectId}"
    })
    @Results({
            @Result(column="ATTACH_ID", property="attachId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="OBJECT_TYPE", property="objectType", jdbcType=JdbcType.VARCHAR),
            @Result(column="OBJECT_ID", property="objectId", jdbcType=JdbcType.INTEGER),
            @Result(column="BUSINESS_SCENARIO", property="businessScenario", jdbcType=JdbcType.VARCHAR),
            @Result(column="INPUT_ID", property="inputId", jdbcType=JdbcType.VARCHAR),
            @Result(column="FILE_PATH", property="filePath", jdbcType=JdbcType.VARCHAR),
            @Result(column="FILE_NAME", property="fileName", jdbcType=JdbcType.VARCHAR),
            @Result(column="FILE_TYPE", property="fileType", jdbcType=JdbcType.VARCHAR),
            @Result(column="FILE_SIZE", property="fileSize", jdbcType=JdbcType.INTEGER),
            @Result(column="FS_GUID", property="fsGuid", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="STAFF_ID", property="staffId", jdbcType=JdbcType.INTEGER),
            @Result(column="STAFF_NAME", property="staffName", jdbcType=JdbcType.VARCHAR),
            @Result(column="STATE", property="state", jdbcType=JdbcType.VARCHAR),
            @Result(column="DESCRIPTION", property="description", jdbcType=JdbcType.VARCHAR)
    })
    List<Attachment> selectByObjectTypeAndId(@Param("objectType") String objectType,@Param("objectId") Integer objectId);

    @Select({
        "select",
        "ATTACH_ID, OBJECT_TYPE, OBJECT_ID, BUSINESS_SCENARIO, INPUT_ID, FILE_PATH, FILE_NAME, ",
        "FILE_TYPE, FILE_SIZE, FS_GUID, CREATE_TIME, STAFF_ID, STAFF_NAME, STATE, DESCRIPTION",
        "from attachment"
    })
    @Results({
        @Result(column="ATTACH_ID", property="attachId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="OBJECT_TYPE", property="objectType", jdbcType=JdbcType.VARCHAR),
        @Result(column="OBJECT_ID", property="objectId", jdbcType=JdbcType.INTEGER),
        @Result(column="BUSINESS_SCENARIO", property="businessScenario", jdbcType=JdbcType.VARCHAR),
        @Result(column="INPUT_ID", property="inputId", jdbcType=JdbcType.VARCHAR),
        @Result(column="FILE_PATH", property="filePath", jdbcType=JdbcType.VARCHAR),
        @Result(column="FILE_NAME", property="fileName", jdbcType=JdbcType.VARCHAR),
        @Result(column="FILE_TYPE", property="fileType", jdbcType=JdbcType.VARCHAR),
        @Result(column="FILE_SIZE", property="fileSize", jdbcType=JdbcType.INTEGER),
        @Result(column="FS_GUID", property="fsGuid", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="STAFF_ID", property="staffId", jdbcType=JdbcType.INTEGER),
        @Result(column="STAFF_NAME", property="staffName", jdbcType=JdbcType.VARCHAR),
        @Result(column="STATE", property="state", jdbcType=JdbcType.VARCHAR),
        @Result(column="DESCRIPTION", property="description", jdbcType=JdbcType.VARCHAR)
    })
    List<Attachment> selectAll();

    @Update({
        "update attachment",
        "set OBJECT_TYPE = #{objectType,jdbcType=VARCHAR},",
          "OBJECT_ID = #{objectId,jdbcType=INTEGER},",
          "BUSINESS_SCENARIO = #{businessScenario,jdbcType=VARCHAR},",
          "INPUT_ID = #{inputId,jdbcType=VARCHAR},",
          "FILE_PATH = #{filePath,jdbcType=VARCHAR},",
          "FILE_NAME = #{fileName,jdbcType=VARCHAR},",
          "FILE_TYPE = #{fileType,jdbcType=VARCHAR},",
          "FILE_SIZE = #{fileSize,jdbcType=INTEGER},",
          "FS_GUID = #{fsGuid,jdbcType=VARCHAR},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "STAFF_ID = #{staffId,jdbcType=INTEGER},",
          "STAFF_NAME = #{staffName,jdbcType=VARCHAR},",
          "STATE = #{state,jdbcType=VARCHAR},",
          "DESCRIPTION = #{description,jdbcType=VARCHAR}",
        "where ATTACH_ID = #{attachId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Attachment record);
}