package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Delete({
        "delete from product",
        "where PRODUCT_ID = #{productId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer productId);

    @Insert({
        "insert into product (PRODUCT_ID, ADD_TIME, ",
        "COST_PRICE, CREATE_TIME, ",
        "DEFAULT_PIC_PATH, MARKET_PRICE, ",
        "PRODUCT_NAME, PRODUCT_NO, ",
        "PRODUCT_STATE, PUBLISH_STATE, ",
        "REMARK, SALE_PRICE, ",
        "STORE_NUM, VOLUME, WEIGHT, ",
        "COMMODITY_ID, STORE_ID)",
        "values (#{productId,jdbcType=INTEGER}, #{addTime,jdbcType=TIMESTAMP}, ",
        "#{costPrice,jdbcType=REAL}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{defaultPicPath,jdbcType=VARCHAR}, #{marketPrice,jdbcType=REAL}, ",
        "#{productName,jdbcType=VARCHAR}, #{productNo,jdbcType=VARCHAR}, ",
        "#{productState,jdbcType=INTEGER}, #{publishState,jdbcType=INTEGER}, ",
        "#{remark,jdbcType=VARCHAR}, #{salePrice,jdbcType=REAL}, ",
        "#{storeNum,jdbcType=INTEGER}, #{volume,jdbcType=REAL}, #{weight,jdbcType=REAL}, ",
        "#{commodityId,jdbcType=INTEGER}, #{storeId,jdbcType=INTEGER})"
    })
    int insert(Product record);

    @Select({
        "select",
        "PRODUCT_ID, ADD_TIME, COST_PRICE, CREATE_TIME, DEFAULT_PIC_PATH, MARKET_PRICE, ",
        "PRODUCT_NAME, PRODUCT_NO, PRODUCT_STATE, PUBLISH_STATE, REMARK, SALE_PRICE, ",
        "STORE_NUM, VOLUME, WEIGHT, COMMODITY_ID, STORE_ID",
        "from product",
        "where PRODUCT_ID = #{productId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="PRODUCT_ID", property="productId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ADD_TIME", property="addTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="COST_PRICE", property="costPrice", jdbcType=JdbcType.REAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="DEFAULT_PIC_PATH", property="defaultPicPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="MARKET_PRICE", property="marketPrice", jdbcType=JdbcType.REAL),
        @Result(column="PRODUCT_NAME", property="productName", jdbcType=JdbcType.VARCHAR),
        @Result(column="PRODUCT_NO", property="productNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="PRODUCT_STATE", property="productState", jdbcType=JdbcType.INTEGER),
        @Result(column="PUBLISH_STATE", property="publishState", jdbcType=JdbcType.INTEGER),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="SALE_PRICE", property="salePrice", jdbcType=JdbcType.REAL),
        @Result(column="STORE_NUM", property="storeNum", jdbcType=JdbcType.INTEGER),
        @Result(column="VOLUME", property="volume", jdbcType=JdbcType.REAL),
        @Result(column="WEIGHT", property="weight", jdbcType=JdbcType.REAL),
        @Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER),
        @Result(column="STORE_ID", property="storeId", jdbcType=JdbcType.INTEGER)
    })
    Product selectByPrimaryKey(Integer productId);

    @Select({
        "select",
        "PRODUCT_ID, ADD_TIME, COST_PRICE, CREATE_TIME, DEFAULT_PIC_PATH, MARKET_PRICE, ",
        "PRODUCT_NAME, PRODUCT_NO, PRODUCT_STATE, PUBLISH_STATE, REMARK, SALE_PRICE, ",
        "STORE_NUM, VOLUME, WEIGHT, COMMODITY_ID, STORE_ID",
        "from product"
    })
    @Results({
        @Result(column="PRODUCT_ID", property="productId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ADD_TIME", property="addTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="COST_PRICE", property="costPrice", jdbcType=JdbcType.REAL),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="DEFAULT_PIC_PATH", property="defaultPicPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="MARKET_PRICE", property="marketPrice", jdbcType=JdbcType.REAL),
        @Result(column="PRODUCT_NAME", property="productName", jdbcType=JdbcType.VARCHAR),
        @Result(column="PRODUCT_NO", property="productNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="PRODUCT_STATE", property="productState", jdbcType=JdbcType.INTEGER),
        @Result(column="PUBLISH_STATE", property="publishState", jdbcType=JdbcType.INTEGER),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="SALE_PRICE", property="salePrice", jdbcType=JdbcType.REAL),
        @Result(column="STORE_NUM", property="storeNum", jdbcType=JdbcType.INTEGER),
        @Result(column="VOLUME", property="volume", jdbcType=JdbcType.REAL),
        @Result(column="WEIGHT", property="weight", jdbcType=JdbcType.REAL),
        @Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER),
        @Result(column="STORE_ID", property="storeId", jdbcType=JdbcType.INTEGER)
    })
    List<Product> selectAll();

    @Select({
            "select",
            "PRODUCT_ID, ADD_TIME, COST_PRICE, CREATE_TIME, DEFAULT_PIC_PATH, MARKET_PRICE, ",
            "PRODUCT_NAME, PRODUCT_NO, PRODUCT_STATE, PUBLISH_STATE, REMARK, SALE_PRICE, ",
            "STORE_NUM, VOLUME, WEIGHT, COMMODITY_ID, STORE_ID",
            "from product where COMMODITY_ID = #{commodityId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="PRODUCT_ID", property="productId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="ADD_TIME", property="addTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="COST_PRICE", property="costPrice", jdbcType=JdbcType.REAL),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="DEFAULT_PIC_PATH", property="defaultPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="MARKET_PRICE", property="marketPrice", jdbcType=JdbcType.REAL),
            @Result(column="PRODUCT_NAME", property="productName", jdbcType=JdbcType.VARCHAR),
            @Result(column="PRODUCT_NO", property="productNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="PRODUCT_STATE", property="productState", jdbcType=JdbcType.INTEGER),
            @Result(column="PUBLISH_STATE", property="publishState", jdbcType=JdbcType.INTEGER),
            @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="SALE_PRICE", property="salePrice", jdbcType=JdbcType.REAL),
            @Result(column="STORE_NUM", property="storeNum", jdbcType=JdbcType.INTEGER),
            @Result(column="VOLUME", property="volume", jdbcType=JdbcType.REAL),
            @Result(column="WEIGHT", property="weight", jdbcType=JdbcType.REAL),
            @Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER),
            @Result(column="STORE_ID", property="storeId", jdbcType=JdbcType.INTEGER),
            @Result(column = "PRODUCT_ID",property = "productAttributes",
                    many = @Many(select = "com.yunxin.cb.mall.mapper.ProductAttributeMapper.selectAllByProductId"))
    })
    List<Product> selectAllByCommodityId(Integer commodityId);

    @Select({
            "select",
            "PRODUCT_ID, ADD_TIME, COST_PRICE, CREATE_TIME, DEFAULT_PIC_PATH, MARKET_PRICE, ",
            "PRODUCT_NAME, PRODUCT_NO, PRODUCT_STATE, PUBLISH_STATE, REMARK, SALE_PRICE, ",
            "STORE_NUM, VOLUME, WEIGHT, COMMODITY_ID, STORE_ID",
            "from product where COMMODITY_ID = #{commodityId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="PRODUCT_ID", property="productId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="ADD_TIME", property="addTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="COST_PRICE", property="costPrice", jdbcType=JdbcType.REAL),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="DEFAULT_PIC_PATH", property="defaultPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="MARKET_PRICE", property="marketPrice", jdbcType=JdbcType.REAL),
            @Result(column="PRODUCT_NAME", property="productName", jdbcType=JdbcType.VARCHAR),
            @Result(column="PRODUCT_NO", property="productNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="PRODUCT_STATE", property="productState", jdbcType=JdbcType.INTEGER),
            @Result(column="PUBLISH_STATE", property="publishState", jdbcType=JdbcType.INTEGER),
            @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="SALE_PRICE", property="salePrice", jdbcType=JdbcType.REAL),
            @Result(column="STORE_NUM", property="storeNum", jdbcType=JdbcType.INTEGER),
            @Result(column="VOLUME", property="volume", jdbcType=JdbcType.REAL),
            @Result(column="WEIGHT", property="weight", jdbcType=JdbcType.REAL),
            @Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER),
            @Result(column="STORE_ID", property="storeId", jdbcType=JdbcType.INTEGER),
            @Result(column = "PRODUCT_ID",property = "productAttributes",
                    many = @Many(select = "com.yunxin.cb.mall.mapper.ProductAttributeMapper.selectAllByProductId"))
    })
    Product selectProductById(Integer productId);

    @Update({
        "update product",
        "set ADD_TIME = #{addTime,jdbcType=TIMESTAMP},",
          "COST_PRICE = #{costPrice,jdbcType=REAL},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "DEFAULT_PIC_PATH = #{defaultPicPath,jdbcType=VARCHAR},",
          "MARKET_PRICE = #{marketPrice,jdbcType=REAL},",
          "PRODUCT_NAME = #{productName,jdbcType=VARCHAR},",
          "PRODUCT_NO = #{productNo,jdbcType=VARCHAR},",
          "PRODUCT_STATE = #{productState,jdbcType=INTEGER},",
          "PUBLISH_STATE = #{publishState,jdbcType=INTEGER},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "SALE_PRICE = #{salePrice,jdbcType=REAL},",
          "STORE_NUM = #{storeNum,jdbcType=INTEGER},",
          "VOLUME = #{volume,jdbcType=REAL},",
          "WEIGHT = #{weight,jdbcType=REAL},",
          "COMMODITY_ID = #{commodityId,jdbcType=INTEGER},",
          "STORE_ID = #{storeId,jdbcType=INTEGER}",
        "where PRODUCT_ID = #{productId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Product record);
}