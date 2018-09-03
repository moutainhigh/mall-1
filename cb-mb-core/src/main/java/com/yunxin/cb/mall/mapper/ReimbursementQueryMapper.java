package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.ReimbursementQuery;
import com.yunxin.cb.mall.vo.ReimbursementProductVO;
import com.yunxin.cb.util.page.Query;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface ReimbursementQueryMapper {

    @Insert({
            "insert into rb_reimbursement_query (REIMBURSEMENT_QUERY_ID, ITEM_ID, ",
            "PRODUCT_ID, SALE_PRICE, ",
            "PRODUCT_NUM, CREATE_TIME, ",
            "SELLER_NAME, COMMODITY_NAME,DEFAULT_PIC_PATH, ",
            "COMMODITY_ID, PRODUCT_NAME, ",
            "CUSTOMER_ID, REIMBURSEMENT_QUERY_STATE)",
            "values (#{reimbursementQueryId,jdbcType=INTEGER}, #{itemId,jdbcType=INTEGER}, ",
            "#{productId,jdbcType=INTEGER}, #{salePrice,jdbcType=REAL}, ",
            "#{productNum,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
            "#{sellerName,jdbcType=VARCHAR},#{commodityName,jdbcType=VARCHAR}, #{defaultPicPath,jdbcType=VARCHAR}, ",
            "#{commodityId,jdbcType=INTEGER}, #{productName,jdbcType=VARCHAR}, ",
            "#{customerId,jdbcType=INTEGER}, #{reimbursementQueryState,jdbcType=INTEGER})"
    })
    int insert(ReimbursementQuery record);

    @Select({
            "select",
            "REIMBURSEMENT_QUERY_ID, ITEM_ID, PRODUCT_ID, SALE_PRICE, PRODUCT_NUM, CREATE_TIME, ",
            "SELLER_NAME, COMMODITY_NAME,DEFAULT_PIC_PATH, COMMODITY_ID, PRODUCT_NAME, CUSTOMER_ID, REIMBURSEMENT_QUERY_STATE",
            "from rb_reimbursement_query",
            "where REIMBURSEMENT_QUERY_ID = #{reimbursementQueryId,jdbcType=INTEGER} and CUSTOMER_ID = #{customerId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="REIMBURSEMENT_QUERY_ID", property="reimbursementQueryId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="ITEM_ID", property="itemId", jdbcType=JdbcType.INTEGER),
            @Result(column="PRODUCT_ID", property="productId", jdbcType=JdbcType.INTEGER),
            @Result(column="SALE_PRICE", property="salePrice", jdbcType=JdbcType.REAL),
            @Result(column="PRODUCT_NUM", property="productNum", jdbcType=JdbcType.INTEGER),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="SELLER_NAME", property="sellerName", jdbcType=JdbcType.VARCHAR),
            @Result(column="COMMODITY_NAME", property="commodityName", jdbcType=JdbcType.VARCHAR),
            @Result(column="DEFAULT_PIC_PATH", property="defaultPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER),
            @Result(column="PRODUCT_NAME", property="productName", jdbcType=JdbcType.VARCHAR),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
            @Result(column="REIMBURSEMENT_QUERY_STATE", property="reimbursementQueryState", jdbcType=JdbcType.INTEGER)
    })
    ReimbursementQuery selectByPrimaryKeyAndCustomerId(@Param("reimbursementQueryId")Integer reimbursementQueryId,@Param("customerId")Integer customerId);

    @Update({
            "update rb_reimbursement_query",
            "set ITEM_ID = #{itemId,jdbcType=INTEGER},",
            "PRODUCT_ID = #{productId,jdbcType=INTEGER},",
            "SALE_PRICE = #{salePrice,jdbcType=REAL},",
            "PRODUCT_NUM = #{productNum,jdbcType=INTEGER},",
            "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
            "SELLER_NAME = #{sellerName,jdbcType=VARCHAR},",
            "DEFAULT_PIC_PATH = #{defaultPicPath,jdbcType=VARCHAR},",
            "COMMODITY_ID = #{commodityId,jdbcType=INTEGER},",
            "PRODUCT_NAME = #{productName,jdbcType=VARCHAR},",
            "CUSTOMER_ID = #{customerId,jdbcType=INTEGER},",
            "REIMBURSEMENT_QUERY_STATE = #{reimbursementQueryState,jdbcType=INTEGER}",
            "where REIMBURSEMENT_QUERY_ID = #{reimbursementQueryId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ReimbursementQuery record);

    /**
     * 分页查询可报账商品列表
     * @param q
     * @return
     */
    @Select({
            "<script>" ,
            "SELECT \n" +
                    "REIMBURSEMENT_QUERY_ID,\n" +
                    "(PRODUCT_NUM*SALE_PRICE) as accountSalePrice,\n" +
                    "ITEM_ID,\n" +
                    "PRODUCT_ID,\n" +
                    "PRODUCT_NUM,\n" +
                    "SALE_PRICE,\n" +
                    "CREATE_TIME,\n" +
                    "SELLER_NAME,\n" +
                    "COMMODITY_NAME,\n" +
                    "DEFAULT_PIC_PATH,\n" +
                    "COMMODITY_ID,\n" +
                    "PRODUCT_NAME,\n" +
                    "REIMBURSEMENT_QUERY_STATE\n" +
                    "FROM  rb_reimbursement_query \n" +
                    "WHERE REIMBURSEMENT_QUERY_STATE = #{data.reimbursementQueryState,jdbcType=INTEGER} and CUSTOMER_ID = #{data.customerId,jdbcType=INTEGER}\n" +
                    "order by CREATE_TIME desc",
                    "LIMIT #{rowIndex},#{pageSize}",
                    "</script>"
    })
    @Results({
            @Result(column="REIMBURSEMENT_QUERY_ID", property="reimbursementQueryId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="ITEM_ID", property="itemId", jdbcType=JdbcType.INTEGER),
            @Result(column="PRODUCT_ID", property="productId", jdbcType=JdbcType.INTEGER),
            @Result(column="SALE_PRICE", property="salePrice", jdbcType=JdbcType.REAL),
            @Result(column="PRODUCT_NUM", property="productNum", jdbcType=JdbcType.INTEGER),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="SELLER_NAME", property="sellerName", jdbcType=JdbcType.VARCHAR),
            @Result(column="COMMODITY_NAME", property="commodityName", jdbcType=JdbcType.VARCHAR),
            @Result(column="DEFAULT_PIC_PATH", property="defaultPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER),
            @Result(column="PRODUCT_NAME", property="productName", jdbcType=JdbcType.VARCHAR),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
            @Result(column="REIMBURSEMENT_QUERY_STATE", property="reimbursementQueryState", jdbcType=JdbcType.INTEGER)
    })
    List<ReimbursementQuery> selectReimbursementQuery(Query q);

    /**
     * 统计能报销的商品总数
     */
    @Select({
            "<script>" ,
            "SELECT \n" +
                    "count(REIMBURSEMENT_QUERY_ID)\n" +
                    "FROM  rb_reimbursement_query \n" +
                    "WHERE REIMBURSEMENT_QUERY_STATE = #{data.reimbursementQueryState,jdbcType=INTEGER} and CUSTOMER_ID = #{data.customerId,jdbcType=INTEGER}",
            "</script>"
    })
    long count(Query q);


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
                    "WHERE a.ORDER_STATE = #{data.orderState,jdbcType=INTEGER} and a.CUSTOMER_ID = #{data.customerId,jdbcType=INTEGER}",
            "</script>"
    })
    long countNum(Query q);

    /**
     * 分页查询已报账订单列表
     * @param reimbursementId
     * @return
     */
    @Select({
        "SELECT c.SELLER_NAME\n" +
                ",c.SALE_PRICE,c.PRODUCT_NUM,c.PRODUCT_NAME,c.COMMODITY_NAME,c.DEFAULT_PIC_PATH\n" +
                "FROM rb_reimbursement a \n" +
                "left join rb_reimbursement_order b on a.REIMBURSEMENT_ID = b.REIMBURSEMENT_ID \n" +
                "left join rb_reimbursement_query c on b.REIMBURSEMENT_QUERY_ID = c.REIMBURSEMENT_QUERY_ID\n"+
                "WHERE a.REIMBURSEMENT_ID = #{reimbursementId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="PRODUCT_NUM", property="productNum", jdbcType=JdbcType.INTEGER),
            @Result(column="SALE_PRICE", property="salePrice", jdbcType=JdbcType.FLOAT),
            @Result(column="SELLER_NAME", property="sellerName", jdbcType=JdbcType.VARCHAR),
            @Result(column="COMMODITY_NAME", property="commodityName", jdbcType=JdbcType.VARCHAR),
            @Result(column="DEFAULT_PIC_PATH", property="defaultPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="PRODUCT_NAME", property="productName", jdbcType=JdbcType.VARCHAR),
    })
    List<ReimbursementProductVO> selectAlreadyReimbursementQuery(Integer reimbursementId);

    @Select({
            "select",
            "REIMBURSEMENT_QUERY_ID, ITEM_ID, PRODUCT_ID, SALE_PRICE, PRODUCT_NUM, CREATE_TIME, ",
            "SELLER_NAME, COMMODITY_NAME,DEFAULT_PIC_PATH, COMMODITY_ID, PRODUCT_NAME, CUSTOMER_ID, REIMBURSEMENT_QUERY_STATE",
            "from rb_reimbursement_query",
            "where REIMBURSEMENT_QUERY_ID = #{reimbursementQueryId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="REIMBURSEMENT_QUERY_ID", property="reimbursementQueryId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="ITEM_ID", property="itemId", jdbcType=JdbcType.INTEGER),
            @Result(column="PRODUCT_ID", property="productId", jdbcType=JdbcType.INTEGER),
            @Result(column="SALE_PRICE", property="salePrice", jdbcType=JdbcType.REAL),
            @Result(column="PRODUCT_NUM", property="productNum", jdbcType=JdbcType.INTEGER),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="SELLER_NAME", property="sellerName", jdbcType=JdbcType.VARCHAR),
            @Result(column="COMMODITY_NAME", property="commodityName", jdbcType=JdbcType.VARCHAR),
            @Result(column="DEFAULT_PIC_PATH", property="defaultPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER),
            @Result(column="PRODUCT_NAME", property="productName", jdbcType=JdbcType.VARCHAR),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
            @Result(column="REIMBURSEMENT_QUERY_STATE", property="reimbursementQueryState", jdbcType=JdbcType.INTEGER)
    })
    ReimbursementQuery selectByPrimaryKey(Integer reimbursementQueryId);

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
                    "c.PRODUCT_NAME\n" +
                    "FROM  order_item b \n" +
                    "left join order_form a on a.ORDER_ID = b.ORDER_ID \n" +
                    "left join seller k on k.SELLER_ID = a.SELLER_ID\n" +
                    "left join product c on b.PRODUCT_ID = c.PRODUCT_ID \n" +
                    /*                    "left join commodity_category d on c.COMMODITY_ID = d.COMMODITY_ID \n" +*/
                    "WHERE a.ORDER_STATE = #{data.orderState,jdbcType=INTEGER} and a.CUSTOMER_ID = #{data.customerId,jdbcType=INTEGER}\n" +
                    "order by a.CREATE_TIME desc",
                    "LIMIT #{rowIndex},#{pageSize}",
            "</script>"
    })
    @Results({
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

    List<ReimbursementQuery> selectPageReimbursementQuery(Query q);

}
