package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.FinancialWallet;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
@Mapper
public interface FinancialWalletMapper {
    @Delete({
        "delete from financial_wallet",
        "where WALLET_ID = #{walletId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer walletId);

    @Insert({
        "insert into financial_wallet (CUSTOMER_ID, ",
        "FREEZING_AMOUNT, CREDIT_AMOUNT, ",
        "DEBT_CAR, DEBT_CREDIT, VERSION)",
        "values (#{customerId,jdbcType=INTEGER}, ",
        "#{freezingAmount,jdbcType=DECIMAL}, #{creditAmount,jdbcType=DECIMAL}, ",
        "#{debtCar,jdbcType=DECIMAL}, #{debtCredit,jdbcType=DECIMAL}, ",
        "#{version,jdbcType=INTEGER})"
    })
    int insert(FinancialWallet wallet);

    @Select({
        "select",
        "WALLET_ID, CUSTOMER_ID, FREEZING_AMOUNT, CREDIT_AMOUNT, DEBT_CAR, DEBT_CREDIT, VERSION",
        "from financial_wallet",
        "where WALLET_ID = #{walletId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="WALLET_ID", property="walletId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="FREEZING_AMOUNT", property="freezingAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREDIT_AMOUNT", property="creditAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEBT_CAR", property="debtCar", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEBT_CREDIT", property="debtCredit", jdbcType=JdbcType.DECIMAL),
        @Result(column="VERSION", property="version", jdbcType=JdbcType.DECIMAL)
    })
    FinancialWallet selectByPrimaryKey(Integer walletId);

    @Select({
            "select",
            "WALLET_ID, CUSTOMER_ID, FREEZING_AMOUNT, CREDIT_AMOUNT, DEBT_CAR, DEBT_CREDIT, VERSION",
            "from financial_wallet",
            "where CUSTOMER_ID = #{customerId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="WALLET_ID", property="walletId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
            @Result(column="FREEZING_AMOUNT", property="freezingAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="CREDIT_AMOUNT", property="creditAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="DEBT_CAR", property="debtCar", jdbcType=JdbcType.DECIMAL),
            @Result(column="DEBT_CREDIT", property="debtCredit", jdbcType=JdbcType.DECIMAL),
            @Result(column="VERSION", property="version", jdbcType=JdbcType.DECIMAL)
    })
    FinancialWallet selectByCustomerId(Integer customerId);

    @Select({
            "select",
            "WALLET_ID, CUSTOMER_ID, FREEZING_AMOUNT, CREDIT_AMOUNT, DEBT_CAR, DEBT_CREDIT, VERSION",
            "from financial_wallet",
            "where CUSTOMER_ID = #{customerId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="WALLET_ID", property="walletId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
            @Result(column="FREEZING_AMOUNT", property="freezingAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="CREDIT_AMOUNT", property="creditAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="DEBT_CAR", property="debtCar", jdbcType=JdbcType.DECIMAL),
            @Result(column="DEBT_CREDIT", property="debtCredit", jdbcType=JdbcType.DECIMAL),
            @Result(column="VERSION", property="version", jdbcType=JdbcType.DECIMAL)
    })
    List<FinancialWallet> selectAll();

    @Update({
            "update financial_wallet",
            "set CUSTOMER_ID = #{customerId,jdbcType=INTEGER},",
            "FREEZING_AMOUNT = #{freezingAmount,jdbcType=DECIMAL},",
            "CREDIT_AMOUNT = #{creditAmount,jdbcType=DECIMAL},",
            "DEBT_CAR = #{debtCar,jdbcType=DECIMAL},",
            "DEBT_CREDIT = #{debtCredit,jdbcType=DECIMAL},",
            "VERSION = VERSION + 1",
            "where WALLET_ID = #{walletId,jdbcType=INTEGER}",
            "and VERSION = #{version,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyOnVersion(FinancialWallet record);
}