package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.FinancialLoanBill;
import com.yunxin.cb.util.page.Query;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface FinacialLiabilitiesBillMapper {
    @Delete({
        "delete from finacial_liabilities_bill",
        "where FINACIAL_LIABILITIES_ID = #{finacialLiabilitiesId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer finacialLiabilitiesId);

    @Insert({
        "insert into finacial_liabilities_bill (FINACIAL_LIABILITIES_ID, CUSTOMER_ID, ",
        "TYPE, TRANSACTION_TYPE, ",
        "TRANSACTION_DESC, AMOUNT, ",
        "CREATE_TIME)",
        "values (#{finacialLiabilitiesId,jdbcType=INTEGER}, #{customerId,jdbcType=INTEGER}, ",
        "#{type,jdbcType=VARCHAR}, #{transactionType,jdbcType=VARCHAR}, ",
        "#{transactionDesc,jdbcType=VARCHAR}, #{amount,jdbcType=DECIMAL}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(FinancialLoanBill record);

    @Select({
        "select",
        "FINACIAL_LIABILITIES_ID, CUSTOMER_ID, TYPE, TRANSACTION_TYPE, TRANSACTION_DESC, ",
        "AMOUNT, CREATE_TIME",
        "from finacial_liabilities_bill",
        "where FINACIAL_LIABILITIES_ID = #{finacialLiabilitiesId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="FINACIAL_LIABILITIES_ID", property="finacialLiabilitiesId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="TRANSACTION_TYPE", property="transactionType", jdbcType=JdbcType.VARCHAR),
        @Result(column="TRANSACTION_DESC", property="transactionDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    FinancialLoanBill selectByPrimaryKey(Integer finacialLiabilitiesId);

    @Select({
        "select",
        "FINACIAL_LIABILITIES_ID, CUSTOMER_ID, TYPE, TRANSACTION_TYPE, TRANSACTION_DESC, ",
        "AMOUNT, CREATE_TIME",
        "from finacial_liabilities_bill where CUSTOMER_ID = #{customerId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="FINACIAL_LIABILITIES_ID", property="finacialLiabilitiesId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="TRANSACTION_TYPE", property="transactionType", jdbcType=JdbcType.VARCHAR),
        @Result(column="TRANSACTION_DESC", property="transactionDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FinancialLoanBill> selectByCustomerId(int customerId);

    @Update({
        "update finacial_liabilities_bill",
        "set CUSTOMER_ID = #{customerId,jdbcType=INTEGER},",
          "TYPE = #{type,jdbcType=VARCHAR},",
          "TRANSACTION_TYPE = #{transactionType,jdbcType=VARCHAR},",
          "TRANSACTION_DESC = #{transactionDesc,jdbcType=VARCHAR},",
          "AMOUNT = #{amount,jdbcType=DECIMAL},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP}",
        "where FINACIAL_LIABILITIES_ID = #{finacialLiabilitiesId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FinancialLoanBill record);


    @Select("<script>"
            +"select FINACIAL_LIABILITIES_ID, CUSTOMER_ID, TYPE, TRANSACTION_TYPE, TRANSACTION_DESC,AMOUNT, CREATE_TIME"
            +" from finacial_liabilities_bill where 1=1"
            + "<if test='data.customerId!=null'>"
            + "AND CUSTOMER_ID = #{data.customerId} "
            + "</if>"
            + "ORDER BY CREATE_TIME DESC "
            + "LIMIT #{rowIndex},#{pageSize}"
            + "</script>")
    @Results({
            @Result(column="FINACIAL_FREEZING_ID", property="financialFreezingId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
            @Result(column="TYPE", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="TRANSACTION_TYPE", property="transactionType", jdbcType=JdbcType.INTEGER),
            @Result(column="TRANSACTION_DESC", property="transactionDesc", jdbcType=JdbcType.VARCHAR),
            @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FinancialLoanBill> pageList(Query q);

    @Select("<script>"
            +"select count(FINACIAL_LIABILITIES_ID) from finacial_liabilities_bill where 1=1"
            + "<if test='data.customerId!=null'>"
            + "AND CUSTOMER_ID = #{data.customerId} "
            + "</if>"
            + "</script>")
    long count(Query q);

}