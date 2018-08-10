package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.ReimbursementQuery;
import com.yunxin.cb.mall.vo.ReimbursementProductVO;
import com.yunxin.cb.util.page.Query;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface ReimbursementQueryMapper {
    /**
     * 分页查询可报账商品列表
     * @param q
     * @return
     */
    @Select({
            "<script>" ,
            "SELECT \n" +
                    "b.ORDER_ID,\n" +
                    "b.PRODUCT_ID,\n" +
                    "(b.PRODUCT_NUM*b.SALE_PRICE) as accountSalePrice,\n" +
                    "b.PRODUCT_IMG,\n" +
                    "b.PRODUCT_NUM,\n" +
                    "b.SALE_PRICE,\n" +
                    "a.CREATE_TIME,\n" +
                    "k.SELLER_ADDRESS,\n" +
                    "c.DEFAULT_PIC_PATH,\n" +
                    "c.COMMODITY_ID,\n" +
                    "d.CATEGORY_ID,\n" +
                    "c.PRODUCT_NAME\n" +
                    "FROM  order_item b \n" +
                    "left join order_form a on a.ORDER_ID = b.ORDER_ID \n" +
                    "left join seller k on k.SELLER_ID = a.SELLER_ID\n" +
                    "left join product c on b.PRODUCT_ID = c.PRODUCT_ID \n" +
                    "left join commodity_category d on c.COMMODITY_ID = d.COMMODITY_ID \n" +
                    "WHERE a.ORDER_STATE = #{data.orderState,jdbcType=INTEGER} and a.CUSTOMER_ID = #{data.customerId,jdbcType=INTEGER}\n" +
                    "and b.ITEM_ID not in (SELECT g.ORDER_ITEM_ID FROM rb_reimbursement h left join rb_reimbursement_order g on g.REIMBURSEMENT_ID = h.REIMBURSEMENT_ID " +
                    "WHERE h.ORDER_STATE != #{data.reimbursement_state,jdbcType=INTEGER} and h.ORDER_STATE !=#{data.reimbursementState,jdbcType=INTEGER})\n"+
                    "order by a.CREATE_TIME desc",
                    "LIMIT #{rowIndex},#{pageSize}",
                    "</script>"
    })
    @Results(id = "reimbursementQueryMap", value = {
        @Result(column="ORDER_ID", property="orderId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="PRODUCT_ID", property="productId", jdbcType=JdbcType.INTEGER),
        @Result(column="PRODUCT_IMG", property="productImg", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="PRODUCT_NUM", property="productNum", jdbcType=JdbcType.INTEGER),
        @Result(column="SALE_PRICE", property="salePrice", jdbcType=JdbcType.FLOAT),
        @Result(column="SELLER_ADDRESS", property="sellerAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEFAULT_PIC_PATH", property="defaultPicPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER),
        @Result(column="CATEGORY_ID", property="categoryId", jdbcType=JdbcType.INTEGER),
        @Result(column="ORDER_STATE", property="orderState", jdbcType=JdbcType.INTEGER),
        @Result(column="PRODUCT_NAME", property="productName", jdbcType=JdbcType.VARCHAR)
    })

    List<ReimbursementQuery> selectReimbursementQuery(Query q);

    /**
     * 统计能报销的商品总数
     */
    @Select({
            "<script>" ,
            "SELECT \n" +
                    "count(a.ORDER_ID)\n" +
                    "FROM  order_item b \n" +
                    "left join order_form a on a.ORDER_ID = b.ORDER_ID \n" +
                    "left join product c on b.PRODUCT_ID = c.PRODUCT_ID \n" +
                    "left join commodity_category d on c.COMMODITY_ID = d.COMMODITY_ID \n" +
                    "WHERE a.ORDER_STATE = #{data.orderState,jdbcType=INTEGER} and a.CUSTOMER_ID = #{data.customerId,jdbcType=INTEGER}\n"+
                    "and b.ITEM_ID not in (SELECT g.ORDER_ITEM_ID FROM rb_reimbursement h left join rb_reimbursement_order g on g.REIMBURSEMENT_ID = h.REIMBURSEMENT_ID " +
                    "WHERE h.ORDER_STATE != #{data.reimbursement_state,jdbcType=INTEGER} and h.ORDER_STATE !=#{data.reimbursementState,jdbcType=INTEGER})",
            "</script>"
    })
    long count(Query q);

    /**
     * 分页查询已报账订单列表
     * @param reimbursementId
     * @return
     */
    @Select({
            "SELECT  \n" +
                    "c.PRODUCT_NAME,\n" +
                    "c.DEFAULT_PIC_PATH,\n" +
                    "d.PRODUCT_NUM,\n" +
                    "d.SALE_PRICE,\n" +
                    "g.SELLER_ADDRESS\n" +
                    "FROM rb_reimbursement_order b\n" +
                    "left join product c on c.PRODUCT_ID = b.PRODUCT_ID\n" +
                    "left join ORDER_ITEM d on d.ITEM_ID = b.ORDER_ITEM_ID\n" +
                    "left join order_form f on d.ORDER_ID = f.ORDER_ID\n" +
                    "left join seller g on f.SELLER_ID = g.SELLER_ID\n" +
                    "WHERE b.REIMBURSEMENT_ID = #{reimbursementId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="REIMBURSEMENT_ID", property="reimbursementId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="PRODUCT_NUM", property="productNum", jdbcType=JdbcType.INTEGER),
            @Result(column="SALE_PRICE", property="salePrice", jdbcType=JdbcType.FLOAT),
            @Result(column="SELLER_ADDRESS", property="sellerAddress", jdbcType=JdbcType.VARCHAR),
            @Result(column="DEFAULT_PIC_PATH", property="defaultPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="PRODUCT_NAME", property="productName", jdbcType=JdbcType.VARCHAR),
    })
    List<ReimbursementProductVO> selectAlreadyReimbursementQuery(Integer reimbursementId);

    /**
     * 统计报账订单总数
     */
    @Select({
            "<script>" ,
            "  SELECT  \n" +
                    "count(a.REIMBURSEMENT_ID)\n" +
                    "FROM `rb_reimbursement` a \n" +
                    "where 1=1",
                    "<if test='data.customerId!=null'>",
                    "and CUSTOMER_ID = #{data.customerId}",
                    "</if>",
                    "<if test='data.orderState!=null'>",
                    "and ORDER_STATE = #{data.orderState}",
                    "</if>",
            "</script>"
    })
    long alreadyReimbursementCount(Query q);

}
