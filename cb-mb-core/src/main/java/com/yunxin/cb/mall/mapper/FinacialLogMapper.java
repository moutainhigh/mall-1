package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.FiaciaLog;
import com.yunxin.cb.mall.vo.FiaciaLogVO;
import com.yunxin.cb.util.page.Query;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

@Mapper
public interface FinacialLogMapper {
    @Delete({
        "delete from finacial_log",
        "where LOG_ID = #{logId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer logId);

    @Insert({
        "insert into finacial_log (LOG_ID, CUSTOMER_ID, ",
        "CUSTOMER_NAME, AMOUNT, ",
        "TYPE, TRANSACTION_TYPE, ",
        "PAY_TYPE, CREATE_TIME, ",
        "STATE, TRANSACTION_NO, ",
        "TRANSACTION_DESC)",
        "values (#{logId,jdbcType=INTEGER}, #{customerId,jdbcType=INTEGER}, ",
        "#{customerName,jdbcType=VARCHAR}, #{amount,jdbcType=DECIMAL}, ",
        "#{type,jdbcType=INTEGER}, #{transactionType,jdbcType=INTEGER}, ",
        "#{payType,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{state,jdbcType=INTEGER}, #{transactionNo,jdbcType=VARCHAR}, ",
        "#{transactionDesc,jdbcType=VARCHAR})"
    })
    int insert(FiaciaLog record);

    @Select({
        "select",
        "LOG_ID, CUSTOMER_ID, CUSTOMER_NAME, AMOUNT, TYPE, TRANSACTION_TYPE, PAY_TYPE, ",
        "CREATE_TIME, STATE, TRANSACTION_NO, TRANSACTION_DESC",
        "from finacial_log",
        "where LOG_ID = #{logId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="LOG_ID", property="logId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="CUSTOMER_NAME", property="customerName", jdbcType=JdbcType.VARCHAR),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="TRANSACTION_TYPE", property="transactionType", jdbcType=JdbcType.INTEGER),
        @Result(column="PAY_TYPE", property="payType", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="STATE", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="TRANSACTION_NO", property="transactionNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="TRANSACTION_DESC", property="transactionDesc", jdbcType=JdbcType.VARCHAR)
    })
    FiaciaLog selectByPrimaryKey(Integer logId);

    @Select({
        "select",
        "LOG_ID, CUSTOMER_ID, CUSTOMER_NAME, AMOUNT, TYPE, TRANSACTION_TYPE, PAY_TYPE, ",
        "CREATE_TIME, STATE, TRANSACTION_NO, TRANSACTION_DESC",
        "from finacial_log"
    })
    @Results({
        @Result(column="LOG_ID", property="logId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="CUSTOMER_NAME", property="customerName", jdbcType=JdbcType.VARCHAR),
        @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
        @Result(column="TYPE", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="TRANSACTION_TYPE", property="transactionType", jdbcType=JdbcType.INTEGER),
        @Result(column="PAY_TYPE", property="payType", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="STATE", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="TRANSACTION_NO", property="transactionNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="TRANSACTION_DESC", property="transactionDesc", jdbcType=JdbcType.VARCHAR)
    })
    List<FiaciaLog> selectAll();

    @Update({
        "update finacial_log",
        "set CUSTOMER_ID = #{customerId,jdbcType=INTEGER},",
          "CUSTOMER_NAME = #{customerName,jdbcType=VARCHAR},",
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
    int updateByPrimaryKey(FiaciaLog record);

    @Select({
            "<script>",
            "select",
            "LOG_ID, CUSTOMER_ID, CUSTOMER_NAME, AMOUNT, TYPE, TRANSACTION_TYPE, PAY_TYPE, ",
            "CREATE_TIME, STATE, TRANSACTION_NO, TRANSACTION_DESC",
            "from finacial_log",
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
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
            @Result(column="CUSTOMER_NAME", property="customerName", jdbcType=JdbcType.VARCHAR),
            @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
            @Result(column="TYPE", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="TRANSACTION_TYPE", property="transactionType", jdbcType=JdbcType.INTEGER),
            @Result(column="PAY_TYPE", property="payType", jdbcType=JdbcType.INTEGER),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="STATE", property="state", jdbcType=JdbcType.INTEGER),
            @Result(column="TRANSACTION_NO", property="transactionNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="TRANSACTION_DESC", property="transactionDesc", jdbcType=JdbcType.VARCHAR)
    })
    List<FiaciaLogVO> pageList(Query q);

    @Select({
            "<script>",
            "select",
            "count(LOG_ID)",
            "from finacial_log",
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
            "(SELECT IFNULL(SUM(amount),0.00) FROM finacial_log WHERE TYPE=0 <if test='data.customerId!=null'> and CUSTOMER_ID = #{data.customerId} </if> <if test='data.yearMonth!=null'> and DATE_FORMAT(CREATE_TIME, '%Y%m') = ${data.yearMonth} </if>) AS addTotalAmount,",
            "(SELECT IFNULL(SUM(amount),0.00) FROM finacial_log WHERE TYPE=1 <if test='data.customerId!=null'> and CUSTOMER_ID = #{data.customerId} </if> <if test='data.yearMonth!=null'> and DATE_FORMAT(CREATE_TIME, '%Y%m') = ${data.yearMonth} </if>) AS subTotalAmount ",
            "FROM DUAL",
            "</script>"
    })
    Map queryTotalAmount(Query q);
}