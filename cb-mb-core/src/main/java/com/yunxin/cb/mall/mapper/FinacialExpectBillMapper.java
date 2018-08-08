package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.FinacialExpectBill;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface FinacialExpectBillMapper {
    @Delete({
        "delete from finacial_expect_bill",
        "where FINACIAL_EXPECT_ID = #{finacialExpectId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer finacialExpectId);

    @Insert({
        "insert into finacial_expect_bill (FINACIAL_EXPECT_ID, CUSTOMER_ID, ",
        "TYPE, TRANSACTION_TYPE, ",
        "TRANSACTION_DESC, AMOUNT, ",
        "CREATE_TIME)",
        "values (#{finacialExpectId,jdbcType=INTEGER}, #{customerId,jdbcType=INTEGER}, ",
        "#{type,jdbcType=INTEGER}, #{transactionType,jdbcType=INTEGER}, ",
        "#{transactionDesc,jdbcType=VARCHAR}, #{amount,jdbcType=DECIMAL}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(FinacialExpectBill record);

    @Select({
        "select",
        "FINACIAL_EXPECT_ID, CUSTOMER_ID, TYPE, TRANSACTION_TYPE, TRANSACTION_DESC, AMOUNT, ",
        "CREATE_TIME",
        "from finacial_expect_bill",
        "where FINACIAL_EXPECT_ID = #{finacialExpectId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="FINACIAL_EXPECT_ID", property="finacialExpectId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="TRANSACTION_TYPE", property="transactionType", jdbcType=JdbcType.INTEGER),
        @Result(column="TRANSACTION_DESC", property="transactionDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    FinacialExpectBill selectByPrimaryKey(Integer finacialExpectId);

    @Select({
        "select",
        "FINACIAL_EXPECT_ID, CUSTOMER_ID, TYPE, TRANSACTION_TYPE, TRANSACTION_DESC, AMOUNT, ",
        "CREATE_TIME",
        "from finacial_expect_bill where CUSTOMER_ID = #{customerId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="FINACIAL_EXPECT_ID", property="finacialExpectId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="TRANSACTION_TYPE", property="transactionType", jdbcType=JdbcType.INTEGER),
        @Result(column="TRANSACTION_DESC", property="transactionDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FinacialExpectBill> selectByCustomerId(int customerId);

    @Update({
        "update finacial_expect_bill",
        "set CUSTOMER_ID = #{customerId,jdbcType=INTEGER},",
          "TYPE = #{type,jdbcType=INTEGER},",
          "TRANSACTION_TYPE = #{transactionType,jdbcType=INTEGER},",
          "TRANSACTION_DESC = #{transactionDesc,jdbcType=VARCHAR},",
          "AMOUNT = #{amount,jdbcType=DECIMAL},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP}",
        "where FINACIAL_EXPECT_ID = #{finacialExpectId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FinacialExpectBill record);
}