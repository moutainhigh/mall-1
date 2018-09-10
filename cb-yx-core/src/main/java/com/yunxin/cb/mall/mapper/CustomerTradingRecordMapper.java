package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.CustomerTradingRecord;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import java.util.List;
@Mapper
public interface CustomerTradingRecordMapper {
    @Delete({
            "delete from customer_trading_record",
            "where TRADE_RECORD_ID = #{tradeRecordId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer tradeRecordId);

    @Insert({
            "insert into customer_trading_record (TRADE_RECORD_ID, CUSTOMER_ID, ",
            "BUSINESS_TYPE, OPERATION_TYPE, ",
            "AMOUNT, CREATE_TIME, ",
            "REMARK)",
            "values (#{tradeRecordId,jdbcType=INTEGER}, #{customerId,jdbcType=INTEGER}, ",
            "#{businessType,jdbcType=INTEGER}, #{operationType,jdbcType=INTEGER}, ",
            "#{amount,jdbcType=DOUBLE}, #{createTime,jdbcType=TIMESTAMP}, ",
            "#{remark,jdbcType=VARCHAR})"
    })
    int insert(CustomerTradingRecord record);

    @Select({
            "select",
            "TRADE_RECORD_ID, CUSTOMER_ID, BUSINESS_TYPE, OPERATION_TYPE, AMOUNT, CREATE_TIME, ",
            "REMARK",
            "from customer_trading_record",
            "where TRADE_RECORD_ID = #{tradeRecordId,jdbcType=INTEGER} and CUSTOMER_ID = #{customerId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="TRADE_RECORD_ID", property="tradeRecordId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
            @Result(column="BUSINESS_TYPE", property="businessType", jdbcType=JdbcType.INTEGER),
            @Result(column="OPERATION_TYPE", property="operationType", jdbcType=JdbcType.INTEGER),
            @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DOUBLE),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    CustomerTradingRecord selectByPrimaryKeyAndCustomerId(@Param("tradeRecordId")Integer tradeRecordId,@Param("customerId")Integer customerId);

    @Select({
            "select",
            "TRADE_RECORD_ID, CUSTOMER_ID, BUSINESS_TYPE, OPERATION_TYPE, AMOUNT, CREATE_TIME, ",
            "REMARK",
            "from customer_trading_record where CUSTOMER_ID = #{customerId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="TRADE_RECORD_ID", property="tradeRecordId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
            @Result(column="BUSINESS_TYPE", property="businessType", jdbcType=JdbcType.INTEGER),
            @Result(column="OPERATION_TYPE", property="operationType", jdbcType=JdbcType.INTEGER),
            @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DOUBLE),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<CustomerTradingRecord> selectByCustomerId(@Param("customerId")Integer customerId);

    @Update({
            "update customer_trading_record",
            "set CUSTOMER_ID = #{customerId,jdbcType=INTEGER},",
            "BUSINESS_TYPE = #{businessType,jdbcType=INTEGER},",
            "OPERATION_TYPE = #{operationType,jdbcType=INTEGER},",
            "AMOUNT = #{amount,jdbcType=DOUBLE},",
            "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
            "REMARK = #{remark,jdbcType=VARCHAR}",
            "where TRADE_RECORD_ID = #{tradeRecordId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CustomerTradingRecord record);
}
