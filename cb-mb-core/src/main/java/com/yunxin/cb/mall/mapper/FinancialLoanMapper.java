package com.yunxin.cb.mall.mapper;

import com.github.pagehelper.Page;
import com.yunxin.cb.mall.entity.FinancialLoan;
import com.yunxin.cb.util.page.Query;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface FinancialLoanMapper {
    @Delete({
            "delete from financial_loan",
            "where LOAN_ID = #{loanId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer loanId);

    @Insert({
            "insert into financial_loan (LOAN_ID, CUSTOMER_ID, ",
            "BANK_ID, AMOUNT, TERM, ",
            "INTEREST_RATE, INTEREST, ",
            "TYPE, FINAL_REPAYMENT_TIME, ",
            "VERSION, REPAY_AMOUNT, ",
            "READY_AMOUNT, LEFT_AMOUNT, ",
            "LEFT_INTEREST, OVERDUE_NUMBER, ",
            "LATE_FEE, STATE, REPAYMENT_STATE, ",
            "AUDIT_TIME, AUDIT_REMARK, ",
            "TRANSFER_TIME, TRANSFER_REMARK, ",
            "CREATE_TIME)",
            "values ",
            "(#{loanId},#{customerId},#{bankId},#{amount},#{term},#{interestRate},#{interest},#{type},",
            "#{finalRepaymentTime},#{version},#{repayAmount},#{readyAmount},#{leftAmount},#{leftInterest},#{overdueNumber},",
            "#{lateFee},#{state},#{repaymentState},#{auditTime},#{auditRemark},#{transferTime},#{transferRemark},",
            "#{createTime})"
    })
    int insert(FinancialLoan record);

    @Select({
            "select",
            "*",
            "from financial_loan",
            "where LOAN_ID = #{loanId,jdbcType=INTEGER}"
    })
    @Results(id = "financialLoanMap", value = {
            @Result(column = "LOAN_ID", property = "loanId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
            @Result(column="BANK_ID", property="bankId", jdbcType=JdbcType.INTEGER),
            @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
            @Result(column="TERM", property="term", jdbcType=JdbcType.INTEGER),
            @Result(column="INTEREST_RATE", property="interestRate", jdbcType=JdbcType.DECIMAL),
            @Result(column="INTEREST", property="interest", jdbcType=JdbcType.DECIMAL),
            @Result(column="TYPE", property="type"),
            @Result(column="FINAL_REPAYMENT_TIME", property="finalRepaymentTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="VERSION", property="version", jdbcType=JdbcType.INTEGER),
            @Result(column="REPAY_AMOUNT", property="repayAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="READY_AMOUNT", property="readyAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="LEFT_AMOUNT", property="leftAmount", jdbcType=JdbcType.DECIMAL),
            @Result(column="LEFT_INTEREST", property="leftInterest", jdbcType=JdbcType.DECIMAL),
            @Result(column="OVERDUE_NUMBER", property="overdueNumber", jdbcType=JdbcType.INTEGER),
            @Result(column="LATE_FEE", property="lateFee", jdbcType=JdbcType.DECIMAL),
            @Result(column="STATE", property="state"),
            @Result(column="REPAYMENT_STATE", property="repaymentState"),
            @Result(column="AUDIT_TIME", property="auditTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="AUDIT_REMARK", property="auditRemark", jdbcType=JdbcType.VARCHAR),
            @Result(column="TRANSFER_TIME", property="transferTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="TRANSFER_REMARK", property="transferRemark", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    FinancialLoan selectByPrimaryKey(Integer loanId);

    @Select({
            "select",
            "*",
            "from financial_loan where CUSTOMER_ID = #{customerId,jdbcType=INTEGER}"
    })
    @ResultMap(value="financialLoanMap")
    List<FinancialLoan> selectByCustomerIdAndType(@Param("customerId") Integer customerId);

    @Update({
            "update financial_loan",
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
    int updateByPrimaryKey(FinancialLoan record);

    @Select({
            "<script>",
            "select",
            "*",
            "from financial_loan",
            "where 1=1",
            "<if test='customerId!=null'>",
            "and CUSTOMER_ID = #{customerId}",
            "</if>",
            "ORDER BY CREATE_TIME DESC",
            "</script>"
    })
    @ResultMap(value="financialLoanMap")
    Page<FinancialLoan> pageByCustomer(@Param("customerId")Integer customerId);

//    @Select({
//            "<script>",
//            "select",
//            "count(LOAN_ID)",
//            "from financial_loan",
//            "where 1=1",
//            "<if test='data.customerId!=null'>",
//            "and CUSTOMER_ID = #{data.customerId}",
//            "</if>",
//            "<if test='data.stateList!=null'>",
//                "and STATE in ",
//                    "<foreach collection='data.stateList' index='index' item='item' open='(' separator=',' close=')'>",
//                        " #{item} ",
//                    "</foreach> ",
//            "</if>",
//            "</script>"
//    })
//    int count(Query q);

    @Select({
            "<script>",
            "select",
            "count(LOAN_ID)",
            "from financial_loan",
            "where 1=1",
            "and CUSTOMER_ID = #{customerId}",
            "and STATE in (1,4)",
            "</script>"
    })
    int countLoanByCustomer(@Param("customerId")Integer customerId);

    @Select({
            "<script>",
            "select",
            "*",
            "from financial_loan",
            "where 1=1",
            "<if test='customerId!=null'>",
            "and CUSTOMER_ID = #{customerId}",
            "</if>",
            "and STATE = 4 and REPAYMENT_STATE &lt;&gt; 2",
            "ORDER BY CREATE_TIME ASC",
            "</script>"
    })
    @ResultMap(value="financialLoanMap")
    List<FinancialLoan> selectByCustomerRepaying(@Param("customerId")Integer customerId);
}