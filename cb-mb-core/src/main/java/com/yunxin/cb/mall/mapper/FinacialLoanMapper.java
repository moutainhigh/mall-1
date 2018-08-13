package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.FinacialLoan;
import com.yunxin.cb.util.page.Query;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
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
            "OVERDUE_NUMER, BANK_ID)",
            "values (#{loanId,jdbcType=INTEGER}, #{customerId,jdbcType=INTEGER}, ",
            "#{amount,jdbcType=DECIMAL}, #{term,jdbcType=INTEGER}, #{interestRate,jdbcType=DECIMAL}, ",
            "#{type,jdbcType=VARCHAR}, #{repayDay,jdbcType=INTEGER}, ",
            "#{state,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
            "#{updateTime,jdbcType=TIMESTAMP}, #{repaymentTerm,jdbcType=INTEGER}, ",
            "#{finalRepaymentTime,jdbcType=TIMESTAMP}, #{repayAmount,jdbcType=DECIMAL}, ",
            "#{readyAmount,jdbcType=DECIMAL}, #{surplusAmount,jdbcType=DECIMAL}, ",
            "#{lateFee,jdbcType=DECIMAL}, #{interest,jdbcType=DECIMAL}, ",
            "#{overdueNumer,jdbcType=INTEGER}, #{bankId,jdbcType=INTEGER},",
            "#{creditAmount,jdbcType=DECIMAL},#{insuranceAmount,jdbcType=DECIMAL})"
    })
    int insert(FinacialLoan record);

    @Select({
            "select",
            "LOAN_ID, CUSTOMER_ID, AMOUNT, TERM, INTEREST_RATE, TYPE, REPAY_DAY, STATE, CREATE_TIME, ",
            "UPDATE_TIME, REPAYMENT_TERM, FINAL_REPAYMENT_TIME, REPAY_AMOUNT, READY_AMOUNT, ",
            "SURPLUS_AMOUNT, LATE_FEE, INTEREST, OVERDUE_NUMER, BANK_ID,CREDIT_AMOUNT,INSURANCE_AMOUNT",
            "from finacial_loan",
            "where LOAN_ID = #{loanId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="LOAN_ID", property="loanId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
            @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
            @Result(column="TERM", property="term", jdbcType=JdbcType.INTEGER),
            @Result(column="INTEREST_RATE", property="interestRate", jdbcType=JdbcType.DECIMAL),
            @Result(column="TYPE", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="REPAY_DAY", property="repayDay", jdbcType=JdbcType.INTEGER),
            @Result(column="STATE", property="state", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="REPAYMENT_TERM", property="repaymentTerm", jdbcType=JdbcType.INTEGER),
            @Result(column="FINAL_REPAYMENT_TIME", property="finalRepaymentTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="REPAY_AMOUNT", property="repayAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="READY_AMOUNT", property="readyAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="SURPLUS_AMOUNT", property="surplusAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="LATE_FEE", property="lateFee", jdbcType=JdbcType.DECIMAL),
            @Result(column="INTEREST", property="interest", jdbcType=JdbcType.DECIMAL),
            @Result(column="OVERDUE_NUMER", property="overdueNumer", jdbcType=JdbcType.INTEGER),
            @Result(column="BANK_ID", property="bankId", jdbcType=JdbcType.INTEGER),
            @Result(column="CREDIT_AMOUNT", property="creditAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="INSURANCE_AMOUNT", property="insuranceAmount", jdbcType=JdbcType.DECIMAL)
    })
    FinacialLoan selectByPrimaryKey(Integer loanId);

    @Select({
            "select",
            "LOAN_ID, CUSTOMER_ID, AMOUNT, TERM, INTEREST_RATE, TYPE, REPAY_DAY, STATE, CREATE_TIME, ",
            "UPDATE_TIME, REPAYMENT_TERM, FINAL_REPAYMENT_TIME, REPAY_AMOUNT, READY_AMOUNT, ",
            "SURPLUS_AMOUNT, LATE_FEE, INTEREST, OVERDUE_NUMER, BANK_ID,CREDIT_AMOUNT,INSURANCE_AMOUNT",
            "from finacial_loan where CUSTOMER_ID = #{customerId,jdbcType=INTEGER}"
    })
    @Results(id = "finacialLoanMap", value = {
            @Result(column="LOAN_ID", property="loanId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
            @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
            @Result(column="TERM", property="term", jdbcType=JdbcType.INTEGER),
            @Result(column="INTEREST_RATE", property="interestRate", jdbcType=JdbcType.DECIMAL),
            @Result(column="TYPE", property="type", jdbcType=JdbcType.VARCHAR),
            @Result(column="REPAY_DAY", property="repayDay", jdbcType=JdbcType.INTEGER),
            @Result(column="STATE", property="state", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="REPAYMENT_TERM", property="repaymentTerm", jdbcType=JdbcType.INTEGER),
            @Result(column="FINAL_REPAYMENT_TIME", property="finalRepaymentTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="REPAY_AMOUNT", property="repayAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="READY_AMOUNT", property="readyAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="SURPLUS_AMOUNT", property="surplusAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="LATE_FEE", property="lateFee", jdbcType=JdbcType.DECIMAL),
            @Result(column="INTEREST", property="interest", jdbcType=JdbcType.DECIMAL),
            @Result(column="OVERDUE_NUMER", property="overdueNumer", jdbcType=JdbcType.INTEGER),
            @Result(column="BANK_ID", property="bankId", jdbcType=JdbcType.INTEGER),
            @Result(column="CREDIT_AMOUNT", property="creditAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="INSURANCE_AMOUNT", property="insuranceAmount", jdbcType=JdbcType.DECIMAL)
    })
    List<FinacialLoan> selectByCustomerIdAndType(@Param("customerId") Integer customerId);

    @Update({
            "update finacial_loan",
            "set CUSTOMER_ID = #{customerId,jdbcType=INTEGER},",
            "AMOUNT = #{amount,jdbcType=DECIMAL},",
            "TERM = #{term,jdbcType=INTEGER},",
            "INTEREST_RATE = #{interestRate,jdbcType=DECIMAL},",
            "TYPE = #{type,jdbcType=VARCHAR},",
            "REPAY_DAY = #{repayDay,jdbcType=INTEGER},",
            "STATE = #{state,jdbcType=VARCHAR},",
            "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
            "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP},",
            "REPAYMENT_TERM = #{repaymentTerm,jdbcType=INTEGER},",
            "FINAL_REPAYMENT_TIME = #{finalRepaymentTime,jdbcType=TIMESTAMP},",
            "REPAY_AMOUNT = #{repayAmount,jdbcType=DECIMAL},",
            "READY_AMOUNT = #{readyAmount,jdbcType=DECIMAL},",
            "SURPLUS_AMOUNT = #{surplusAmount,jdbcType=DECIMAL},",
            "LATE_FEE = #{lateFee,jdbcType=DECIMAL},",
            "INTEREST = #{interest,jdbcType=DECIMAL},",
            "OVERDUE_NUMER = #{overdueNumer,jdbcType=INTEGER},",
            "BANK_ID = #{bankId,jdbcType=INTEGER}",
            "CREDIT_AMOUNT = #{bankId,jdbcType=INTEGER}",
            "BANK_ID = #{creditAmount,jdbcType=INTEGER}",
            "INSURANCE_AMOUNT LOAN_ID = #{insuranceAmount,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FinacialLoan record);

    @Select({
            "<script>",
            "select",
            "LOAN_ID, CUSTOMER_ID, AMOUNT, TERM, INTEREST_RATE, TYPE, REPAY_DAY, STATE, CREATE_TIME, ",
            "UPDATE_TIME, REPAYMENT_TERM, FINAL_REPAYMENT_TIME, REPAY_AMOUNT, READY_AMOUNT, ",
            "SURPLUS_AMOUNT, LATE_FEE, INTEREST, OVERDUE_NUMER, BANK_ID,CREDIT_AMOUNT,INSURANCE_AMOUNT",
            "from finacial_loan",
            "where 1=1",
            "<if test='data.customerId!=null'>",
            "and CUSTOMER_ID = #{data.customerId}",
            "</if>",
            "ORDER BY CREATE_TIME DESC",
            "LIMIT #{rowIndex},#{pageSize}",
            "</script>"
    })
    @ResultMap(value="finacialLoanMap")
    List<FinacialLoan> pageList(Query q);

    @Select({
            "<script>",
            "select",
            "count(LOAN_ID)",
            "from finacial_loan",
            "where 1=1",
            "<if test='data.customerId!=null'>",
            "and CUSTOMER_ID = #{data.customerId}",
            "</if>",
            "<if test='data.stateList!=null'>",
                "and STATE in ",
                    "<foreach collection='data.stateList' index='index' item='item' open='(' separator=',' close=')'>",
                        " #{item} ",
                    "</foreach> ",
            "</if>",
            "</script>"
    })
    int count(Query q);

}