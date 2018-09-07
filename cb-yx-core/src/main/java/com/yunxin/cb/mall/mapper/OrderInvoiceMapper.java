package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.OrderInvoice;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
@Mapper
public interface OrderInvoiceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_invoice
     *
     * @mbg.generated Wed Jul 18 10:25:27 CST 2018
     */
    @Delete({
        "delete from order_invoice",
        "where INVOICE_ID = #{invoiceId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer invoiceId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_invoice
     *
     * @mbg.generated Wed Jul 18 10:25:27 CST 2018
     */
    @Insert({
        "insert into order_invoice (INVOICE_ID, BANK_ACCOUNT, ",
        "BANK_NAME, CONTENT, ",
        "INVOICE_AMOUNT, INVOICE_CODE, ",
        "INVOICE_TITLE, INVOICE_TITLE_TYPE, ",
        "INVOICE_TYPE, REGISTER_ADDRESS, ",
        "REGISTER_PHONE, REMARK, ",
        "TAXPAYER_NO, ORDER_ID)",
        "values (#{invoiceId,jdbcType=INTEGER}, #{bankAccount,jdbcType=VARCHAR}, ",
        "#{bankName,jdbcType=VARCHAR}, #{content,jdbcType=VARCHAR}, ",
        "#{invoiceAmount,jdbcType=REAL}, #{invoiceCode,jdbcType=VARCHAR}, ",
        "#{invoiceTitle,jdbcType=VARCHAR}, #{invoiceTitleType,jdbcType=INTEGER}, ",
        "#{invoiceType,jdbcType=INTEGER}, #{registerAddress,jdbcType=VARCHAR}, ",
        "#{registerPhone,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, ",
        "#{taxpayerNo,jdbcType=VARCHAR}, #{orderId,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true, keyProperty="invoiceId", keyColumn="INVOICE_ID")
    int insert(OrderInvoice record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_invoice
     *
     * @mbg.generated Wed Jul 18 10:25:27 CST 2018
     */
    @Select({
        "select",
        "INVOICE_ID, BANK_ACCOUNT, BANK_NAME, CONTENT, INVOICE_AMOUNT, INVOICE_CODE, ",
        "INVOICE_TITLE, INVOICE_TITLE_TYPE, INVOICE_TYPE, REGISTER_ADDRESS, REGISTER_PHONE, ",
        "REMARK, TAXPAYER_NO, ORDER_ID",
        "from order_invoice",
        "where INVOICE_ID = #{invoiceId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="INVOICE_ID", property="invoiceId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="BANK_ACCOUNT", property="bankAccount", jdbcType=JdbcType.VARCHAR),
        @Result(column="BANK_NAME", property="bankName", jdbcType=JdbcType.VARCHAR),
        @Result(column="CONTENT", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="INVOICE_AMOUNT", property="invoiceAmount", jdbcType=JdbcType.REAL),
        @Result(column="INVOICE_CODE", property="invoiceCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="INVOICE_TITLE", property="invoiceTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="INVOICE_TITLE_TYPE", property="invoiceTitleType", jdbcType=JdbcType.INTEGER),
        @Result(column="INVOICE_TYPE", property="invoiceType", jdbcType=JdbcType.INTEGER),
        @Result(column="REGISTER_ADDRESS", property="registerAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="REGISTER_PHONE", property="registerPhone", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="TAXPAYER_NO", property="taxpayerNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORDER_ID", property="orderId", jdbcType=JdbcType.INTEGER)
    })
    OrderInvoice selectByPrimaryKey(Integer invoiceId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_invoice
     *
     * @mbg.generated Wed Jul 18 10:25:27 CST 2018
     */
    @Select({
        "select",
        "INVOICE_ID, BANK_ACCOUNT, BANK_NAME, CONTENT, INVOICE_AMOUNT, INVOICE_CODE, ",
        "INVOICE_TITLE, INVOICE_TITLE_TYPE, INVOICE_TYPE, REGISTER_ADDRESS, REGISTER_PHONE, ",
        "REMARK, TAXPAYER_NO, ORDER_ID",
        "from order_invoice"
    })
    @Results({
        @Result(column="INVOICE_ID", property="invoiceId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="BANK_ACCOUNT", property="bankAccount", jdbcType=JdbcType.VARCHAR),
        @Result(column="BANK_NAME", property="bankName", jdbcType=JdbcType.VARCHAR),
        @Result(column="CONTENT", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="INVOICE_AMOUNT", property="invoiceAmount", jdbcType=JdbcType.REAL),
        @Result(column="INVOICE_CODE", property="invoiceCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="INVOICE_TITLE", property="invoiceTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="INVOICE_TITLE_TYPE", property="invoiceTitleType", jdbcType=JdbcType.INTEGER),
        @Result(column="INVOICE_TYPE", property="invoiceType", jdbcType=JdbcType.INTEGER),
        @Result(column="REGISTER_ADDRESS", property="registerAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="REGISTER_PHONE", property="registerPhone", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="TAXPAYER_NO", property="taxpayerNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORDER_ID", property="orderId", jdbcType=JdbcType.INTEGER)
    })
    List<OrderInvoice> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_invoice
     *
     * @mbg.generated Wed Jul 18 10:25:27 CST 2018
     */
    @Update({
        "update order_invoice",
        "set BANK_ACCOUNT = #{bankAccount,jdbcType=VARCHAR},",
          "BANK_NAME = #{bankName,jdbcType=VARCHAR},",
          "CONTENT = #{content,jdbcType=VARCHAR},",
          "INVOICE_AMOUNT = #{invoiceAmount,jdbcType=REAL},",
          "INVOICE_CODE = #{invoiceCode,jdbcType=VARCHAR},",
          "INVOICE_TITLE = #{invoiceTitle,jdbcType=VARCHAR},",
          "INVOICE_TITLE_TYPE = #{invoiceTitleType,jdbcType=INTEGER},",
          "INVOICE_TYPE = #{invoiceType,jdbcType=INTEGER},",
          "REGISTER_ADDRESS = #{registerAddress,jdbcType=VARCHAR},",
          "REGISTER_PHONE = #{registerPhone,jdbcType=VARCHAR},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "TAXPAYER_NO = #{taxpayerNo,jdbcType=VARCHAR},",
          "ORDER_ID = #{orderId,jdbcType=INTEGER}",
        "where INVOICE_ID = #{invoiceId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(OrderInvoice record);
}