package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.FinacialLoan;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface FinacialLoanMapper {
    @Delete({
        "delete from finacial_loan",
        "where LOAN_ID = #{loanId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer loanId);

    @Insert({
        "insert into finacial_loan (LOAN_ID, CUSTOMER_ID, ",
        "AMOUNT, TERM, INTEREST_RATE, ",
        "TYPE, REPAY_DAY, ",
        "STATE, CREATE_TIME, ",
        "UPDATE_TIME, REPAYMENT_TERM, ",
        "FINAL_REPAYMENT_TIME, REPAY_AMOUNT, ",
        "READY_AMOUNT, SURPLUS_AMOUNT, ",
        "LATE_FEE, INTEREST, ",
        "OVERDUE_NUMER)",
        "values (#{loanId,jdbcType=INTEGER}, #{customerId,jdbcType=INTEGER}, ",
        "#{amount,jdbcType=DECIMAL}, #{term,jdbcType=INTEGER}, #{interestRate,jdbcType=DECIMAL}, ",
        "#{type,jdbcType=INTEGER}, #{repayDay,jdbcType=INTEGER}, ",
        "#{state,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{repaymentTerm,jdbcType=INTEGER}, ",
        "#{finalRepaymentTime,jdbcType=TIMESTAMP}, #{repayAmount,jdbcType=DECIMAL}, ",
        "#{readyAmount,jdbcType=DECIMAL}, #{surplusAmount,jdbcType=DECIMAL}, ",
        "#{lateFee,jdbcType=DECIMAL}, #{interest,jdbcType=DECIMAL}, ",
        "#{overdueNumer,jdbcType=INTEGER})"
    })
    int insert(FinacialLoan record);

    @Select({
        "select",
        "LOAN_ID, CUSTOMER_ID, AMOUNT, TERM, INTEREST_RATE, TYPE, REPAY_DAY, STATE, CREATE_TIME, ",
        "UPDATE_TIME, REPAYMENT_TERM, FINAL_REPAYMENT_TIME, REPAY_AMOUNT, READY_AMOUNT, ",
        "SURPLUS_AMOUNT, LATE_FEE, INTEREST, OVERDUE_NUMER",
        "from finacial_loan",
        "where LOAN_ID = #{loanId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="LOAN_ID", property="loanId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="TERM", property="term", jdbcType=JdbcType.INTEGER),
        @Result(column="INTEREST_RATE", property="interestRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="REPAY_DAY", property="repayDay", jdbcType=JdbcType.INTEGER),
        @Result(column="STATE", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REPAYMENT_TERM", property="repaymentTerm", jdbcType=JdbcType.INTEGER),
        @Result(column="FINAL_REPAYMENT_TIME", property="finalRepaymentTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REPAY_AMOUNT", property="repayAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="READY_AMOUNT", property="readyAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="SURPLUS_AMOUNT", property="surplusAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="LATE_FEE", property="lateFee", jdbcType=JdbcType.DECIMAL),
        @Result(column="INTEREST", property="interest", jdbcType=JdbcType.DECIMAL),
        @Result(column="OVERDUE_NUMER", property="overdueNumer", jdbcType=JdbcType.INTEGER)
    })
    FinacialLoan selectByPrimaryKey(Integer loanId);

    @Select({
        "select",
        "LOAN_ID, CUSTOMER_ID, AMOUNT, TERM, INTEREST_RATE, TYPE, REPAY_DAY, STATE, CREATE_TIME, ",
        "UPDATE_TIME, REPAYMENT_TERM, FINAL_REPAYMENT_TIME, REPAY_AMOUNT, READY_AMOUNT, ",
        "SURPLUS_AMOUNT, LATE_FEE, INTEREST, OVERDUE_NUMER",
        "from finacial_loan where CUSTOMER_ID = #{customerId,jdbcType=INTEGER} ",
        " order by CREATE_TIME asc"
    })
    @Results({
        @Result(column="LOAN_ID", property="loanId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="TERM", property="term", jdbcType=JdbcType.INTEGER),
        @Result(column="INTEREST_RATE", property="interestRate", jdbcType=JdbcType.DECIMAL),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="REPAY_DAY", property="repayDay", jdbcType=JdbcType.INTEGER),
        @Result(column="STATE", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REPAYMENT_TERM", property="repaymentTerm", jdbcType=JdbcType.INTEGER),
        @Result(column="FINAL_REPAYMENT_TIME", property="finalRepaymentTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REPAY_AMOUNT", property="repayAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="READY_AMOUNT", property="readyAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="SURPLUS_AMOUNT", property="surplusAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="LATE_FEE", property="lateFee", jdbcType=JdbcType.DECIMAL),
        @Result(column="INTEREST", property="interest", jdbcType=JdbcType.DECIMAL),
        @Result(column="OVERDUE_NUMER", property="overdueNumer", jdbcType=JdbcType.INTEGER)
    })
    List<FinacialLoan> selectByCustomerId(int customerId);

    @Update({
        "update finacial_loan",
        "set CUSTOMER_ID = #{customerId,jdbcType=INTEGER},",
          "AMOUNT = #{amount,jdbcType=DECIMAL},",
          "TERM = #{term,jdbcType=INTEGER},",
          "INTEREST_RATE = #{interestRate,jdbcType=DECIMAL},",
          "TYPE = #{type,jdbcType=INTEGER},",
          "REPAY_DAY = #{repayDay,jdbcType=INTEGER},",
          "STATE = #{state,jdbcType=INTEGER},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP},",
          "REPAYMENT_TERM = #{repaymentTerm,jdbcType=INTEGER},",
          "FINAL_REPAYMENT_TIME = #{finalRepaymentTime,jdbcType=TIMESTAMP},",
          "REPAY_AMOUNT = #{repayAmount,jdbcType=DECIMAL},",
          "READY_AMOUNT = #{readyAmount,jdbcType=DECIMAL},",
          "SURPLUS_AMOUNT = #{surplusAmount,jdbcType=DECIMAL},",
          "LATE_FEE = #{lateFee,jdbcType=DECIMAL},",
          "INTEREST = #{interest,jdbcType=DECIMAL},",
          "OVERDUE_NUMER = #{overdueNumer,jdbcType=INTEGER}",
        "where LOAN_ID = #{loanId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FinacialLoan record);
}