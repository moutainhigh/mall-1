package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.FinancialLoanConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface FinancialLoanConfigMapper {

    @Select({
            "SELECT",
            "LOAN_CONFIG_ID, TERM, INTEREST_RATE, TITLE, REMARK",
            "FROM financial_loan_config",
            "ORDER BY TERM"
    })
    @Results({
            @Result(column="LOAN_CONFIG_ID", property="loanConfigId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="TERM", property="term", jdbcType=JdbcType.INTEGER),
            @Result(column="INTEREST_RATE", property="interestRate", jdbcType=JdbcType.DECIMAL),
            @Result(column="TITLE", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<FinancialLoanConfig> selectAll();

    @Select({
            "SELECT",
            "LOAN_CONFIG_ID, TERM, INTEREST_RATE, TITLE, REMARK",
            "FROM financial_loan_config",
            "where LOAN_CONFIG_ID = #{loanConfigId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="LOAN_CONFIG_ID", property="loanConfigId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="TERM", property="term", jdbcType=JdbcType.INTEGER),
            @Result(column="INTEREST_RATE", property="interestRate", jdbcType=JdbcType.DECIMAL),
            @Result(column="TITLE", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    FinancialLoanConfig selectByPrimaryKey(int loanConfigId);
}