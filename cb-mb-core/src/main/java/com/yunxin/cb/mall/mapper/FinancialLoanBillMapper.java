package com.yunxin.cb.mall.mapper;

import com.github.pagehelper.Page;
import com.yunxin.cb.mall.entity.FinancialLoanBill;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface FinancialLoanBillMapper {

    @Insert({
            "insert into financial_loan_bill (CUSTOMER_ID, ",
            "TYPE, TRANSACTION_TYPE, ",
            "TRANSACTION_DESC, AMOUNT, ",
            "CREATE_TIME)",
            "values (#{customerId,jdbcType=INTEGER}, ",
            "#{type}, #{transactionType}, ",
            "#{transactionDesc,jdbcType=VARCHAR}, #{amount,jdbcType=DECIMAL}, ",
            "#{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(FinancialLoanBill record);

    @Select({
            "select",
            "*",
            "from financial_loan_bill",
            "where LOAN_BILL_ID = #{loanBillId,jdbcType=INTEGER}"
    })
    @Results(id = "loanBill", value = {
            @Result(column = "LOAN_BILL_ID", property = "loanBillId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "CUSTOMER_ID", property = "customerId", jdbcType = JdbcType.INTEGER),
            @Result(column = "TYPE", property = "type", jdbcType = JdbcType.VARCHAR),
            @Result(column = "TRANSACTION_TYPE", property = "transactionType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "TRANSACTION_DESC", property = "transactionDesc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "AMOUNT", property = "amount", jdbcType = JdbcType.DECIMAL),
            @Result(column = "CREATE_TIME", property = "createTime", jdbcType = JdbcType.TIMESTAMP)
    })
    FinancialLoanBill selectByPrimaryKey(Integer loanBillId);

    @Select({
            "select",
            "*",
            "from financial_loan_bill",
            "where CUSTOMER_ID = #{customerId,jdbcType=INTEGER}"
    })
    @ResultMap(value = "loanBill")
    List<FinancialLoanBill> selectByCustomerId(@Param("customerId") Integer customerId);


    @Select({
            "select * ",
            "from financial_loan_bill where 1=1 ",
            "AND CUSTOMER_ID = #{customerId} ",
            "ORDER BY CREATE_TIME DESC "}
    )
    @ResultMap(value = "loanBill")
    Page<FinancialLoanBill> pageListByCustomer(@Param("customerId") Integer customerId);


}