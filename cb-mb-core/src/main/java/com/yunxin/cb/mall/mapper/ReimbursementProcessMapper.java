package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.ReimbursementProcess;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface ReimbursementProcessMapper {

    @Delete({
            "delete from rb_reimbursement_process",
            "where PROCESS_ID = #{processId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer processId);


    @Insert({
            "insert into rb_reimbursement_process (PROCESS_ID, REIMBURSEMENT_ID, ",
            "USER_ID, REMARKS, ",
            "ORDER_STATE, CREATE_TIME)",
            "values (#{processId,jdbcType=INTEGER}, #{reimbursementId,jdbcType=INTEGER}, ",
            "#{userId,jdbcType=INTEGER}, #{remarks,jdbcType=VARCHAR}, ",
            "#{orderState,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(ReimbursementProcess record);


    @Select({
            "select",
            "PROCESS_ID, REIMBURSEMENT_ID, USER_ID, REMARKS, ORDER_STATE, CREATE_TIME",
            "from rb_reimbursement_process",
            "where PROCESS_ID = #{processId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="PROCESS_ID", property="processId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="REIMBURSEMENT_ID", property="reimbursementId", jdbcType=JdbcType.INTEGER),
            @Result(column="USER_ID", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="REMARKS", property="remarks", jdbcType=JdbcType.VARCHAR),
            @Result(column="ORDER_STATE", property="orderState", jdbcType=JdbcType.INTEGER),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    ReimbursementProcess selectByPrimaryKey(Integer processId);


    @Select({
            "select",
            "PROCESS_ID, REIMBURSEMENT_ID, USER_ID, REMARKS, ORDER_STATE, CREATE_TIME",
            "from rb_reimbursement_process"
    })
    @Results({
            @Result(column="PROCESS_ID", property="processId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="REIMBURSEMENT_ID", property="reimbursementId", jdbcType=JdbcType.INTEGER),
            @Result(column="USER_ID", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="REMARKS", property="remarks", jdbcType=JdbcType.VARCHAR),
            @Result(column="ORDER_STATE", property="orderState", jdbcType=JdbcType.INTEGER),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ReimbursementProcess> selectAll();

    @Update({
            "update rb_reimbursement_process",
            "set REIMBURSEMENT_ID = #{reimbursementId,jdbcType=INTEGER},",
            "USER_ID = #{userId,jdbcType=INTEGER},",
            "REMARKS = #{remarks,jdbcType=VARCHAR},",
            "ORDER_STATE = #{orderState,jdbcType=INTEGER},",
            "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP}",
            "where PROCESS_ID = #{processId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ReimbursementProcess record);

    @Select({
            "select PROCESS_ID, REIMBURSEMENT_ID, USER_ID, REMARKS, ORDER_STATE, CREATE_TIME from rb_reimbursement_process where REIMBURSEMENT_ID = #{reimbursementId,jdbcType=INTEGER} order by PROCESS_ID"
    })
    @Results({
            @Result(column="PROCESS_ID", property="processId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="REIMBURSEMENT_ID", property="reimbursementId", jdbcType=JdbcType.INTEGER),
            @Result(column="USER_ID", property="userId", jdbcType=JdbcType.INTEGER),
            @Result(column="REMARKS", property="remarks", jdbcType=JdbcType.VARCHAR),
            @Result(column="ORDER_STATE", property="orderState", jdbcType=JdbcType.INTEGER),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ReimbursementProcess> selectAllByReimbursementId(Integer  reimbursementId);
}
