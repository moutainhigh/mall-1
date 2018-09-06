package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.FinancialLoanRepayment;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface FinancialLoanRepaymentMapper {
    @Delete({
        "delete from financial_loan_repayment",
        "where REPAYMENT_ID = #{repaymentId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer repaymentId);

    @Insert({
        "insert into financial_loan_repayment (CUSTOMER_ID, ",
        "LOAN_ID, REPAY_AMOUNT, ",
        "REPAY_CAPITAL, REPAY_INTEREST, LOAN_REPAYMENT_TYPE, ",
        "CREATE_TIME)",
        "values (#{customerId,jdbcType=INTEGER}, ",
        "#{loanId,jdbcType=INTEGER}, #{repayAmount,jdbcType=DECIMAL}, ",
        "#{repayCapital,jdbcType=DECIMAL}, #{repayInterest,jdbcType=DECIMAL}, ",
        "#{type}, #{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(FinancialLoanRepayment record);

    @Select({
        "select",
        "*",
        "from financial_loan_repayment",
        "where REPAYMENT_ID = #{repaymentId,jdbcType=INTEGER}"
    })
    @Results(id = "loanRepayment", value = {
            @Result(column = "REPAYMENT_ID", property = "repaymentId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "CUSTOMER_ID", property = "customerId", jdbcType = JdbcType.INTEGER),
            @Result(column = "LOAN_ID", property = "loanId", jdbcType = JdbcType.INTEGER),
            @Result(column = "REPAY_AMOUNT", property = "repayAmount", jdbcType = JdbcType.DECIMAL),
            @Result(column = "REPAY_CAPITAL", property = "repayCapital", jdbcType = JdbcType.DECIMAL),
            @Result(column = "REPAY_INTEREST", property = "repayInterest", jdbcType = JdbcType.DECIMAL),
            @Result(column = "LOAN_REPAYMENT_TYPE", property = "type"),
            @Result(column = "CREATE_TIME", property = "createTime", jdbcType = JdbcType.TIMESTAMP)
    })
    FinancialLoanRepayment selectByPrimaryKey(@Param("repaymentId") Integer repaymentId);

    @Select({
        "select",
        "*",
        "from financial_loan_repayment where CUSTOMER_ID = #{customerId,jdbcType=INTEGER}"
    })
    @ResultMap(value = "loanRepayment")
    List<FinancialLoanRepayment> selectByCustomerId(@Param("customerId")Integer customerId);

}