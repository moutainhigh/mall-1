package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.FinacialInsuCashbackLog;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface FinacialInsuCashbackLogMapper {
    @Delete({
        "delete from finacial_insu_cashback_log",
        "where LOG_ID = #{logId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer logId);

    @Insert({
        "insert into finacial_insu_cashback_log (LOG_ID, CUSTOMER_ID, ",
        "CUSTOMER_NAME, MOBILE, ",
        "AMOUNT, STATE, ORDER_NO, ",
        "CREATE_TIME)",
        "values (#{logId,jdbcType=INTEGER}, #{customerId,jdbcType=INTEGER}, ",
        "#{customerName,jdbcType=VARCHAR}, #{mobile,jdbcType=VARCHAR}, ",
        "#{amount,jdbcType=DECIMAL}, #{state,jdbcType=INTEGER}, #{orderNo,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(FinacialInsuCashbackLog record);

    @Select({
        "select",
        "LOG_ID, CUSTOMER_ID, CUSTOMER_NAME, MOBILE, AMOUNT, STATE, ORDER_NO, CREATE_TIME",
        "from finacial_insu_cashback_log",
        "where LOG_ID = #{logId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="LOG_ID", property="logId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="CUSTOMER_NAME", property="customerName", jdbcType=JdbcType.VARCHAR),
        @Result(column="MOBILE", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="STATE", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="ORDER_NO", property="orderNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    FinacialInsuCashbackLog selectByPrimaryKey(Integer logId);

    @Select({
        "select",
        "LOG_ID, CUSTOMER_ID, CUSTOMER_NAME, MOBILE, AMOUNT, STATE, ORDER_NO, CREATE_TIME",
        "from finacial_insu_cashback_log"
    })
    @Results({
        @Result(column="LOG_ID", property="logId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="CUSTOMER_NAME", property="customerName", jdbcType=JdbcType.VARCHAR),
        @Result(column="MOBILE", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="STATE", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="ORDER_NO", property="orderNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FinacialInsuCashbackLog> selectAll();

    @Update({
        "update finacial_insu_cashback_log",
        "set CUSTOMER_ID = #{customerId,jdbcType=INTEGER},",
          "CUSTOMER_NAME = #{customerName,jdbcType=VARCHAR},",
          "MOBILE = #{mobile,jdbcType=VARCHAR},",
          "AMOUNT = #{amount,jdbcType=DECIMAL},",
          "STATE = #{state,jdbcType=INTEGER},",
          "ORDER_NO = #{orderNo,jdbcType=VARCHAR},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP}",
        "where LOG_ID = #{logId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FinacialInsuCashbackLog record);
}