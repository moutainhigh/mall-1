package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.util.page.Query;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Set;

@Mapper
public interface OrderMapper {

    final static String columns = "ORDER_ID, BARTER_STATE, BUYER_MESSAGE, CANCEL_REASON, CANCEL_TIME, CITY, CONSIGNEE_ADDRESS," +
            " CONSIGNEE_MOBILE, CONSIGNEE_NAME, CONSIGNEE_TELEPHONE, COUPONS_FEE, COURIER_NUMBER," +
            " CREATE_TIME, DELIVERY, DELIVERY_FEE_TOTAL, DELIVERY_STATE, DELIVERY_TYPE, DISCOUNT_DELIVERY_FEE_TOTAL," +
            " DISCOUNT_TOTAL, DISTRICT, ENABLED, FEE_TOTAL, FINISH_TIME, ORDER_CODE, ORDER_STATE," +
            " ORIGIN_ORDER_CODE, PAY_BY_INTEGRAL, PAYMENT_SEQUENCE, PAYMENT_TIME, PAYMENT_TYPE," +
            " POST_CODE, PROD_QUANTITY, PROVINCE, REMARK, RETURN_REFUND_STATE, SCORE_TOTAL," +
            " TOTAL_PRICE, UPDATE_TIME, USED_SCORE, VOLUME_TOTAL, WEIGHT_TOTAL, CUSTOMER_ID,"  +
            " LOGISTIC_ID, SELLER_ID";

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_form
     *
     * @mbg.generated Wed Jul 18 10:16:09 CST 2018
     */
    @Delete({
        "delete from order_form",
        "where ORDER_ID = #{orderId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer orderId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_form
     *
     * @mbg.generated Wed Jul 18 10:16:09 CST 2018
     */
    @Insert({
        "insert into order_form (",
            columns, ")",
        "values (#{orderId,jdbcType=INTEGER}, #{barterState,jdbcType=INTEGER}, ",
        "#{buyerMessage,jdbcType=VARCHAR}, #{cancelReason,jdbcType=VARCHAR}, ",
        "#{cancelTime,jdbcType=TIMESTAMP}, #{city,jdbcType=VARCHAR}, ",
        "#{consigneeAddress,jdbcType=VARCHAR}, #{consigneeMobile,jdbcType=VARCHAR}, ",
        "#{consigneeName,jdbcType=VARCHAR}, #{consigneeTelephone,jdbcType=VARCHAR}, ",
        "#{couponsFee,jdbcType=DOUBLE}, #{courierNumber,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{delivery,jdbcType=BIT}, ",
        "#{deliveryFeeTotal,jdbcType=DOUBLE}, #{deliveryState,jdbcType=INTEGER}, ",
        "#{deliveryType,jdbcType=INTEGER}, #{discountDeliveryFeeTotal,jdbcType=DOUBLE}, ",
        "#{discountTotal,jdbcType=DOUBLE}, #{district,jdbcType=VARCHAR}, ",
        "#{enabled,jdbcType=BIT}, #{feeTotal,jdbcType=DOUBLE}, #{finishTime,jdbcType=TIMESTAMP}, ",
        "#{orderCode,jdbcType=VARCHAR}, #{orderState,jdbcType=INTEGER}, ",
        "#{originOrderCode,jdbcType=VARCHAR}, #{payByIntegral,jdbcType=DOUBLE}, ",
        "#{paymentSequence,jdbcType=VARCHAR}, #{paymentTime,jdbcType=TIMESTAMP}, ",
        "#{paymentType,jdbcType=INTEGER}, #{postCode,jdbcType=VARCHAR}, ",
        "#{prodQuantity,jdbcType=INTEGER}, #{province,jdbcType=VARCHAR}, ",
        "#{remark,jdbcType=VARCHAR}, #{returnRefundState,jdbcType=INTEGER}, ",
        "#{scoreTotal,jdbcType=INTEGER}, #{totalPrice,jdbcType=DOUBLE}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{usedScore,jdbcType=INTEGER}, ",
        "#{volumeTotal,jdbcType=DOUBLE}, #{weightTotal,jdbcType=DOUBLE}, ",
        "#{customerId,jdbcType=INTEGER}, #{logisticId,jdbcType=INTEGER}, ",
        "#{sellerId,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true, keyProperty="orderId", keyColumn="ORDER_ID")
    int insert(Order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_form
     *
     * @mbg.generated Wed Jul 18 10:16:09 CST 2018
     */
    @Select({
        "select",
            columns,
        "from order_form",
        "where ORDER_ID = #{orderId,jdbcType=INTEGER}"
    })
    @Results(id = "orderDetailMap", value = {
        @Result(column="ORDER_ID", property="orderId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="BARTER_STATE", property="barterState", jdbcType=JdbcType.INTEGER),
        @Result(column="BUYER_MESSAGE", property="buyerMessage", jdbcType=JdbcType.VARCHAR),
        @Result(column="CANCEL_REASON", property="cancelReason", jdbcType=JdbcType.VARCHAR),
        @Result(column="CANCEL_TIME", property="cancelTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CITY", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="CONSIGNEE_ADDRESS", property="consigneeAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="CONSIGNEE_MOBILE", property="consigneeMobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="CONSIGNEE_NAME", property="consigneeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="CONSIGNEE_TELEPHONE", property="consigneeTelephone", jdbcType=JdbcType.VARCHAR),
        @Result(column="COUPONS_FEE", property="couponsFee", jdbcType=JdbcType.DOUBLE),
        @Result(column="COURIER_NUMBER", property="courierNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="DELIVERY", property="delivery", jdbcType=JdbcType.BIT),
        @Result(column="DELIVERY_FEE_TOTAL", property="deliveryFeeTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="DELIVERY_STATE", property="deliveryState", jdbcType=JdbcType.INTEGER),
        @Result(column="DELIVERY_TYPE", property="deliveryType", jdbcType=JdbcType.INTEGER),
        @Result(column="DISCOUNT_DELIVERY_FEE_TOTAL", property="discountDeliveryFeeTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="DISCOUNT_TOTAL", property="discountTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="DISTRICT", property="district", jdbcType=JdbcType.VARCHAR),
        @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
        @Result(column="FEE_TOTAL", property="feeTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="FINISH_TIME", property="finishTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ORDER_CODE", property="orderCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORDER_STATE", property="orderState", jdbcType=JdbcType.INTEGER),
        @Result(column="ORIGIN_ORDER_CODE", property="originOrderCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="PAY_BY_INTEGRAL", property="payByIntegral", jdbcType=JdbcType.DOUBLE),
        @Result(column="PAYMENT_SEQUENCE", property="paymentSequence", jdbcType=JdbcType.VARCHAR),
        @Result(column="PAYMENT_TIME", property="paymentTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="PAYMENT_TYPE", property="paymentType", jdbcType=JdbcType.INTEGER),
        @Result(column="POST_CODE", property="postCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="PROD_QUANTITY", property="prodQuantity", jdbcType=JdbcType.INTEGER),
        @Result(column="PROVINCE", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="RETURN_REFUND_STATE", property="returnRefundState", jdbcType=JdbcType.INTEGER),
        @Result(column="SCORE_TOTAL", property="scoreTotal", jdbcType=JdbcType.INTEGER),
        @Result(column="TOTAL_PRICE", property="totalPrice", jdbcType=JdbcType.DOUBLE),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="USED_SCORE", property="usedScore", jdbcType=JdbcType.INTEGER),
        @Result(column="VOLUME_TOTAL", property="volumeTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="WEIGHT_TOTAL", property="weightTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="LOGISTIC_ID", property="logisticId", jdbcType=JdbcType.INTEGER),
        @Result(column="SELLER_ID", property="sellerId", jdbcType=JdbcType.INTEGER),
        @Result(column="ORDER_ID", property="orderItems", javaType=Set.class,
            many=@Many(select="com.yunxin.cb.mall.mapper.OrderItemMapper.selectByOrderId"))
    })
    Order selectByPrimaryKey(Integer orderId);

    @Select({
            "select",
                columns,
            "from order_form",
            "where ORDER_ID = #{orderId,jdbcType=INTEGER}",
            "and CUSTOMER_ID = #{customerId,jdbcType=INTEGER}"
    })
    @ResultMap(value="orderDetailMap")
    Order selectByOrderIdAndCustomerId(@Param("orderId") Integer orderId, @Param("customerId")Integer customerId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_form
     *
     * @mbg.generated Wed Jul 18 10:16:09 CST 2018
     */
    @Select({
        "select",
            columns,
        "from order_form"
    })
    @Results(id = "orderMap", value = {
        @Result(column="ORDER_ID", property="orderId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="BARTER_STATE", property="barterState", jdbcType=JdbcType.INTEGER),
        @Result(column="BUYER_MESSAGE", property="buyerMessage", jdbcType=JdbcType.VARCHAR),
        @Result(column="CANCEL_REASON", property="cancelReason", jdbcType=JdbcType.VARCHAR),
        @Result(column="CANCEL_TIME", property="cancelTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CITY", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="CONSIGNEE_ADDRESS", property="consigneeAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="CONSIGNEE_MOBILE", property="consigneeMobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="CONSIGNEE_NAME", property="consigneeName", jdbcType=JdbcType.VARCHAR),
        @Result(column="CONSIGNEE_TELEPHONE", property="consigneeTelephone", jdbcType=JdbcType.VARCHAR),
        @Result(column="COUPONS_FEE", property="couponsFee", jdbcType=JdbcType.DOUBLE),
        @Result(column="COURIER_NUMBER", property="courierNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="DELIVERY", property="delivery", jdbcType=JdbcType.BIT),
        @Result(column="DELIVERY_FEE_TOTAL", property="deliveryFeeTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="DELIVERY_STATE", property="deliveryState", jdbcType=JdbcType.INTEGER),
        @Result(column="DELIVERY_TYPE", property="deliveryType", jdbcType=JdbcType.INTEGER),
        @Result(column="DISCOUNT_DELIVERY_FEE_TOTAL", property="discountDeliveryFeeTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="DISCOUNT_TOTAL", property="discountTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="DISTRICT", property="district", jdbcType=JdbcType.VARCHAR),
        @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
        @Result(column="FEE_TOTAL", property="feeTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="FINISH_TIME", property="finishTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ORDER_CODE", property="orderCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORDER_STATE", property="orderState", jdbcType=JdbcType.INTEGER),
        @Result(column="ORIGIN_ORDER_CODE", property="originOrderCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="PAY_BY_INTEGRAL", property="payByIntegral", jdbcType=JdbcType.DOUBLE),
        @Result(column="PAYMENT_SEQUENCE", property="paymentSequence", jdbcType=JdbcType.VARCHAR),
        @Result(column="PAYMENT_TIME", property="paymentTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="PAYMENT_TYPE", property="paymentType", jdbcType=JdbcType.INTEGER),
        @Result(column="POST_CODE", property="postCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="PROD_QUANTITY", property="prodQuantity", jdbcType=JdbcType.INTEGER),
        @Result(column="PROVINCE", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="RETURN_REFUND_STATE", property="returnRefundState", jdbcType=JdbcType.INTEGER),
        @Result(column="SCORE_TOTAL", property="scoreTotal", jdbcType=JdbcType.INTEGER),
        @Result(column="TOTAL_PRICE", property="totalPrice", jdbcType=JdbcType.DOUBLE),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="USED_SCORE", property="usedScore", jdbcType=JdbcType.INTEGER),
        @Result(column="VOLUME_TOTAL", property="volumeTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="WEIGHT_TOTAL", property="weightTotal", jdbcType=JdbcType.DOUBLE),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
        @Result(column="LOGISTIC_ID", property="logisticId", jdbcType=JdbcType.INTEGER),
        @Result(column="SELLER_ID", property="sellerId", jdbcType=JdbcType.INTEGER)
    })
    List<Order> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_form
     *
     * @mbg.generated Wed Jul 18 10:16:09 CST 2018
     */
    @Update({
        "update order_form",
        "set BARTER_STATE = #{barterState,jdbcType=INTEGER},",
          "BUYER_MESSAGE = #{buyerMessage,jdbcType=VARCHAR},",
          "CANCEL_REASON = #{cancelReason,jdbcType=VARCHAR},",
          "CANCEL_TIME = #{cancelTime,jdbcType=TIMESTAMP},",
          "CITY = #{city,jdbcType=VARCHAR},",
          "CONSIGNEE_ADDRESS = #{consigneeAddress,jdbcType=VARCHAR},",
          "CONSIGNEE_MOBILE = #{consigneeMobile,jdbcType=VARCHAR},",
          "CONSIGNEE_NAME = #{consigneeName,jdbcType=VARCHAR},",
          "CONSIGNEE_TELEPHONE = #{consigneeTelephone,jdbcType=VARCHAR},",
          "COUPONS_FEE = #{couponsFee,jdbcType=DOUBLE},",
          "COURIER_NUMBER = #{courierNumber,jdbcType=VARCHAR},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "DELIVERY = #{delivery,jdbcType=BIT},",
          "DELIVERY_FEE_TOTAL = #{deliveryFeeTotal,jdbcType=DOUBLE},",
          "DELIVERY_STATE = #{deliveryState,jdbcType=INTEGER},",
          "DELIVERY_TYPE = #{deliveryType,jdbcType=INTEGER},",
          "DISCOUNT_DELIVERY_FEE_TOTAL = #{discountDeliveryFeeTotal,jdbcType=DOUBLE},",
          "DISCOUNT_TOTAL = #{discountTotal,jdbcType=DOUBLE},",
          "DISTRICT = #{district,jdbcType=VARCHAR},",
          "ENABLED = #{enabled,jdbcType=BIT},",
          "FEE_TOTAL = #{feeTotal,jdbcType=DOUBLE},",
          "FINISH_TIME = #{finishTime,jdbcType=TIMESTAMP},",
          "ORDER_CODE = #{orderCode,jdbcType=VARCHAR},",
          "ORDER_STATE = #{orderState,jdbcType=INTEGER},",
          "ORIGIN_ORDER_CODE = #{originOrderCode,jdbcType=VARCHAR},",
          "PAY_BY_INTEGRAL = #{payByIntegral,jdbcType=DOUBLE},",
          "PAYMENT_SEQUENCE = #{paymentSequence,jdbcType=VARCHAR},",
          "PAYMENT_TIME = #{paymentTime,jdbcType=TIMESTAMP},",
          "PAYMENT_TYPE = #{paymentType,jdbcType=INTEGER},",
          "POST_CODE = #{postCode,jdbcType=VARCHAR},",
          "PROD_QUANTITY = #{prodQuantity,jdbcType=INTEGER},",
          "PROVINCE = #{province,jdbcType=VARCHAR},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "RETURN_REFUND_STATE = #{returnRefundState,jdbcType=INTEGER},",
          "SCORE_TOTAL = #{scoreTotal,jdbcType=INTEGER},",
          "TOTAL_PRICE = #{totalPrice,jdbcType=DOUBLE},",
          "UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP},",
          "USED_SCORE = #{usedScore,jdbcType=INTEGER},",
          "VOLUME_TOTAL = #{volumeTotal,jdbcType=DOUBLE},",
          "WEIGHT_TOTAL = #{weightTotal,jdbcType=DOUBLE},",
          "CUSTOMER_ID = #{customerId,jdbcType=INTEGER},",
          "LOGISTIC_ID = #{logisticId,jdbcType=INTEGER},",
          "SELLER_ID = #{sellerId,jdbcType=INTEGER}",
        "where ORDER_ID = #{orderId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Order record);

    @Select({
            "<script>",
            "select",
                columns,
            "from order_form",
            "where 1=1",
            "<if test='data.customerId!=null'>",
            "and CUSTOMER_ID = #{data.customerId}",
            "</if>",
            "<if test='data.orderState!=null'>",
            "and ORDER_STATE = #{data.orderState}",
            "</if>",
            "ORDER BY CREATE_TIME DESC",
            "LIMIT #{rowIndex},#{pageSize}",
            "</script>"
    })
    @ResultMap(value="orderMap")
    List<Order> pageList(Query q);

    @Select({
            "<script>",
            "select",
            "count(ORDER_ID)",
            "from order_form",
            "where 1=1",
            "<if test='data.customerId!=null'>",
                 "and CUSTOMER_ID = #{data.customerId}",
            "</if>",
            "<if test='data.orderState!=null'>",
                "and ORDER_STATE = #{data.orderState}",
            "</if>",
            "ORDER BY CREATE_TIME DESC",
            "LIMIT #{rowIndex},#{pageSize}",
            "</script>"
    })
    long count(Query q);
}