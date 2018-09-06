package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.FinancialLogBill;
import com.yunxin.cb.mall.vo.FinancialLogVO;
import com.yunxin.cb.util.page.Query;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

@Mapper
public interface FinancialLogMapper {
    @Delete({
        "delete from financial_log_bill",
        "where LOG_ID = #{logId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer logId);

    @Insert({
        "insert into financial_log_bill (LOG_ID, CUSTOMER_ID, ",
        "CUSTOMER_NAME, TITLE, IMAGE, AMOUNT, ",
        "TYPE, TRANSACTION_TYPE, ",
        "PAY_TYPE, CREATE_TIME, ",
        "STATE, TRANSACTION_NO, ",
        "TRANSACTION_DESC)",
        "values (#{logId,jdbcType=INTEGER}, #{customerId,jdbcType=INTEGER}, ",
        "#{customerName,jdbcType=VARCHAR},#{title,jdbcType=VARCHAR},#{image,jdbcType=VARCHAR}, #{amount,jdbcType=DECIMAL}, ",
        "#{type,jdbcType=INTEGER}, #{transactionType,jdbcType=INTEGER}, ",
        "#{payType,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{state,jdbcType=INTEGER}, #{transactionNo,jdbcType=VARCHAR}, ",
        "#{transactionDesc,jdbcType=VARCHAR})"
    })
    int insert(FinancialLogBill record);

    @Select({
        "select",
        "LOG_ID, CUSTOMER_ID, CUSTOMER_NAME, TITLE, IMAGE, AMOUNT, TYPE, TRANSACTION_TYPE, PAY_TYPE, ",
        "CREATE_TIME, STATE, TRANSACTION_NO, TRANSACTION_DESC",
        "from financial_log_bill",
        "where LOG_ID = #{logId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="LOG_ID", property="logId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="CUSTOMER_NAME", property="customerName", jdbcType=JdbcType.VARCHAR),
        @Result(column="TITLE", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="IMAGE", property="image", jdbcType=JdbcType.VARCHAR),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="TRANSACTION_TYPE", property="transactionType", jdbcType=JdbcType.INTEGER),
        @Result(column="PAY_TYPE", property="payType", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="STATE", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="TRANSACTION_NO", property="transactionNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="TRANSACTION_DESC", property="transactionDesc", jdbcType=JdbcType.VARCHAR)
    })
    FinancialLogBill selectByPrimaryKey(Integer logId);

    @Select({
        "select",
        "LOG_ID, CUSTOMER_ID, CUSTOMER_NAME, TITLE, IMAGE, AMOUNT, TYPE, TRANSACTION_TYPE, PAY_TYPE, ",
        "CREATE_TIME, STATE, TRANSACTION_NO, TRANSACTION_DESC",
        "from financial_log_bill"
    })
    @Results({
        @Result(column="LOG_ID", property="logId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="CUSTOMER_NAME", property="customerName", jdbcType=JdbcType.VARCHAR),
        @Result(column="TITLE", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="IMAGE", property="image", jdbcType=JdbcType.VARCHAR),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="TRANSACTION_TYPE", property="transactionType", jdbcType=JdbcType.INTEGER),
        @Result(column="PAY_TYPE", property="payType", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="STATE", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="TRANSACTION_NO", property="transactionNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="TRANSACTION_DESC", property="transactionDesc", jdbcType=JdbcType.VARCHAR)
    })
    List<FinancialLogBill> selectAll();

    @Update({
        "update financial_log_bill",
        "set CUSTOMER_ID = #{customerId,jdbcType=INTEGER},",
          "CUSTOMER_NAME = #{customerName,jdbcType=VARCHAR},",
          "TITLE = #{title,jdbcType=VARCHAR},",
          "IMAGE = #{image,jdbcType=VARCHAR},",
          "AMOUNT = #{amount,jdbcType=DECIMAL},",
          "TYPE = #{type,jdbcType=INTEGER},",
          "TRANSACTION_TYPE = #{transactionType,jdbcType=INTEGER},",
          "PAY_TYPE = #{payType,jdbcType=INTEGER},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "STATE = #{state,jdbcType=INTEGER},",
          "TRANSACTION_NO = #{transactionNo,jdbcType=VARCHAR},",
          "TRANSACTION_DESC = #{transactionDesc,jdbcType=VARCHAR}",
        "where LOG_ID = #{logId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(FinancialLogBill record);

    @Select({
            "<script>",
            "select",
            "LOG_ID, IMAGE, AMOUNT, TYPE, TRANSACTION_TYPE,",
            "CREATE_TIME",
            "from financial_log_bill",
            "where 1=1",
            "<if test='data.customerId!=null'>",
            "and CUSTOMER_ID = #{data.customerId}",
            "</if>",
            "<if test='data.yearMonth!=null'>",
            "and DATE_FORMAT(CREATE_TIME, '%Y%m') = ${data.yearMonth}",
            "</if>",
            "ORDER BY CREATE_TIME DESC",
            "LIMIT #{rowIndex},#{pageSize}",
            "</script>"
    })
    @Results({
            @Result(column="LOG_ID", property="logId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
            @Result(column="TYPE", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="TRANSACTION_TYPE", property="transactionType", jdbcType=JdbcType.INTEGER),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<FinancialLogVO> pageList(Query q);

    @Select({
            "<script>",
            "select",
            "count(LOG_ID)",
            "from financial_log_bill",
            "where 1=1",
            "<if test='data.customerId!=null'>",
            "and CUSTOMER_ID = #{data.customerId}",
            "</if>",
            "<if test='data.yearMonth!=null'>",
            "and DATE_FORMAT(CREATE_TIME, '%Y%m') = ${data.yearMonth}",
            "</if>",
            "</script>"
    })
    long count(Query q);

    @Select({
            "<script>",
            "SELECT ",
            "(SELECT IFNULL(SUM(amount),0.00) FROM financial_log_bill WHERE TYPE=0 <if test='data.customerId!=null'> and CUSTOMER_ID = #{data.customerId} </if> <if test='data.yearMonth!=null'> and DATE_FORMAT(CREATE_TIME, '%Y%m') = ${data.yearMonth} </if>) AS addTotalAmount,",
            "(SELECT IFNULL(SUM(amount),0.00) FROM financial_log_bill WHERE TYPE=1 <if test='data.customerId!=null'> and CUSTOMER_ID = #{data.customerId} </if> <if test='data.yearMonth!=null'> and DATE_FORMAT(CREATE_TIME, '%Y%m') = ${data.yearMonth} </if>) AS subTotalAmount ",
            "FROM DUAL",
            "</script>"
    })
    Map queryTotalAmount(Query q);

    @Select({
            "select",
            "LOG_ID, CUSTOMER_ID, CUSTOMER_NAME, TITLE, IMAGE, AMOUNT, TYPE, TRANSACTION_TYPE, PAY_TYPE, ",
            "CREATE_TIME, STATE, TRANSACTION_NO, TRANSACTION_DESC",
            "from financial_log_bill",
            "where LOG_ID = #{logId,jdbcType=INTEGER} and CUSTOMER_ID = #{customerId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="LOG_ID", property="logId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
            @Result(column="CUSTOMER_NAME", property="customerName", jdbcType=JdbcType.VARCHAR),
            @Result(column="TITLE", property="title", jdbcType=JdbcType.VARCHAR),
            @Result(column="IMAGE", property="image", jdbcType=JdbcType.VARCHAR),
            @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
            @Result(column="TYPE", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="TRANSACTION_TYPE", property="transactionType", jdbcType=JdbcType.INTEGER),
            @Result(column="PAY_TYPE", property="payType", jdbcType=JdbcType.INTEGER),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="STATE", property="state", jdbcType=JdbcType.INTEGER),
            @Result(column="TRANSACTION_NO", property="transactionNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="TRANSACTION_DESC", property="transactionDesc", jdbcType=JdbcType.VARCHAR)
    })
    FinancialLogBill selectByPrimaryKeyAndCustomerId(@Param("logId")Integer logId, @Param("customerId")Integer customerId);
}