package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.FinancialCreditLineBill;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FinancialCreditLineBillMapper {
    @Delete({
        "delete from financial_credit_line_bill",
        "where CREDIT_LINE_ID = #{creditLineId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer creditLineId);

    @Insert({
        "insert into financial_credit_line_bill (CUSTOMER_ID, ",
        "TYPE, TRANSACTION_TYPE, ",
        "TRANSACTION_DESC, AMOUNT, ",
        "CREATE_TIME)",
        "values (#{customerId,jdbcType=INTEGER}, ",
        "#{type}, #{transactionType}, ",
        "#{transactionDesc,jdbcType=VARCHAR}, #{amount,jdbcType=DECIMAL}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(FinancialCreditLineBill record);
}