package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.Commodity;
import java.util.List;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface CommodityMapper {
	@Delete({
			"delete from commodity",
			"where COMMODITY_ID = #{commodityId,jdbcType=INTEGER}"
	})
	int deleteByPrimaryKey(Integer commodityId);

	@Insert({
			"insert into commodity (COMMODITY_ID, AUDIT_REMARK, ",
			"BARTER, CITY, COMMODITY_CODE, ",
			"COMMODITY_NAME, COMMODITY_PYNAME, ",
			"COMMODITY_STATE, COMMODITY_TITLE, ",
			"CONTENT, COST_PRICE, ",
			"CREATE_TIME, DEFAULT_PIC_PATH, ",
			"DELIVERY_TYPE, DESCRIPTION, ",
			"FORBID_AIR_CARGO, GIVEAWAY, ",
			"MARKET_PRICE, POPULAR, ",
			"PRE_SELL, PROVINCE, PUBLISH_STATE, ",
			"RECOMMEND, SALE_NUM, ",
			"SELL_PRICE, SEO_DESCRIPTION, ",
			"SEO_KEY, SEO_TITLE, ",
			"SHORT_NAME, SPECIAL, ",
			"UNIT, VOLUME, WEIGHT, ",
			"BRAND_ID, CATALOG_ID, ",
			"SECTION_ID, SELLER_ID, ",
			"PACKING_LIST)",
			"values (#{commodityId,jdbcType=INTEGER}, #{auditRemark,jdbcType=VARCHAR}, ",
			"#{barter,jdbcType=BIT}, #{city,jdbcType=VARCHAR}, #{commodityCode,jdbcType=VARCHAR}, ",
			"#{commodityName,jdbcType=VARCHAR}, #{commodityPyname,jdbcType=VARCHAR}, ",
			"#{commodityState,jdbcType=INTEGER}, #{commodityTitle,jdbcType=VARCHAR}, ",
			"#{content,jdbcType=VARCHAR}, #{costPrice,jdbcType=DOUBLE}, ",
			"#{createTime,jdbcType=TIMESTAMP}, #{defaultPicPath,jdbcType=VARCHAR}, ",
			"#{deliveryType,jdbcType=INTEGER}, #{description,jdbcType=VARCHAR}, ",
			"#{forbidAirCargo,jdbcType=BIT}, #{giveaway,jdbcType=BIT}, ",
			"#{marketPrice,jdbcType=DOUBLE}, #{popular,jdbcType=BIT}, ",
			"#{preSell,jdbcType=BIT}, #{province,jdbcType=VARCHAR}, #{publishState,jdbcType=INTEGER}, ",
			"#{recommend,jdbcType=BIT}, #{saleNum,jdbcType=INTEGER}, ",
			"#{sellPrice,jdbcType=DOUBLE}, #{seoDescription,jdbcType=VARCHAR}, ",
			"#{seoKey,jdbcType=VARCHAR}, #{seoTitle,jdbcType=VARCHAR}, ",
			"#{shortName,jdbcType=VARCHAR}, #{special,jdbcType=BIT}, ",
			"#{unit,jdbcType=INTEGER}, #{volume,jdbcType=DOUBLE}, #{weight,jdbcType=DOUBLE}, ",
			"#{brandId,jdbcType=INTEGER}, #{catalogId,jdbcType=INTEGER}, ",
			"#{sectionId,jdbcType=INTEGER}, #{sellerId,jdbcType=INTEGER}, ",
			"#{packingList,jdbcType=VARCHAR})"
	})
	int insert(Commodity record);

	@Select({
			"select",
			"COMMODITY_ID, AUDIT_REMARK, BARTER, CITY, COMMODITY_CODE, COMMODITY_NAME, COMMODITY_PYNAME, ",
			"COMMODITY_STATE, COMMODITY_TITLE, CONTENT, COST_PRICE, CREATE_TIME, DEFAULT_PIC_PATH, ",
			"DELIVERY_TYPE, DESCRIPTION, FORBID_AIR_CARGO, GIVEAWAY, MARKET_PRICE, POPULAR, ",
			"PRE_SELL, PROVINCE, PUBLISH_STATE, RECOMMEND, SALE_NUM, SELL_PRICE, SEO_DESCRIPTION, ",
			"SEO_KEY, SEO_TITLE, SHORT_NAME, SPECIAL, UNIT, VOLUME, WEIGHT, BRAND_ID, CATALOG_ID, ",
			"SECTION_ID, SELLER_ID, PACKING_LIST",
			"from commodity",
			"where COMMODITY_ID = #{commodityId,jdbcType=INTEGER}"
	})
	@Results({
			@Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER, id=true),
			@Result(column="AUDIT_REMARK", property="auditRemark", jdbcType=JdbcType.VARCHAR),
			@Result(column="BARTER", property="barter", jdbcType=JdbcType.BIT),
			@Result(column="CITY", property="city", jdbcType=JdbcType.VARCHAR),
			@Result(column="COMMODITY_CODE", property="commodityCode", jdbcType=JdbcType.VARCHAR),
			@Result(column="COMMODITY_NAME", property="commodityName", jdbcType=JdbcType.VARCHAR),
			@Result(column="COMMODITY_PYNAME", property="commodityPyname", jdbcType=JdbcType.VARCHAR),
			@Result(column="COMMODITY_STATE", property="commodityState", jdbcType=JdbcType.INTEGER),
			@Result(column="COMMODITY_TITLE", property="commodityTitle", jdbcType=JdbcType.VARCHAR),
			@Result(column="CONTENT", property="content", jdbcType=JdbcType.VARCHAR),
			@Result(column="COST_PRICE", property="costPrice", jdbcType=JdbcType.DOUBLE),
			@Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
			@Result(column="DEFAULT_PIC_PATH", property="defaultPicPath", jdbcType=JdbcType.VARCHAR),
			@Result(column="DELIVERY_TYPE", property="deliveryType", jdbcType=JdbcType.INTEGER),
			@Result(column="DESCRIPTION", property="description", jdbcType=JdbcType.VARCHAR),
			@Result(column="FORBID_AIR_CARGO", property="forbidAirCargo", jdbcType=JdbcType.BIT),
			@Result(column="GIVEAWAY", property="giveaway", jdbcType=JdbcType.BIT),
			@Result(column="MARKET_PRICE", property="marketPrice", jdbcType=JdbcType.DOUBLE),
			@Result(column="POPULAR", property="popular", jdbcType=JdbcType.BIT),
			@Result(column="PRE_SELL", property="preSell", jdbcType=JdbcType.BIT),
			@Result(column="PROVINCE", property="province", jdbcType=JdbcType.VARCHAR),
			@Result(column="PUBLISH_STATE", property="publishState", jdbcType=JdbcType.INTEGER),
			@Result(column="RECOMMEND", property="recommend", jdbcType=JdbcType.BIT),
			@Result(column="SALE_NUM", property="saleNum", jdbcType=JdbcType.INTEGER),
			@Result(column="SELL_PRICE", property="sellPrice", jdbcType=JdbcType.DOUBLE),
			@Result(column="SEO_DESCRIPTION", property="seoDescription", jdbcType=JdbcType.VARCHAR),
			@Result(column="SEO_KEY", property="seoKey", jdbcType=JdbcType.VARCHAR),
			@Result(column="SEO_TITLE", property="seoTitle", jdbcType=JdbcType.VARCHAR),
			@Result(column="SHORT_NAME", property="shortName", jdbcType=JdbcType.VARCHAR),
			@Result(column="SPECIAL", property="special", jdbcType=JdbcType.BIT),
			@Result(column="UNIT", property="unit", jdbcType=JdbcType.INTEGER),
			@Result(column="VOLUME", property="volume", jdbcType=JdbcType.DOUBLE),
			@Result(column="WEIGHT", property="weight", jdbcType=JdbcType.DOUBLE),
			@Result(column="BRAND_ID", property="brandId", jdbcType=JdbcType.INTEGER),
			@Result(column="CATALOG_ID", property="catalogId", jdbcType=JdbcType.INTEGER),
			@Result(column="SECTION_ID", property="sectionId", jdbcType=JdbcType.INTEGER),
			@Result(column="SELLER_ID", property="sellerId", jdbcType=JdbcType.INTEGER),
			@Result(column="PACKING_LIST", property="packingList", jdbcType=JdbcType.VARCHAR)
	})
	Commodity selectByPrimaryKey(Integer commodityId);

	@Select({
			"select",
			"COMMODITY_ID, AUDIT_REMARK, BARTER, CITY, COMMODITY_CODE, COMMODITY_NAME, COMMODITY_PYNAME, ",
			"COMMODITY_STATE, COMMODITY_TITLE, CONTENT, COST_PRICE, CREATE_TIME, DEFAULT_PIC_PATH, ",
			"DELIVERY_TYPE, DESCRIPTION, FORBID_AIR_CARGO, GIVEAWAY, MARKET_PRICE, POPULAR, ",
			"PRE_SELL, PROVINCE, PUBLISH_STATE, RECOMMEND, SALE_NUM, SELL_PRICE, SEO_DESCRIPTION, ",
			"SEO_KEY, SEO_TITLE, SHORT_NAME, SPECIAL, UNIT, VOLUME, WEIGHT, BRAND_ID, CATALOG_ID, ",
			"SECTION_ID, SELLER_ID, PACKING_LIST",
			"from commodity"
	})
	@Results({
			@Result(column="COMMODITY_ID", property="commodityId", jdbcType=JdbcType.INTEGER, id=true),
			@Result(column="AUDIT_REMARK", property="auditRemark", jdbcType=JdbcType.VARCHAR),
			@Result(column="BARTER", property="barter", jdbcType=JdbcType.BIT),
			@Result(column="CITY", property="city", jdbcType=JdbcType.VARCHAR),
			@Result(column="COMMODITY_CODE", property="commodityCode", jdbcType=JdbcType.VARCHAR),
			@Result(column="COMMODITY_NAME", property="commodityName", jdbcType=JdbcType.VARCHAR),
			@Result(column="COMMODITY_PYNAME", property="commodityPyname", jdbcType=JdbcType.VARCHAR),
			@Result(column="COMMODITY_STATE", property="commodityState", jdbcType=JdbcType.INTEGER),
			@Result(column="COMMODITY_TITLE", property="commodityTitle", jdbcType=JdbcType.VARCHAR),
			@Result(column="CONTENT", property="content", jdbcType=JdbcType.VARCHAR),
			@Result(column="COST_PRICE", property="costPrice", jdbcType=JdbcType.DOUBLE),
			@Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
			@Result(column="DEFAULT_PIC_PATH", property="defaultPicPath", jdbcType=JdbcType.VARCHAR),
			@Result(column="DELIVERY_TYPE", property="deliveryType", jdbcType=JdbcType.INTEGER),
			@Result(column="DESCRIPTION", property="description", jdbcType=JdbcType.VARCHAR),
			@Result(column="FORBID_AIR_CARGO", property="forbidAirCargo", jdbcType=JdbcType.BIT),
			@Result(column="GIVEAWAY", property="giveaway", jdbcType=JdbcType.BIT),
			@Result(column="MARKET_PRICE", property="marketPrice", jdbcType=JdbcType.DOUBLE),
			@Result(column="POPULAR", property="popular", jdbcType=JdbcType.BIT),
			@Result(column="PRE_SELL", property="preSell", jdbcType=JdbcType.BIT),
			@Result(column="PROVINCE", property="province", jdbcType=JdbcType.VARCHAR),
			@Result(column="PUBLISH_STATE", property="publishState", jdbcType=JdbcType.INTEGER),
			@Result(column="RECOMMEND", property="recommend", jdbcType=JdbcType.BIT),
			@Result(column="SALE_NUM", property="saleNum", jdbcType=JdbcType.INTEGER),
			@Result(column="SELL_PRICE", property="sellPrice", jdbcType=JdbcType.DOUBLE),
			@Result(column="SEO_DESCRIPTION", property="seoDescription", jdbcType=JdbcType.VARCHAR),
			@Result(column="SEO_KEY", property="seoKey", jdbcType=JdbcType.VARCHAR),
			@Result(column="SEO_TITLE", property="seoTitle", jdbcType=JdbcType.VARCHAR),
			@Result(column="SHORT_NAME", property="shortName", jdbcType=JdbcType.VARCHAR),
			@Result(column="SPECIAL", property="special", jdbcType=JdbcType.BIT),
			@Result(column="UNIT", property="unit", jdbcType=JdbcType.INTEGER),
			@Result(column="VOLUME", property="volume", jdbcType=JdbcType.DOUBLE),
			@Result(column="WEIGHT", property="weight", jdbcType=JdbcType.DOUBLE),
			@Result(column="BRAND_ID", property="brandId", jdbcType=JdbcType.INTEGER),
			@Result(column="CATALOG_ID", property="catalogId", jdbcType=JdbcType.INTEGER),
			@Result(column="SECTION_ID", property="sectionId", jdbcType=JdbcType.INTEGER),
			@Result(column="SELLER_ID", property="sellerId", jdbcType=JdbcType.INTEGER),
			@Result(column="PACKING_LIST", property="packingList", jdbcType=JdbcType.VARCHAR)
	})
	List<Commodity> selectAll();

	@Update({
			"update commodity",
			"set AUDIT_REMARK = #{auditRemark,jdbcType=VARCHAR},",
			"BARTER = #{barter,jdbcType=BIT},",
			"CITY = #{city,jdbcType=VARCHAR},",
			"COMMODITY_CODE = #{commodityCode,jdbcType=VARCHAR},",
			"COMMODITY_NAME = #{commodityName,jdbcType=VARCHAR},",
			"COMMODITY_PYNAME = #{commodityPyname,jdbcType=VARCHAR},",
			"COMMODITY_STATE = #{commodityState,jdbcType=INTEGER},",
			"COMMODITY_TITLE = #{commodityTitle,jdbcType=VARCHAR},",
			"CONTENT = #{content,jdbcType=VARCHAR},",
			"COST_PRICE = #{costPrice,jdbcType=DOUBLE},",
			"CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
			"DEFAULT_PIC_PATH = #{defaultPicPath,jdbcType=VARCHAR},",
			"DELIVERY_TYPE = #{deliveryType,jdbcType=INTEGER},",
			"DESCRIPTION = #{description,jdbcType=VARCHAR},",
			"FORBID_AIR_CARGO = #{forbidAirCargo,jdbcType=BIT},",
			"GIVEAWAY = #{giveaway,jdbcType=BIT},",
			"MARKET_PRICE = #{marketPrice,jdbcType=DOUBLE},",
			"POPULAR = #{popular,jdbcType=BIT},",
			"PRE_SELL = #{preSell,jdbcType=BIT},",
			"PROVINCE = #{province,jdbcType=VARCHAR},",
			"PUBLISH_STATE = #{publishState,jdbcType=INTEGER},",
			"RECOMMEND = #{recommend,jdbcType=BIT},",
			"SALE_NUM = #{saleNum,jdbcType=INTEGER},",
			"SELL_PRICE = #{sellPrice,jdbcType=DOUBLE},",
			"SEO_DESCRIPTION = #{seoDescription,jdbcType=VARCHAR},",
			"SEO_KEY = #{seoKey,jdbcType=VARCHAR},",
			"SEO_TITLE = #{seoTitle,jdbcType=VARCHAR},",
			"SHORT_NAME = #{shortName,jdbcType=VARCHAR},",
			"SPECIAL = #{special,jdbcType=BIT},",
			"UNIT = #{unit,jdbcType=INTEGER},",
			"VOLUME = #{volume,jdbcType=DOUBLE},",
			"WEIGHT = #{weight,jdbcType=DOUBLE},",
			"BRAND_ID = #{brandId,jdbcType=INTEGER},",
			"CATALOG_ID = #{catalogId,jdbcType=INTEGER},",
			"SECTION_ID = #{sectionId,jdbcType=INTEGER},",
			"SELLER_ID = #{sellerId,jdbcType=INTEGER},",
			"PACKING_LIST = #{packingList,jdbcType=VARCHAR}",
			"where COMMODITY_ID = #{commodityId,jdbcType=INTEGER}"
	})
	int updateByPrimaryKey(Commodity record);
}