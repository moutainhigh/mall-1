package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.FinacialCreditLineBill;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface FinancialCreditLineBillMapper {
    @Delete({
        "delete from finacial_credit_line_bill",
        "where FINACIAL_CREDIT_LINE_ID = #{finacialCreditLineId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer finacialCreditLineId);

    @Insert({
        "insert into finacial_credit_line_bill (FINACIAL_CREDIT_LINE_ID, CUSTOMER_ID, ",
        "TYPE, TRANSACTION_TYPE, ",
        "TRANSACTION_DESC, AMOUNT, ",
        "CREATE_TIME)",
        "values (#{finacialCreditLineId,jdbcType=INTEGER}, #{customerId,jdbcType=INTEGER}, ",
        "#{type,jdbcType=VARCHAR}, #{transactionType,jdbcType=VARCHAR}, ",
        "#{transactionDesc,jdbcType=VARCHAR}, #{amount,jdbcType=DECIMAL}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(FinacialCreditLineBill record);

    @Select({
        "select",
        "FINACIAL_CREDIT_LINE_ID, CUSTOMER_ID, TYPE, TRANSACTION_TYPE, TRANSACTION_DESC, ",
        "AMOUNT, CREATE_TIME",
        "from finacial_credit_line_bill",
        "where FINACIAL_CREDIT_LINE_ID = #{finacialCreditLineId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="FINACIAL_CREDIT_LINE_ID", property="finacialCreditLineId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="TRANSACTION_TYPE", property="transactionType", jdbcType=JdbcType.VARCHAR),
        @Result(column="TRANSACTION_DESC", property="transactionDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    FinacialCreditLineBill selectByPrimaryKey(Integer finacialCreditLineId);

    @Select({
        "select",
        "FINACIAL_CREDIT_LINE_ID, CUSTOMER_ID, TYPE, TRANSACTION_TYPE, TRANSACTION_DESC, ",
        "AMOUNT, CREATE_TIME",
        "from finacial_credit_line_bill"
    })
    @Results({
        @Result(column="FINACIAL_CREDIT_LINE_ID", property="finacialCreditLineId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="TRANSACTION_TYPE", property="transactionType", jdbcType=JdbcType.VARCHAR),
        @Result(column="TRANSACTION_DESC", property="transactionDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FinacialCreditLineBill> selectAll();

    @Update({
        "update finacial_credit_line_bill",
        "set CUSTOMER_ID = #{customerId,jdbcType=INTEGER},",
          "TYPE = #{type,jdbcType=VARCHAR},",
          "TRANSACTION_TYPE = #{transactionType,jdbcType=VARCHAR},",
          "TRANSACTION_DESC = #{transactionDesc,jdbcType=VARCHAR},",
          "AMOUNT = #{amount,jdbcType=DECIMAL},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP}",
        "where FINACIAL_CREDIT_LINE_ID = #{finacialCreditLineId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FinacialCreditLineBill record);
}