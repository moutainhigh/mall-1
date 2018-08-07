package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.FinacialWallet;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

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
        "VERSION)",
        "values (#{walletId,jdbcType=INTEGER}, #{customerId,jdbcType=INTEGER}, ",
        "#{assets,jdbcType=DECIMAL}, #{balance,jdbcType=DECIMAL}, ",
        "#{expectedAmount,jdbcType=DECIMAL}, #{debtTotal,jdbcType=DECIMAL}, ",
        "#{debtExpected,jdbcType=DECIMAL}, #{debtCredit,jdbcType=DECIMAL}, ",
        "#{creditAmount,jdbcType=DECIMAL}, #{freezingAmount,jdbcType=DECIMAL}, ",
        "#{version,jdbcType=INTEGER})"
    })
    int insert(FinacialWallet record);

    @Select({
        "select",
        "WALLET_ID, CUSTOMER_ID, ASSETS, BALANCE, EXPECTED_AMOUNT, DEBT_TOTAL, DEBT_EXPECTED, ",
        "DEBT_CREDIT, CREDIT_AMOUNT, FREEZING_AMOUNT, VERSION",
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
        @Result(column="VERSION", property="version", jdbcType=JdbcType.INTEGER)
    })
    FinacialWallet selectByPrimaryKey(Integer walletId);

    @Select({
            "select",
            "WALLET_ID, CUSTOMER_ID, ASSETS, BALANCE, EXPECTED_AMOUNT, DEBT_TOTAL, DEBT_EXPECTED, ",
            "DEBT_CREDIT, CREDIT_AMOUNT, FREEZING_AMOUNT, VERSION",
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
            @Result(column="VERSION", property="version", jdbcType=JdbcType.INTEGER)
    })
    FinacialWallet selectByCustomerId(Integer customerId);

    @Select({
        "select",
        "WALLET_ID, CUSTOMER_ID, ASSETS, BALANCE, EXPECTED_AMOUNT, DEBT_TOTAL, DEBT_EXPECTED, ",
        "DEBT_CREDIT, CREDIT_AMOUNT, FREEZING_AMOUNT, VERSION",
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
        @Result(column="VERSION", property="version", jdbcType=JdbcType.INTEGER)
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
          "VERSION = #{version,jdbcType=INTEGER}",
        "where WALLET_ID = #{walletId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FinacialWallet record);
}