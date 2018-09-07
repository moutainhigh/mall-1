package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.FinacialBill;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface FinacialBillMapper {
    @Delete({
        "delete from finacial_bill",
        "where BILL_ID = #{billId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer billId);

    @Insert({
        "insert into finacial_bill (BILL_ID, CUSTOMER_ID, ",
        "AMOUNT, TYPE, STATE, ",
        "TRANSACTION_TYPE, CREATE_TIME, ",
        "TRANSACTION_DESC, TRANSACTION_NO, ",
        "WITHDRAW_BANK, CHARGE_FEE, ",
        "REAL_AMOUNT)",
        "values (#{billId,jdbcType=INTEGER}, #{customerId,jdbcType=INTEGER}, ",
        "#{amount,jdbcType=DECIMAL}, #{type,jdbcType=INTEGER}, #{state,jdbcType=INTEGER}, ",
        "#{transactionType,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{transactionDesc,jdbcType=VARCHAR}, #{transactionNo,jdbcType=VARCHAR}, ",
        "#{withdrawBank,jdbcType=VARCHAR}, #{chargeFee,jdbcType=DECIMAL}, ",
        "#{realAmount,jdbcType=DECIMAL})"
    })
    int insert(FinacialBill record);

    @Select({
        "select",
        "BILL_ID, CUSTOMER_ID, AMOUNT, TYPE, STATE, TRANSACTION_TYPE, CREATE_TIME, TRANSACTION_DESC, ",
        "TRANSACTION_NO, WITHDRAW_BANK, CHARGE_FEE, REAL_AMOUNT",
        "from finacial_bill",
        "where BILL_ID = #{billId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="BILL_ID", property="billId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="STATE", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="TRANSACTION_TYPE", property="transactionType", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="TRANSACTION_DESC", property="transactionDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="TRANSACTION_NO", property="transactionNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="WITHDRAW_BANK", property="withdrawBank", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHARGE_FEE", property="chargeFee", jdbcType=JdbcType.DECIMAL),
        @Result(column="REAL_AMOUNT", property="realAmount", jdbcType=JdbcType.DECIMAL)
    })
    FinacialBill selectByPrimaryKey(Integer billId);

    @Select({
        "select",
        "BILL_ID, CUSTOMER_ID, AMOUNT, TYPE, STATE, TRANSACTION_TYPE, CREATE_TIME, TRANSACTION_DESC, ",
        "TRANSACTION_NO, WITHDRAW_BANK, CHARGE_FEE, REAL_AMOUNT",
        "from finacial_bill"
    })
    @Results({
        @Result(column="BILL_ID", property="billId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="STATE", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="TRANSACTION_TYPE", property="transactionType", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="TRANSACTION_DESC", property="transactionDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="TRANSACTION_NO", property="transactionNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="WITHDRAW_BANK", property="withdrawBank", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHARGE_FEE", property="chargeFee", jdbcType=JdbcType.DECIMAL),
        @Result(column="REAL_AMOUNT", property="realAmount", jdbcType=JdbcType.DECIMAL)
    })
    List<FinacialBill> selectAll();

    @Update({
        "update finacial_bill",
        "set CUSTOMER_ID = #{customerId,jdbcType=INTEGER},",
          "AMOUNT = #{amount,jdbcType=DECIMAL},",
          "TYPE = #{type,jdbcType=INTEGER},",
          "STATE = #{state,jdbcType=INTEGER},",
          "TRANSACTION_TYPE = #{transactionType,jdbcType=INTEGER},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "TRANSACTION_DESC = #{transactionDesc,jdbcType=VARCHAR},",
          "TRANSACTION_NO = #{transactionNo,jdbcType=VARCHAR},",
          "WITHDRAW_BANK = #{withdrawBank,jdbcType=VARCHAR},",
          "CHARGE_FEE = #{chargeFee,jdbcType=DECIMAL},",
          "REAL_AMOUNT = #{realAmount,jdbcType=DECIMAL}",
        "where BILL_ID = #{billId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FinacialBill record);
}