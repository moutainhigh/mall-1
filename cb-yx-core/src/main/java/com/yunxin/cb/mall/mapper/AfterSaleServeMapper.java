package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.AfterSaleServe;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

public interface AfterSaleServeMapper {
    @Delete({
        "delete from yx_after_sale_serve",
        "where AFTER_SALE_SERVE_ID = #{afterSaleServeId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer afterSaleServeId);

    @Insert({
        "insert into yx_after_sale_serve (AFTER_SALE_SERVE_ID, AFTER_SALE_SERVE_CODE, ",
        "AFTER_SALE_SERVE_TYPE, ORDER_ID, ",
        "SELLER_ID, USER_ID, ",
        "REASON, PROBLEM_DESCRIPTION, ",
        "CONTACT_PERSON, CONTACT_PHONE, ",
        "AUDIT_STATE, AFTER_SALE_SERVE_STATE, ",
        "COURIER_NUMBER, PHONE, ",
        "NAME, ADDRESS, LOGISTIC_CODE, ",
        "ACTUAL_REFUND, BARTER_START_TIME, ",
        "BARTER_END_TIME, CREATE_TIME, ",
        "DISPOSE_END_TIME)",
        "values (#{afterSaleServeId,jdbcType=INTEGER}, #{afterSaleServeCode,jdbcType=INTEGER}, ",
        "#{afterSaleServeType,jdbcType=BIT}, #{orderId,jdbcType=INTEGER}, ",
        "#{sellerId,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{reason,jdbcType=VARCHAR}, #{problemDescription,jdbcType=VARCHAR}, ",
        "#{contactPerson,jdbcType=VARCHAR}, #{contactPhone,jdbcType=VARCHAR}, ",
        "#{auditState,jdbcType=BIT}, #{afterSaleServeState,jdbcType=BIT}, ",
        "#{courierNumber,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, #{logisticCode,jdbcType=VARCHAR}, ",
        "#{actualRefund,jdbcType=REAL}, #{barterStartTime,jdbcType=TIMESTAMP}, ",
        "#{barterEndTime,jdbcType=TIMESTAMP}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{disposeEndTime,jdbcType=TIMESTAMP})"
    })
    int insert(AfterSaleServe record);

    @Select({
        "select",
        "AFTER_SALE_SERVE_ID, AFTER_SALE_SERVE_CODE, AFTER_SALE_SERVE_TYPE, ORDER_ID, ",
        "SELLER_ID, USER_ID, REASON, PROBLEM_DESCRIPTION, CONTACT_PERSON, CONTACT_PHONE, ",
        "AUDIT_STATE, AFTER_SALE_SERVE_STATE, COURIER_NUMBER, PHONE, NAME, ADDRESS, LOGISTIC_CODE, ",
        "ACTUAL_REFUND, BARTER_START_TIME, BARTER_END_TIME, CREATE_TIME, DISPOSE_END_TIME",
        "from yx_after_sale_serve",
        "where AFTER_SALE_SERVE_ID = #{afterSaleServeId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="AFTER_SALE_SERVE_ID", property="afterSaleServeId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="AFTER_SALE_SERVE_CODE", property="afterSaleServeCode", jdbcType=JdbcType.INTEGER),
        @Result(column="AFTER_SALE_SERVE_TYPE", property="afterSaleServeType", jdbcType=JdbcType.BIT),
        @Result(column="ORDER_ID", property="orderId", jdbcType=JdbcType.INTEGER),
        @Result(column="SELLER_ID", property="sellerId", jdbcType=JdbcType.INTEGER),
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="REASON", property="reason", jdbcType=JdbcType.VARCHAR),
        @Result(column="PROBLEM_DESCRIPTION", property="problemDescription", jdbcType=JdbcType.VARCHAR),
        @Result(column="CONTACT_PERSON", property="contactPerson", jdbcType=JdbcType.VARCHAR),
        @Result(column="CONTACT_PHONE", property="contactPhone", jdbcType=JdbcType.VARCHAR),
        @Result(column="AUDIT_STATE", property="auditState", jdbcType=JdbcType.BIT),
        @Result(column="AFTER_SALE_SERVE_STATE", property="afterSaleServeState", jdbcType=JdbcType.BIT),
        @Result(column="COURIER_NUMBER", property="courierNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="PHONE", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="ADDRESS", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="LOGISTIC_CODE", property="logisticCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="ACTUAL_REFUND", property="actualRefund", jdbcType=JdbcType.REAL),
        @Result(column="BARTER_START_TIME", property="barterStartTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="BARTER_END_TIME", property="barterEndTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="DISPOSE_END_TIME", property="disposeEndTime", jdbcType=JdbcType.TIMESTAMP)
    })
    AfterSaleServe selectByPrimaryKey(Integer afterSaleServeId);

