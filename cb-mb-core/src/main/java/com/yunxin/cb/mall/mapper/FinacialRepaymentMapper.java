package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.FinacialRepayment;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

public interface FinacialRepaymentMapper {
    @Delete({
        "delete from finacial_repayment",
        "where REPAYMENT_ID = #{repaymentId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer repaymentId);

    @Insert({
        "insert into finacial_repayment (REPAYMENT_ID, CUSTOMER_ID, ",
        "LOAN_ID, AMOUNT, ",
        "SEQ, LATE_FEE, INTEREST, ",
        "CREATE_TIME, REPAY_AMOUNT, ",
        "READY_REPAYMENT_TIME, REPAY_TIME)",
        "values (#{repaymentId,jdbcType=INTEGER}, #{customerId,jdbcType=INTEGER}, ",
        "#{loanId,jdbcType=INTEGER}, #{amount,jdbcType=DECIMAL}, ",
        "#{seq,jdbcType=INTEGER}, #{lateFee,jdbcType=DECIMAL}, #{interest,jdbcType=DECIMAL}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{repayAmount,jdbcType=DECIMAL}, ",
        "#{readyRepaymentTime,jdbcType=TIMESTAMP}, #{repayTime,jdbcType=TIMESTAMP})"
    })
    int insert(FinacialRepayment record);

    @Select({
        "select",
        "REPAYMENT_ID, CUSTOMER_ID, LOAN_ID, AMOUNT, SEQ, LATE_FEE, INTEREST, CREATE_TIME, ",
        "REPAY_AMOUNT, READY_REPAYMENT_TIME, REPAY_TIME",
        "from finacial_repayment",
        "where REPAYMENT_ID = #{repaymentId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="REPAYMENT_ID", property="repaymentId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="LOAN_ID", property="loanId", jdbcType=JdbcType.INTEGER),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="SEQ", property="seq", jdbcType=JdbcType.INTEGER),
        @Result(column="LATE_FEE", property="lateFee", jdbcType=JdbcType.DECIMAL),
        @Result(column="INTEREST", property="interest", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REPAY_AMOUNT", property="repayAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="READY_REPAYMENT_TIME", property="readyRepaymentTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REPAY_TIME", property="repayTime", jdbcType=JdbcType.TIMESTAMP)
    })
    FinacialRepayment selectByPrimaryKey(Integer repaymentId);

    @Select({
        "select",
        "REPAYMENT_ID, CUSTOMER_ID, LOAN_ID, AMOUNT, SEQ, LATE_FEE, INTEREST, CREATE_TIME, ",
        "REPAY_AMOUNT, READY_REPAYMENT_TIME, REPAY_TIME",
        "from finacial_repayment where CUSTOMER_ID = #{customerId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="REPAYMENT_ID", property="repaymentId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="LOAN_ID", property="loanId", jdbcType=JdbcType.INTEGER),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="SEQ", property="seq", jdbcType=JdbcType.INTEGER),
        @Result(column="LATE_FEE", property="lateFee", jdbcType=JdbcType.DECIMAL),
        @Result(column="INTEREST", property="interest", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REPAY_AMOUNT", property="repayAmount", jdbcType=JdbcType.DECIMAL),
        @Result(column="READY_REPAYMENT_TIME", property="readyRepaymentTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REPAY_TIME", property="repayTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FinacialRepayment> selectByCustomerId(int customerId);

    @Update({
        "update finacial_repayment",
        "set CUSTOMER_ID = #{customerId,jdbcType=INTEGER},",
          "LOAN_ID = #{loanId,jdbcType=INTEGER},",
          "AMOUNT = #{amount,jdbcType=DECIMAL},",
          "SEQ = #{seq,jdbcType=INTEGER},",
          "LATE_FEE = #{lateFee,jdbcType=DECIMAL},",
          "INTEREST = #{interest,jdbcType=DECIMAL},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "REPAY_AMOUNT = #{repayAmount,jdbcType=DECIMAL},",
          "READY_REPAYMENT_TIME = #{readyRepaymentTime,jdbcType=TIMESTAMP},",
          "REPAY_TIME = #{repayTime,jdbcType=TIMESTAMP}",
        "where REPAYMENT_ID = #{repaymentId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FinacialRepayment record);
}