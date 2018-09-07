package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.FinancialWithdraw;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface FinancialWithdrawMapper {
    @Delete({
        "delete from financial_withdraw",
        "where WITHDRAW_ID = #{withdrawId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer withdrawId);

    @Insert({
        "insert into financial_withdraw (CUSTOMER_ID, ",
        "BANK_ID, AMOUNT, ",
        "REAL_AMOUNT, CHARGE_FEE, ",
        "STATE, WITHDRAW_TYPE, AUDIT_DATE, ",
        "AUDIT_OPERATOR, AUDIT_MESSAGE, ",
        "GRANT_DATE, GRANT_OPERATOR, ",
        "APPLY_DATE, UPDATE_DATE)",
        "values (#{customerId,jdbcType=INTEGER}, ",
        "#{bankId,jdbcType=INTEGER}, #{amount,jdbcType=DECIMAL}, ",
        "#{realAmount,jdbcType=DECIMAL}, #{chargeFee,jdbcType=DECIMAL}, ",
        "#{state,jdbcType=INTEGER}, #{withdrawType,jdbcType=INTEGER}, #{auditDate,jdbcType=TIMESTAMP}, ",
        "#{auditOperator,jdbcType=VARCHAR}, #{auditMessage,jdbcType=VARCHAR}, ",
        "#{grantDate,jdbcType=TIMESTAMP}, #{grantOperator,jdbcType=VARCHAR}, ",
        "#{applyDate,jdbcType=TIMESTAMP}, #{updateDate,jdbcType=TIMESTAMP})"
    })
    @Options(useGeneratedKeys=true, keyProperty="withdrawId", keyColumn="WITHDRAW_ID")
    int insert(FinancialWithdraw record);

    @Select({
        "select",
        "WITHDRAW_ID, CUSTOMER_ID, BANK_ID, AMOUNT, REAL_AMOUNT, CHARGE_FEE, STATE, WITHDRAW_TYPE, AUDIT_DATE, ",
        "AUDIT_OPERATOR, AUDIT_MESSAGE, GRANT_DATE, GRANT_OPERATOR, APPLY_DATE, UPDATE_DATE",
        "from financial_withdraw",
        "where WITHDRAW_ID = #{withdrawId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="WITHDRAW_ID", property="withdrawId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="BANK_ID", property="bankId", jdbcType=JdbcType.INTEGER),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="REAL_AMOUNT", property="realAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="CHARGE_FEE", property="chargeFee", jdbcType=JdbcType.DECIMAL),
        @Result(column="STATE", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="WITHDRAW_TYPE", property="withdrawType", jdbcType=JdbcType.INTEGER),
        @Result(column="AUDIT_DATE", property="auditDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="AUDIT_OPERATOR", property="auditOperator", jdbcType=JdbcType.VARCHAR),
        @Result(column="AUDIT_MESSAGE", property="auditMessage", jdbcType=JdbcType.VARCHAR),
        @Result(column="GRANT_DATE", property="grantDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="GRANT_OPERATOR", property="grantOperator", jdbcType=JdbcType.VARCHAR),
        @Result(column="APPLY_DATE", property="applyDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP)
    })
    FinancialWithdraw selectByPrimaryKey(Integer withdrawId);

    @Select({
        "select",
        "WITHDRAW_ID, CUSTOMER_ID, BANK_ID, AMOUNT, REAL_AMOUNT, CHARGE_FEE, STATE, WITHDRAW_TYPE, AUDIT_DATE, ",
        "AUDIT_OPERATOR, AUDIT_MESSAGE, GRANT_DATE, GRANT_OPERATOR, APPLY_DATE, UPDATE_DATE",
        "from financial_withdraw"
    })
    @Results({
        @Result(column="WITHDRAW_ID", property="withdrawId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="BANK_ID", property="bankId", jdbcType=JdbcType.INTEGER),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="REAL_AMOUNT", property="realAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="CHARGE_FEE", property="chargeFee", jdbcType=JdbcType.DECIMAL),
        @Result(column="STATE", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="WITHDRAW_TYPE", property="withdrawType", jdbcType=JdbcType.INTEGER),
        @Result(column="AUDIT_DATE", property="auditDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="AUDIT_OPERATOR", property="auditOperator", jdbcType=JdbcType.VARCHAR),
        @Result(column="AUDIT_MESSAGE", property="auditMessage", jdbcType=JdbcType.VARCHAR),
        @Result(column="GRANT_DATE", property="grantDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="GRANT_OPERATOR", property="grantOperator", jdbcType=JdbcType.VARCHAR),
        @Result(column="APPLY_DATE", property="applyDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FinancialWithdraw> selectAll();

    @Update({
        "update financial_withdraw",
        "set CUSTOMER_ID = #{customerId,jdbcType=INTEGER},",
          "BANK_ID = #{bankId,jdbcType=INTEGER},",
          "AMOUNT = #{amount,jdbcType=DECIMAL},",
          "REAL_AMOUNT = #{realAmount,jdbcType=DECIMAL},",
          "CHARGE_FEE = #{chargeFee,jdbcType=DECIMAL},",
          "STATE = #{state,jdbcType=INTEGER},",
          "WITHDRAW_TYPE = #{withdrawType,jdbcType=INTEGER},",
          "AUDIT_DATE = #{auditDate,jdbcType=TIMESTAMP},",
          "AUDIT_OPERATOR = #{auditOperator,jdbcType=VARCHAR},",
          "AUDIT_MESSAGE = #{auditMessage,jdbcType=VARCHAR},",
          "GRANT_DATE = #{grantDate,jdbcType=TIMESTAMP},",
          "GRANT_OPERATOR = #{grantOperator,jdbcType=VARCHAR},",
          "APPLY_DATE = #{applyDate,jdbcType=TIMESTAMP},",
          "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}",
        "where WITHDRAW_ID = #{withdrawId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FinancialWithdraw record);
}