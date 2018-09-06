package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.FinancialWalletLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface FinancialWalletLogMapper {
    @Delete({
        "delete from financial_wallet_log",
        "where WALLET_LOG_ID = #{walletLogId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer walletLogId);

    @Insert({
        "insert into financial_wallet_log (WALLET_ID, ",
        "CUSTOMER_ID, TYPE, ",
        "FREEZING_AMOUNT, CREDIT_AMOUNT, ",
        "DEBT_CAR, DEBT_CREDIT, ",
        "VERSION, AMOUNT, REMARK)",
        "values (#{walletId,jdbcType=INTEGER}, ",
        "#{customerId,jdbcType=INTEGER}, #{type}, ",
        "#{freezingAmount,jdbcType=DECIMAL}, #{creditAmount,jdbcType=DECIMAL}, ",
        "#{debtCar,jdbcType=DECIMAL}, #{debtCredit,jdbcType=DECIMAL}, ",
        "#{version,jdbcType=INTEGER}, #{amount,jdbcType=DECIMAL}, ",
        "#{remark})"
    })
    int insert(FinancialWalletLog record);

}