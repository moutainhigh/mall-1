package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.FinacialWallet;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
@Mapper
public interface FinacialWalletMapper {
    @Delete({
        "delete from finacial_wallet",
        "where WALLET_ID = #{walletId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer walletId);

    @Insert({
        "insert into finacial_wallet (WALLET_ID, CUSTOMER_ID, ",
        "ASSETS, BALANCE, ",
        "EXPECTED_AMOUNT, DEBT_TOTAL, ",
        "DEBT_EXPECTED, DEBT_CREDIT, ",
        "CREDIT_AMOUNT, FREEZING_AMOUNT, ",
        "VERSION,INSURANCE_AMOUNT,TOTAL_AMOUNT)",
        "values (#{walletId,jdbcType=INTEGER}, #{customerId,jdbcType=INTEGER}, ",
        "#{assets,jdbcType=DECIMAL}, #{balance,jdbcType=DECIMAL}, ",
        "#{expectedAmount,jdbcType=DECIMAL}, #{debtTotal,jdbcType=DECIMAL}, ",
        "#{debtExpected,jdbcType=DECIMAL}, #{debtCredit,jdbcType=DECIMAL}, ",
        "#{creditAmount,jdbcType=DECIMAL}, #{freezingAmount,jdbcType=DECIMAL}, ",
        "#{version,jdbcType=INTEGER}, #{insuranceAmount,jdbcType=DECIMAL}, #{totalAmount,jdbcType=DECIMAL})"
    })
    int insert(FinacialWallet record);

    @Select({
        "select",
        "WALLET_ID, CUSTOMER_ID, ASSETS, BALANCE, EXPECTED_AMOUNT, DEBT_TOTAL, DEBT_EXPECTED, ",
        "DEBT_CREDIT, CREDIT_AMOUNT, FREEZING_AMOUNT, VERSION,INSURANCE_AMOUNT,TOTAL_AMOUNT",
        "from finacial_wallet",
        "where WALLET_ID = #{walletId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="WALLET_ID", property="walletId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="ASSETS", property="assets", jdbcType=JdbcType.DECIMAL),
        @Result(column="BALANCE", property="balance", jdbcType=JdbcType.DECIMAL),
        @Result(column="EXPECTED_AMOUNT", property="expectedAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEBT_TOTAL", property="debtTotal", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEBT_EXPECTED", property="debtExpected", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEBT_CREDIT", property="debtCredit", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREDIT_AMOUNT", property="creditAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="FREEZING_AMOUNT", property="freezingAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="VERSION", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="INSURANCE_AMOUNT", property="insuranceAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="TOTAL_AMOUNT", property="totalAmount", jdbcType=JdbcType.DECIMAL)
    })
    FinacialWallet selectByPrimaryKey(Integer walletId);

    @Select({
            "select",
            "WALLET_ID, CUSTOMER_ID, ASSETS, BALANCE, EXPECTED_AMOUNT, DEBT_TOTAL, DEBT_EXPECTED, ",
            "DEBT_CREDIT, CREDIT_AMOUNT, FREEZING_AMOUNT, VERSION,INSURANCE_AMOUNT,TOTAL_AMOUNT",
            "from finacial_wallet",
            "where WALLET_ID = #{customerId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="WALLET_ID", property="walletId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
            @Result(column="ASSETS", property="assets", jdbcType=JdbcType.DECIMAL),
            @Result(column="BALANCE", property="balance", jdbcType=JdbcType.DECIMAL),
            @Result(column="EXPECTED_AMOUNT", property="expectedAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="DEBT_TOTAL", property="debtTotal", jdbcType=JdbcType.DECIMAL),
            @Result(column="DEBT_EXPECTED", property="debtExpected", jdbcType=JdbcType.DECIMAL),
            @Result(column="DEBT_CREDIT", property="debtCredit", jdbcType=JdbcType.DECIMAL),
            @Result(column="CREDIT_AMOUNT", property="creditAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="FREEZING_AMOUNT", property="freezingAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="VERSION", property="version", jdbcType=JdbcType.INTEGER),
            @Result(column="INSURANCE_AMOUNT", property="insuranceAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="TOTAL_AMOUNT", property="totalAmount", jdbcType=JdbcType.DECIMAL)
    })
    FinacialWallet selectByCustomerId(Integer customerId);

    @Select({
        "select",
        "WALLET_ID, CUSTOMER_ID, ASSETS, BALANCE, EXPECTED_AMOUNT, DEBT_TOTAL, DEBT_EXPECTED, ",
        "DEBT_CREDIT, CREDIT_AMOUNT, FREEZING_AMOUNT, VERSION,INSURANCE_AMOUNT,TOTAL_AMOUNT",
        "from finacial_wallet"
    })
    @Results({
        @Result(column="WALLET_ID", property="walletId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="ASSETS", property="assets", jdbcType=JdbcType.DECIMAL),
        @Result(column="BALANCE", property="balance", jdbcType=JdbcType.DECIMAL),
        @Result(column="EXPECTED_AMOUNT", property="expectedAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEBT_TOTAL", property="debtTotal", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEBT_EXPECTED", property="debtExpected", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEBT_CREDIT", property="debtCredit", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREDIT_AMOUNT", property="creditAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="FREEZING_AMOUNT", property="freezingAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="VERSION", property="version", jdbcType=JdbcType.INTEGER),
        @Result(column="INSURANCE_AMOUNT", property="insuranceAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="TOTAL_AMOUNT", property="totalAmount", jdbcType=JdbcType.DECIMAL)
    })
    List<FinacialWallet> selectAll();

    @Update({
        "update finacial_wallet",
        "set CUSTOMER_ID = #{customerId,jdbcType=INTEGER},",
          "ASSETS = #{assets,jdbcType=DECIMAL},",
          "BALANCE = #{balance,jdbcType=DECIMAL},",
          "EXPECTED_AMOUNT = #{expectedAmount,jdbcType=DECIMAL},",
          "DEBT_TOTAL = #{debtTotal,jdbcType=DECIMAL},",
          "DEBT_EXPECTED = #{debtExpected,jdbcType=DECIMAL},",
          "DEBT_CREDIT = #{debtCredit,jdbcType=DECIMAL},",
          "CREDIT_AMOUNT = #{creditAmount,jdbcType=DECIMAL},",
          "FREEZING_AMOUNT = #{freezingAmount,jdbcType=DECIMAL},",
          "VERSION = #{version,jdbcType=INTEGER},",
          "INSURANCE_AMOUNT = #{freezingAmount,jdbcType=INTEGER},",
          "TOTAL_AMOUNT = #{totalAmount,jdbcType=INTEGER}",
        "where WALLET_ID = #{walletId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FinacialWallet record);
}