    @Select({
        "select",
        "AFTER_SALE_SERVE_ID, AFTER_SALE_SERVE_CODE, AFTER_SALE_SERVE_TYPE, ORDER_ID, ",
        "SELLER_ID, USER_ID, REASON, PROBLEM_DESCRIPTION, CONTACT_PERSON, CONTACT_PHONE, ",
        "AUDIT_STATE, AFTER_SALE_SERVE_STATE, COURIER_NUMBER, PHONE, NAME, ADDRESS, LOGISTIC_CODE, ",
        "ACTUAL_REFUND, BARTER_START_TIME, BARTER_END_TIME, CREATE_TIME, DISPOSE_END_TIME",
        "from yx_after_sale_serve"
    })
    @Results({
        @Result(column="AFTER_SALE_SERVE_ID", property="afterSaleServeId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="AFTER_SALE_SERVE_CODE", property="afterSaleServeCode", jdbcType=JdbcType.INTEGER),
        @Result(column="AFTER_SALE_SERVE_TYPE", property="afterSaleServeType", jdbcType=JdbcType.BIT),
        @Result(column="ORDER_ID", property="orderId", jdbcType=JdbcType.INTEGER),
        @Result(column="SELLER_ID", property="sellerId", jdbcType=JdbcType.INTEGER),
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="REASON", property="reason", jdbcType=JdbcType.VARCHAR),
        @Result(column="PROBLEM_DESCRIPTION", property="problemDescription", jdbcType=JdbcType.VARCHAR),
        @Result(column="CONTACT_PERSON", property="contactPerson", jdbcType=JdbcType.VARCHAR),
        @Result(column="CONTACT_PHONE", property="contactPhone", jdbcType=JdbcType.VARCHAR),
        @Result(column="AUDIT_STATE", property="auditState", jdbcType=JdbcType.BIT),
        @Result(column="AFTER_SALE_SERVE_STATE", property="afterSaleServeState", jdbcType=JdbcType.BIT),
        @Result(column="COURIER_NUMBER", property="courierNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="PHONE", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="NAME", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="ADDRESS", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="LOGISTIC_CODE", property="logisticCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="ACTUAL_REFUND", property="actualRefund", jdbcType=JdbcType.REAL),
        @Result(column="BARTER_START_TIME", property="barterStartTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="BARTER_END_TIME", property="barterEndTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="DISPOSE_END_TIME", property="disposeEndTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<AfterSaleServe> selectAll();

    @Update({
        "update yx_after_sale_serve",
        "set AFTER_SALE_SERVE_CODE = #{afterSaleServeCode,jdbcType=INTEGER},",
          "AFTER_SALE_SERVE_TYPE = #{afterSaleServeType,jdbcType=BIT},",
          "ORDER_ID = #{orderId,jdbcType=INTEGER},",
          "SELLER_ID = #{sellerId,jdbcType=INTEGER},",
          "USER_ID = #{userId,jdbcType=INTEGER},",
          "REASON = #{reason,jdbcType=VARCHAR},",
          "PROBLEM_DESCRIPTION = #{problemDescription,jdbcType=VARCHAR},",
          "CONTACT_PERSON = #{contactPerson,jdbcType=VARCHAR},",
          "CONTACT_PHONE = #{contactPhone,jdbcType=VARCHAR},",
          "AUDIT_STATE = #{auditState,jdbcType=BIT},",
          "AFTER_SALE_SERVE_STATE = #{afterSaleServeState,jdbcType=BIT},",
          "COURIER_NUMBER = #{courierNumber,jdbcType=VARCHAR},",
          "PHONE = #{phone,jdbcType=VARCHAR},",
          "NAME = #{name,jdbcType=VARCHAR},",
          "ADDRESS = #{address,jdbcType=VARCHAR},",
          "LOGISTIC_CODE = #{logisticCode,jdbcType=VARCHAR},",
          "ACTUAL_REFUND = #{actualRefund,jdbcType=REAL},",
          "BARTER_START_TIME = #{barterStartTime,jdbcType=TIMESTAMP},",
          "BARTER_END_TIME = #{barterEndTime,jdbcType=TIMESTAMP},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "DISPOSE_END_TIME = #{disposeEndTime,jdbcType=TIMESTAMP}",
        "where AFTER_SALE_SERVE_ID = #{afterSaleServeId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AfterSaleServe record);
}