package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.ReimbursementOrder;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface ReimbursementOrderMapper {
    @Delete({
            "delete from rb_reimbursement_order",
            "where REIMBURSEMENT_ORDER_ID = #{reimbursementOrderId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer reimbursementOrderId);


    @Insert({
            "insert into rb_reimbursement_order (REIMBURSEMENT_ORDER_ID, ORDER_ITEM_ID, ",
            "PRODUCT_ID, AMOUNT, ",
            "TAX, PRODUCT_PRICE, ",
            "REIMBURSEMENT_ID, CREATE_TIME)",
            "values (#{reimbursementOrderId,jdbcType=INTEGER}, #{orderItemId,jdbcType=INTEGER}, ",
            "#{productId,jdbcType=INTEGER}, #{amount,jdbcType=DECIMAL}, ",
            "#{tax,jdbcType=DECIMAL}, #{productPrice,jdbcType=DECIMAL}, ",
            "#{reimbursementId,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP})"
    })
    int insert(ReimbursementOrder record);


    @Select({
            "select",
            "REIMBURSEMENT_ORDER_ID, ORDER_ITEM_ID, PRODUCT_ID, AMOUNT, TAX, PRODUCT_PRICE, ",
            "REIMBURSEMENT_ID, CREATE_TIME",
            "from rb_reimbursement_order",
            "where REIMBURSEMENT_ORDER_ID = #{reimbursementOrderId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="REIMBURSEMENT_ORDER_ID", property="reimbursementOrderId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="ORDER_ITEM_ID", property="orderItemId", jdbcType=JdbcType.INTEGER),
            @Result(column="PRODUCT_ID", property="productId", jdbcType=JdbcType.INTEGER),
            @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
            @Result(column="TAX", property="tax", jdbcType=JdbcType.DECIMAL),
            @Result(column="PRODUCT_PRICE", property="productPrice", jdbcType=JdbcType.DECIMAL),
            @Result(column="REIMBURSEMENT_ID", property="reimbursementId", jdbcType=JdbcType.INTEGER),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    ReimbursementOrder selectByPrimaryKey(Integer reimbursementOrderId);


    @Select({
            "select",
            "REIMBURSEMENT_ORDER_ID, ORDER_ITEM_ID, PRODUCT_ID, AMOUNT, TAX, PRODUCT_PRICE, ",
            "REIMBURSEMENT_ID, CREATE_TIME",
            "from rb_reimbursement_order"
    })
    @Results({
            @Result(column="REIMBURSEMENT_ORDER_ID", property="reimbursementOrderId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="ORDER_ITEM_ID", property="orderItemId", jdbcType=JdbcType.INTEGER),
            @Result(column="PRODUCT_ID", property="productId", jdbcType=JdbcType.INTEGER),
            @Result(column="AMOUNT", property="amount", jdbcType=JdbcType.DECIMAL),
            @Result(column="TAX", property="tax", jdbcType=JdbcType.DECIMAL),
            @Result(column="PRODUCT_PRICE", property="productPrice", jdbcType=JdbcType.DECIMAL),
            @Result(column="REIMBURSEMENT_ID", property="reimbursementId", jdbcType=JdbcType.INTEGER),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ReimbursementOrder> selectAll();


    @Update({
            "update rb_reimbursement_order",
            "set ORDER_ITEM_ID = #{orderItemId,jdbcType=INTEGER},",
            "PRODUCT_ID = #{productId,jdbcType=INTEGER},",
            "AMOUNT = #{amount,jdbcType=DECIMAL},",
            "TAX = #{tax,jdbcType=DECIMAL},",
            "PRODUCT_PRICE = #{productPrice,jdbcType=DECIMAL},",
            "REIMBURSEMENT_ID = #{reimbursementId,jdbcType=INTEGER},",
            "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP}",
            "where REIMBURSEMENT_ORDER_ID = #{reimbursementOrderId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ReimbursementOrder record);
}
