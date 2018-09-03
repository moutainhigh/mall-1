package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.HistoryRecord;
import com.yunxin.cb.util.page.Query;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface HistoryRecordMapper {
    @Insert({
        "insert into history_record (RECORD_ID, CREATE_TIME, ",
        "SALE_PRICE, COMMODITY_ID, PRODUCT_ID,",
        "CUSTOMER_ID)",
        "values (#{recordId,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{salePrice,jdbcType=REAL}, #{commodityId,jdbcType=INTEGER}, #{productId,jdbcType=INTEGER}, ",
        "#{customerId,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true, keyProperty="recordId", keyColumn="RECORD_ID")
    int insert(HistoryRecord record);

    @Select({"select RECORD_ID, CREATE_TIME, SALE_PRICE, COMMODITY_ID, PRODUCT_ID, CUSTOMER_ID from history_record where 1=1 ",
            "AND CUSTOMER_ID = #{customerId} AND PRODUCT_ID = #{productId} "
    })
    @Results({
            @Result(column="RECORD_ID", property="recordId", jdbcType=JdbcType.INTEGER),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="SALE_PRICE", property="salePrice", jdbcType=JdbcType.REAL),
            @Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER),
            @Result(column="PRODUCT_ID", property="productId", jdbcType=JdbcType.INTEGER),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER)
    })
    HistoryRecord selectByCustomerIdAndProductId(@Param("customerId")int customerId,@Param("productId")int productId);

    @Select("<script>"
            +"select RECORD_ID, CREATE_TIME, SALE_PRICE, COMMODITY_ID, PRODUCT_ID, CUSTOMER_ID from history_record where 1=1 "
            + "<if test='customerId!=null'>"
            + "AND CUSTOMER_ID = #{data.customerId} "
            + "</if>"
            + "<if test='recordId!=null'>"
            + "AND RECORD_ID = #{data.recordId} "
            + "</if>"
            + "ORDER BY CREATE_TIME DESC "
            + "</script>")
    @Results({
            @Result(column="RECORD_ID", property="recordId", jdbcType=JdbcType.INTEGER),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="SALE_PRICE", property="salePrice", jdbcType=JdbcType.REAL),
            @Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER),
            @Result(column="PRODUCT_ID", property="productId", jdbcType=JdbcType.INTEGER),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER)
    })
    List<HistoryRecord> selectAll(Query q);

    @Select("<script>"
            +"select RECORD_ID, CREATE_TIME, SALE_PRICE, COMMODITY_ID, PRODUCT_ID, CUSTOMER_ID from history_record "
            + "where <![CDATA[DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= DATE(CREATE_TIME)]]> "
            + "<if test='data.customerId!=null'>"
            + "AND CUSTOMER_ID = #{data.customerId} "
            + "</if>"
            + "<if test='data.recordId!=null'>"
            + "AND RECORD_ID = #{data.recordId} "
            + "</if>"
            + "ORDER BY CREATE_TIME DESC "
            + "LIMIT #{rowIndex},#{pageSize}"
            + "</script>")
    @Results({
            @Result(column="RECORD_ID", property="recordId", jdbcType=JdbcType.INTEGER),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="SALE_PRICE", property="salePrice", jdbcType=JdbcType.REAL),
            @Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER),
            @Result(column="PRODUCT_ID", property="productId", jdbcType=JdbcType.INTEGER),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
            @Result(column="COMMODITY_ID", property="commodity", jdbcType=JdbcType.INTEGER,
                    one = @One(select = "com.yunxin.cb.mall.mapper.CommodityMapper.selectByPrimaryKey"))
    })
    List<HistoryRecord> pageList(Query q);

    @Select("<script>"
            +"select count(RECORD_ID) from history_record "
            + "where <![CDATA[DATE_SUB(CURDATE(), INTERVAL 30 DAY) <= DATE(CREATE_TIME)]]> "
            + "<if test='data.customerId!=null'>"
            + "AND CUSTOMER_ID = #{data.customerId} "
            + "</if>"
            + "<if test='data.recordId!=null'>"
            + "AND RECORD_ID = #{data.recordId} "
            + "</if>"
            + "</script>")
    long count(Query q);

    @Delete({"<script>",
            "delete from history_record",
            "WHERE CUSTOMER_ID = #{customerId} and RECORD_ID IN",
            "<foreach item='item' index='index' collection='recordIds'",
            "open='(' separator=',' close=')'>",
            "#{item}",
            "</foreach>",
            "</script>"})
    int removeHistoryRecordeBatch(@Param("recordIds") List<Integer> recordIds,@Param("customerId")Integer customerId);

    @Delete({"delete from history_record",
            "WHERE CUSTOMER_ID = #{customerId} AND PRODUCT_ID = #{productId}"
    })
    int removeByCustomerIdAndProductId(@Param("customerId")int customerId,@Param("productId")int productId);
}