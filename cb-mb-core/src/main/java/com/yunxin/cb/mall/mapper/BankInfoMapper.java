package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.BankInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import java.util.List;
@Mapper
public interface BankInfoMapper {

       @Delete({
               "delete from bank_info",
               "where BANK_ID = #{bankId,jdbcType=INTEGER} and CUSTOMER_ID=#{customerId,jdbcType=INTEGER}"
       })
       int deleteByPrimaryKey(@Param("bankId")Integer bankId,@Param("customerId")Integer customerId);

       @Insert({
               "insert into bank_info (BANK_ID, BANK_CARD_NUMBER, BANK_NAME,",
               "EFFECTIVE_TIME, CARDHOLDER, ",
               "CARD_TYPE, CUSTOMER_CARD_NO, ",
               "MOBILE, CUSTOMER_ID, ",
               "CREATE_TIME)",
               "values (#{bankId,jdbcType=INTEGER}, #{bankCardNumber,jdbcType=VARCHAR},#{bankName,jdbcType=VARCHAR}, ",
               "#{effectiveTime,jdbcType=VARCHAR}, #{cardholder,jdbcType=VARCHAR}, ",
               "#{cardType,jdbcType=VARCHAR}, #{customerCardNo,jdbcType=VARCHAR}, ",
               "#{mobile,jdbcType=VARCHAR}, #{customerId,jdbcType=INTEGER}, ",
               "#{createTime,jdbcType=TIMESTAMP})"
       })
       int insert(BankInfo record);

       @Select({
               "select",
               "BANK_ID, BANK_CARD_NUMBER, BANK_NAME, EFFECTIVE_TIME, CARDHOLDER, CARD_TYPE, CUSTOMER_CARD_NO, ",
               "MOBILE, CUSTOMER_ID, CREATE_TIME",
               "from bank_info",
               "where BANK_ID = #{bankId,jdbcType=INTEGER} AND CUSTOMER_ID = #{customerId,jdbcType=INTEGER}"
       })
       @Results({
               @Result(column="BANK_ID", property="bankId", jdbcType=JdbcType.INTEGER, id=true),
               @Result(column="BANK_CARD_NUMBER", property="bankCardNumber", jdbcType=JdbcType.VARCHAR),
               @Result(column="BANK_NAME", property="bankName", jdbcType=JdbcType.VARCHAR),
               @Result(column="EFFECTIVE_TIME", property="effectiveTime", jdbcType=JdbcType.VARCHAR),
               @Result(column="CARDHOLDER", property="cardholder", jdbcType=JdbcType.VARCHAR),
               @Result(column="CARD_TYPE", property="cardType", jdbcType=JdbcType.VARCHAR),
               @Result(column="CUSTOMER_CARD_NO", property="customerCardNo", jdbcType=JdbcType.VARCHAR),
               @Result(column="MOBILE", property="mobile", jdbcType=JdbcType.VARCHAR),
               @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
               @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
       })
       BankInfo selectByPrimaryKey(@Param("bankId")Integer bankId,@Param("customerId")Integer customerId);

       @Select({
               "select",
               "BANK_ID, BANK_CARD_NUMBER, BANK_NAME, EFFECTIVE_TIME, CARDHOLDER, CARD_TYPE, CUSTOMER_CARD_NO, ",
               "MOBILE, CUSTOMER_ID, CREATE_TIME",
               "from bank_info where CUSTOMER_ID = #{customerId,jdbcType=INTEGER}"
       })
       @Results({
               @Result(column="BANK_ID", property="bankId", jdbcType=JdbcType.INTEGER, id=true),
               @Result(column="BANK_CARD_NUMBER", property="bankCardNumber", jdbcType=JdbcType.VARCHAR),
               @Result(column="BANK_NAME", property="bankName", jdbcType=JdbcType.VARCHAR),
               @Result(column="EFFECTIVE_TIME", property="effectiveTime", jdbcType=JdbcType.VARCHAR),
               @Result(column="CARDHOLDER", property="cardholder", jdbcType=JdbcType.VARCHAR),
               @Result(column="CARD_TYPE", property="cardType", jdbcType=JdbcType.VARCHAR),
               @Result(column="CUSTOMER_CARD_NO", property="customerCardNo", jdbcType=JdbcType.VARCHAR),
               @Result(column="MOBILE", property="mobile", jdbcType=JdbcType.VARCHAR),
               @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
               @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
       })
       List<BankInfo> selectAll(@Param("customerId")Integer customerId);

       @Update({
               "update bank_info",
               "set BANK_CARD_NUMBER = #{bankCardNumber,jdbcType=VARCHAR},",
               "BANK_NAME = #{bankName,jdbcType=VARCHAR},",
               "EFFECTIVE_TIME = #{effectiveTime,jdbcType=VARCHAR},",
               "CARDHOLDER = #{cardholder,jdbcType=VARCHAR},",
               "CARD_TYPE = #{cardType,jdbcType=VARCHAR},",
               "CUSTOMER_CARD_NO = #{customerCardNo,jdbcType=VARCHAR},",
               "MOBILE = #{mobile,jdbcType=VARCHAR},",
               "CUSTOMER_ID = #{customerId,jdbcType=INTEGER},",
               "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP}",
               "where BANK_ID = #{bankId,jdbcType=INTEGER}"
       })
       int updateByPrimaryKey(BankInfo record);
}
