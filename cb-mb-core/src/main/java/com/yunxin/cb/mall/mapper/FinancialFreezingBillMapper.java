package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.FinancialFreezingBill;
import com.yunxin.cb.util.page.Query;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface FinancialFreezingBillMapper {
    @Delete({
        "delete from financial_freezing_bill",
        "where FINACIAL_FREEZING_ID = #{financialFreezingId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer financialFreezingId);

    @Insert({
        "insert into financial_freezing_bill (FINACIAL_FREEZING_ID, CUSTOMER_ID, ",
        "TYPE, TRANSACTION_TYPE, ",
        "TRANSACTION_DESC, AMOUNT, ",
        "CREATE_TIME)",
        "values (#{financialFreezingId,jdbcType=INTEGER}, #{customerId,jdbcType=INTEGER}, ",
        "#{type,jdbcType=INTEGER}, #{transactionType,jdbcType=INTEGER}, ",
        "#{transactionDesc,jdbcType=VARCHAR}, #{amount,jdbcType=DECIMAL}, ",
        "#{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(FinancialFreezingBill record);

    @Select({
        "select",
        "FINACIAL_FREEZING_ID, CUSTOMER_ID, TYPE, TRANSACTION_TYPE, TRANSACTION_DESC, AMOUNT, ",
        "CREATE_TIME",
        "from financial_freezing_bill",
        "where FINACIAL_FREEZING_ID = #{financialFreezingId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="FINACIAL_FREEZING_ID", property="financialFreezingId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="TRANSACTION_TYPE", property="transactionType", jdbcType=JdbcType.INTEGER),
        @Result(column="TRANSACTION_DESC", property="transactionDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    FinancialFreezingBill selectByPrimaryKey(Integer financialFreezingId);

    @Select({
        "select",
        "FINACIAL_FREEZING_ID, CUSTOMER_ID, TYPE, TRANSACTION_TYPE, TRANSACTION_DESC, AMOUNT, ",
        "CREATE_TIME",
        "from financial_freezing_bill where CUSTOMER_ID = #{customerId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="FINACIAL_FREEZING_ID", property="financialFreezingId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="TRANSACTION_TYPE", property="transactionType", jdbcType=JdbcType.INTEGER),
        @Result(column="TRANSACTION_DESC", property="transactionDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FinancialFreezingBill> selectByCustomerId(int customerId);

    @Update({
        "update financial_freezing_bill",
        "set CUSTOMER_ID = #{customerId,jdbcType=INTEGER},",
          "TYPE = #{type,jdbcType=INTEGER},",
          "TRANSACTION_TYPE = #{transactionType,jdbcType=INTEGER},",
          "TRANSACTION_DESC = #{transactionDesc,jdbcType=VARCHAR},",
          "AMOUNT = #{amount,jdbcType=DECIMAL},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP}",
        "where FINACIAL_FREEZING_ID = #{financialFreezingId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FinancialFreezingBill record);

    @Select("<script>"
            +"select FINACIAL_FREEZING_ID, CUSTOMER_ID, TYPE, TRANSACTION_TYPE, TRANSACTION_DESC, AMOUNT,CREATE_TIME"
            +" from financial_freezing_bill where 1=1"
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
    List<FinancialFreezingBill> pageList(Query q);

    @Select("<script>"
            +"select count(FINACIAL_FREEZING_ID) from financial_freezing_bill where 1=1"
            + "<if test='data.customerId!=null'>"
            + "AND CUSTOMER_ID = #{data.customerId} "
            + "</if>"
            + "</script>")
    long count(Query q);
}