package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.FinacialWalletLog;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface FinacialWalletLogMapper {
    @Delete({
        "delete from finacial_wallet_log",
        "where WALLET_LOG_ID = #{walletLogId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer walletLogId);

    @Insert({
        "insert into finacial_wallet_log (WALLET_LOG_ID, WALLET_ID, ",
        "CUSTOMER_ID, ASSETS, ",
        "BALANCE, EXPECTED_AMOUNT, ",
        "DEBT_TOTAL, DEBT_EXPECTED, ",
        "DEBT_CREDIT, CREDIT_AMOUNT, ",
        "FREEZING_AMOUNT, TYPE, ",
        "AMOUNT, VERSION)",
        "values (#{walletLogId,jdbcType=INTEGER}, #{walletId,jdbcType=INTEGER}, ",
        "#{customerId,jdbcType=INTEGER}, #{assets,jdbcType=DECIMAL}, ",
        "#{balance,jdbcType=DECIMAL}, #{expectedAmount,jdbcType=DECIMAL}, ",
        "#{debtTotal,jdbcType=DECIMAL}, #{debtExpected,jdbcType=DECIMAL}, ",
        "#{debtCredit,jdbcType=DECIMAL}, #{creditAmount,jdbcType=DECIMAL}, ",
        "#{freezingAmount,jdbcType=DECIMAL}, #{type,jdbcType=INTEGER}, ",
        "#{amount,jdbcType=DECIMAL}, #{version,jdbcType=INTEGER})"
    })
    int insert(FinacialWalletLog record);

    @Select({
        "select",
        "WALLET_LOG_ID, WALLET_ID, CUSTOMER_ID, ASSETS, BALANCE, EXPECTED_AMOUNT, DEBT_TOTAL, ",
        "DEBT_EXPECTED, DEBT_CREDIT, CREDIT_AMOUNT, FREEZING_AMOUNT, TYPE, AMOUNT, VERSION",
        "from finacial_wallet_log",
        "where WALLET_LOG_ID = #{walletLogId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="WALLET_LOG_ID", property="walletLogId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="WALLET_ID", property="walletId", jdbcType=JdbcType.INTEGER),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="ASSETS", property="assets", jdbcType=JdbcType.DECIMAL),
        @Result(column="BALANCE", property="balance", jdbcType=JdbcType.DECIMAL),
        @Result(column="EXPECTED_AMOUNT", property="expectedAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEBT_TOTAL", property="debtTotal", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEBT_EXPECTED", property="debtExpected", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEBT_CREDIT", property="debtCredit", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREDIT_AMOUNT", property="creditAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="FREEZING_AMOUNT", property="freezingAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="VERSION", property="version", jdbcType=JdbcType.INTEGER)
    })
    FinacialWalletLog selectByPrimaryKey(Integer walletLogId);

    @Select({
            "select",
            "WALLET_LOG_ID, WALLET_ID, CUSTOMER_ID, ASSETS, BALANCE, EXPECTED_AMOUNT, DEBT_TOTAL, ",
            "DEBT_EXPECTED, DEBT_CREDIT, CREDIT_AMOUNT, FREEZING_AMOUNT, TYPE, AMOUNT, VERSION",
            "from finacial_wallet_log",
            "where CUSTOMER_ID = #{customerId,jdbcType=INTEGER} and VERSION = #{version,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="WALLET_LOG_ID", property="walletLogId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="WALLET_ID", property="walletId", jdbcType=JdbcType.INTEGER),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
            @Result(column="ASSETS", property="assets", jdbcType=JdbcType.DECIMAL),
            @Result(column="BALANCE", property="balance", jdbcType=JdbcType.DECIMAL),
            @Result(column="EXPECTED_AMOUNT", property="expectedAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="DEBT_TOTAL", property="debtTotal", jdbcType=JdbcType.DECIMAL),
            @Result(column="DEBT_EXPECTED", property="debtExpected", jdbcType=JdbcType.DECIMAL),
            @Result(column="DEBT_CREDIT", property="debtCredit", jdbcType=JdbcType.DECIMAL),
            @Result(column="CREDIT_AMOUNT", property="creditAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="FREEZING_AMOUNT", property="freezingAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="TYPE", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
            @Result(column="VERSION", property="version", jdbcType=JdbcType.INTEGER)
    })
    FinacialWalletLog selectByCustomerIdAndVersion(@Param("customerId") Integer customerId,@Param("version") Integer version);

    @Select({
        "select",
        "WALLET_LOG_ID, WALLET_ID, CUSTOMER_ID, ASSETS, BALANCE, EXPECTED_AMOUNT, DEBT_TOTAL, ",
        "DEBT_EXPECTED, DEBT_CREDIT, CREDIT_AMOUNT, FREEZING_AMOUNT, TYPE, AMOUNT, VERSION",
        "from finacial_wallet_log"
    })
    @Results({
        @Result(column="WALLET_LOG_ID", property="walletLogId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="WALLET_ID", property="walletId", jdbcType=JdbcType.INTEGER),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="ASSETS", property="assets", jdbcType=JdbcType.DECIMAL),
        @Result(column="BALANCE", property="balance", jdbcType=JdbcType.DECIMAL),
        @Result(column="EXPECTED_AMOUNT", property="expectedAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEBT_TOTAL", property="debtTotal", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEBT_EXPECTED", property="debtExpected", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEBT_CREDIT", property="debtCredit", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREDIT_AMOUNT", property="creditAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="FREEZING_AMOUNT", property="freezingAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="VERSION", property="version", jdbcType=JdbcType.INTEGER)
    })
    List<FinacialWalletLog> selectAll();

    @Update({
        "update finacial_wallet_log",
        "set WALLET_ID = #{walletId,jdbcType=INTEGER},",
          "CUSTOMER_ID = #{customerId,jdbcType=INTEGER},",
          "ASSETS = #{assets,jdbcType=DECIMAL},",
          "BALANCE = #{balance,jdbcType=DECIMAL},",
          "EXPECTED_AMOUNT = #{expectedAmount,jdbcType=DECIMAL},",
          "DEBT_TOTAL = #{debtTotal,jdbcType=DECIMAL},",
          "DEBT_EXPECTED = #{debtExpected,jdbcType=DECIMAL},",
          "DEBT_CREDIT = #{debtCredit,jdbcType=DECIMAL},",
          "CREDIT_AMOUNT = #{creditAmount,jdbcType=DECIMAL},",
          "FREEZING_AMOUNT = #{freezingAmount,jdbcType=DECIMAL},",
          "TYPE = #{type,jdbcType=INTEGER},",
          "AMOUNT = #{amount,jdbcType=DECIMAL},",
          "VERSION = #{version,jdbcType=INTEGER}",
        "where WALLET_LOG_ID = #{walletLogId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FinacialWalletLog record);
}