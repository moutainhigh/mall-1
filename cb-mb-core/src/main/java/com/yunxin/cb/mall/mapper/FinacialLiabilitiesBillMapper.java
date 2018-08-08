package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.FinacialLiabilitiesBill;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

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
    int insert(FinacialLiabilitiesBill record);

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
    FinacialLiabilitiesBill selectByPrimaryKey(Integer finacialLiabilitiesId);

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
    List<FinacialLiabilitiesBill> selectByCustomerId(int customerId);

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
    int updateByPrimaryKey(FinacialLiabilitiesBill record);
}