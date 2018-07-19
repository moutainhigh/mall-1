package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.Favorite;
import com.yunxin.cb.util.page.Query;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface FavoriteMapper {
    @Delete({
        "delete from favorite",
        "where FAVORITE_ID = #{favoriteId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer favoriteId);

    @Insert({
        "insert into favorite (FAVORITE_ID, CREATE_TIME, ",
        "SALE_PRICE, COMMODITY_ID, ",
        "CUSTOMER_ID)",
        "values (#{favoriteId,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{salePrice,jdbcType=REAL}, #{commodityId,jdbcType=INTEGER}, ",
        "#{customerId,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true, keyProperty="favoriteId", keyColumn="FAVORITE_ID")
    int insert(Favorite record);

    @Select({
        "select",
        "FAVORITE_ID, CREATE_TIME, SALE_PRICE, COMMODITY_ID, CUSTOMER_ID",
        "from favorite",
        "where FAVORITE_ID = #{favoriteId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="FAVORITE_ID", property="favoriteId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="SALE_PRICE", property="salePrice", jdbcType=JdbcType.REAL),
        @Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER),
        @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER)
    })
    Favorite selectByPrimaryKey(Integer favoriteId);

    @Select("<script>"
            +"select FAVORITE_ID, CREATE_TIME, SALE_PRICE, COMMODITY_ID, CUSTOMER_ID from favorite where 1=1 "
            + "<if test='customerId!=null'>"
            + "AND CUSTOMER_ID = #{data.customerId} "
            + "</if>"
            + "<if test='commodityId!=null'>"
            + "AND FAVORITE_ID = #{data.commodityId} "
            + "</if>"
            + "ORDER BY CREATE_TIME DESC "
            + "</script>")
    @Results({
            @Result(column="FAVORITE_ID", property="favoriteId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="SALE_PRICE", property="salePrice", jdbcType=JdbcType.REAL),
            @Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER)
    })
    List<Favorite> selectAll(Query q);

    @Select({
            "select",
            "FAVORITE_ID, CREATE_TIME, SALE_PRICE, COMMODITY_ID, CUSTOMER_ID",
            "from favorite",
            "where CUSTOMER_ID = #{customerId,jdbcType=INTEGER}",
            "and   COMMODITY_ID = #{commodityId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="FAVORITE_ID", property="favoriteId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="SALE_PRICE", property="salePrice", jdbcType=JdbcType.REAL),
            @Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER)
    })
    Favorite findByCustomerAndCommodity(Favorite record);

    @Update({
        "update favorite",
        "set CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "SALE_PRICE = #{salePrice,jdbcType=REAL},",
          "COMMODITY_ID = #{commodityId,jdbcType=INTEGER},",
          "CUSTOMER_ID = #{customerId,jdbcType=INTEGER}",
        "where FAVORITE_ID = #{favoriteId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Favorite record);

    @Select("<script>"
            +"select FAVORITE_ID, CREATE_TIME, SALE_PRICE, COMMODITY_ID, CUSTOMER_ID from favorite where 1=1"
            + "<if test='data.customerId!=null'>"
            + "AND CUSTOMER_ID = #{data.customerId} "
            + "</if>"
            + "<if test='data.commodityId!=null'>"
            + "AND FAVORITE_ID = #{data.commodityId} "
            + "</if>"
            + "ORDER BY CREATE_TIME DESC "
            + "LIMIT #{rowIndex},#{pageSize}"
            + "</script>")
    @Results({
            @Result(column="FAVORITE_ID", property="favoriteId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="SALE_PRICE", property="salePrice", jdbcType=JdbcType.REAL),
            @Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER),
            @Result(column="CUSTOMER_ID", property="customerId", jdbcType=JdbcType.INTEGER),
            @Result(column="COMMODITY_ID", property="commodity", jdbcType=JdbcType.INTEGER,
                    one = @One(select = "com.yunxin.cb.mall.mapper.CommodityMapper.selectByPrimaryKey"))
            })
    List<Favorite> pageList(Query q);

    @Select("<script>"
            +"select count(FAVORITE_ID) from favorite where 1=1"
            + "<if test='data.customerId!=null'>"
            + "AND CUSTOMER_ID = #{data.customerId} "
            + "</if>"
            + "<if test='data.commodityId!=null'>"
            + "AND FAVORITE_ID = #{data.commodityId} "
            + "</if>"
            + "</script>")
    long count(Query q);
}