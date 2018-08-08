package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.ReimbursementQuery;
import com.yunxin.cb.util.page.Query;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface ReimbursementQueryMapper {
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
                    "WHERE a.ORDER_STATE = 7 \n" +
                    "and b.ITEM_ID not in (SELECT g.ORDER_ITEM_ID FROM rb_reimbursement h left join rb_reimbursement_order g on g.REIMBURSEMENT_ID = h.REIMBURSEMENT_ID WHERE h.ORDER_STATE != 3)\n"+
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
                    "WHERE a.ORDER_STATE = 7 \n"+
            "and b.ITEM_ID not in (SELECT g.ORDER_ITEM_ID FROM rb_reimbursement h left join rb_reimbursement_order g on g.REIMBURSEMENT_ID = h.REIMBURSEMENT_ID WHERE h.ORDER_STATE != 3)",
            "</script>"
    })
    long count(Query q);

}
