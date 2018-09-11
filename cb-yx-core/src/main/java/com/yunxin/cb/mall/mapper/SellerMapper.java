package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.Seller;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface SellerMapper {

    @Delete({
            "delete from yx_seller",
            "where SELLER_ID = #{sellerId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer sellerId);

    @Insert({
            "insert into yx_seller (SELLER_ID, SELLER_CODE, ",
            "SELLER_NAME, SELLER_TYPE, ",
            "LINKMAN, MOBILE, ",
            "TELEPHONE, EMAIL, ",
            "PROVINCE, CITY, ",
            "DISTRICT, PROVINCE_NAME, ",
            "CITY_NAME, DISTRICT_NAME, ",
            "POSITION_X, POSITION_Y, ",
            "SELLER_ADDRESS, QQ, ",
            "WECHAT, CHANNEL_TYPE, ",
            "CHANNEL_ACCOUNT, BUS_NAME, ",
            "BUSLICENSE_NO, BUSLICENSE_PIC_PATH, ",
            "ACCOUNT_NAME, PUBLIC_ACCOUNT, ",
            "BANK_ACCOUNT, BANK_ACCOUNT_ADDRESS, ",
            "AGREEMENT_PIC_PATH, AUDIT, ",
            "ID_CARD_NUM, HOLD_ID_POTO_PIC_PATH, ",
            "POSITIVE_ID_POTO_PIC_PATH, INTRODUCTION, ",
            "BIG_PIC_PATH, MEDIUM_PIC_PATH, ",
            "SMALL_PIC_PATH, CREATE_TIME, ",
            "ENABLED, REMARK)",
            "values (#{sellerId,jdbcType=INTEGER}, #{sellerCode,jdbcType=VARCHAR}, ",
            "#{sellerName,jdbcType=VARCHAR}, #{sellerType,jdbcType=INTEGER}, ",
            "#{linkman,jdbcType=VARCHAR}, #{mobile,jdbcType=VARCHAR}, ",
            "#{telephone,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, ",
            "#{province,jdbcType=VARCHAR}, #{city,jdbcType=VARCHAR}, ",
            "#{district,jdbcType=VARCHAR}, #{provinceName,jdbcType=VARCHAR}, ",
            "#{cityName,jdbcType=VARCHAR}, #{districtName,jdbcType=VARCHAR}, ",
            "#{positionX,jdbcType=VARCHAR}, #{positionY,jdbcType=VARCHAR}, ",
            "#{sellerAddress,jdbcType=VARCHAR}, #{qq,jdbcType=VARCHAR}, ",
            "#{wechat,jdbcType=VARCHAR}, #{channelType,jdbcType=INTEGER}, ",
            "#{channelAccount,jdbcType=VARCHAR}, #{busName,jdbcType=VARCHAR}, ",
            "#{buslicenseNo,jdbcType=VARCHAR}, #{buslicensePicPath,jdbcType=VARCHAR}, ",
            "#{accountName,jdbcType=VARCHAR}, #{publicAccount,jdbcType=VARCHAR}, ",
            "#{bankAccount,jdbcType=VARCHAR}, #{bankAccountAddress,jdbcType=VARCHAR}, ",
            "#{agreementPicPath,jdbcType=VARCHAR}, #{audit,jdbcType=BIT}, ",
            "#{idCardNum,jdbcType=VARCHAR}, #{holdIdPotoPicPath,jdbcType=VARCHAR}, ",
            "#{positiveIdPotoPicPath,jdbcType=VARCHAR}, #{introduction,jdbcType=VARCHAR}, ",
            "#{bigPicPath,jdbcType=VARCHAR}, #{mediumPicPath,jdbcType=VARCHAR}, ",
            "#{smallPicPath,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
            "#{enabled,jdbcType=BIT}, #{remark,jdbcType=VARCHAR})"
    })
    int insert(Seller record);

    @Select({
            "select",
            "SELLER_ID, SELLER_CODE, SELLER_NAME, SELLER_TYPE, LINKMAN, MOBILE, TELEPHONE, ",
            "EMAIL, PROVINCE, CITY, DISTRICT, PROVINCE_NAME, CITY_NAME, DISTRICT_NAME, POSITION_X, ",
            "POSITION_Y, SELLER_ADDRESS, QQ, WECHAT, CHANNEL_TYPE, CHANNEL_ACCOUNT, BUS_NAME, ",
            "BUSLICENSE_NO, BUSLICENSE_PIC_PATH, ACCOUNT_NAME, PUBLIC_ACCOUNT, BANK_ACCOUNT, ",
            "BANK_ACCOUNT_ADDRESS, AGREEMENT_PIC_PATH, AUDIT, ID_CARD_NUM, HOLD_ID_POTO_PIC_PATH, ",
            "POSITIVE_ID_POTO_PIC_PATH, INTRODUCTION, BIG_PIC_PATH, MEDIUM_PIC_PATH, SMALL_PIC_PATH, ",
            "CREATE_TIME, ENABLED, REMARK",
            "from yx_seller",
            "where SELLER_ID = #{sellerId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="SELLER_ID", property="sellerId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="SELLER_CODE", property="sellerCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="SELLER_NAME", property="sellerName", jdbcType=JdbcType.VARCHAR),
            @Result(column="SELLER_TYPE", property="sellerType", jdbcType=JdbcType.INTEGER),
            @Result(column="LINKMAN", property="linkman", jdbcType=JdbcType.VARCHAR),
            @Result(column="MOBILE", property="mobile", jdbcType=JdbcType.VARCHAR),
            @Result(column="TELEPHONE", property="telephone", jdbcType=JdbcType.VARCHAR),
            @Result(column="EMAIL", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="PROVINCE", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="CITY", property="city", jdbcType=JdbcType.VARCHAR),
            @Result(column="DISTRICT", property="district", jdbcType=JdbcType.VARCHAR),
            @Result(column="PROVINCE_NAME", property="provinceName", jdbcType=JdbcType.VARCHAR),
            @Result(column="CITY_NAME", property="cityName", jdbcType=JdbcType.VARCHAR),
            @Result(column="DISTRICT_NAME", property="districtName", jdbcType=JdbcType.VARCHAR),
            @Result(column="POSITION_X", property="positionX", jdbcType=JdbcType.VARCHAR),
            @Result(column="POSITION_Y", property="positionY", jdbcType=JdbcType.VARCHAR),
            @Result(column="SELLER_ADDRESS", property="sellerAddress", jdbcType=JdbcType.VARCHAR),
            @Result(column="QQ", property="qq", jdbcType=JdbcType.VARCHAR),
            @Result(column="WECHAT", property="wechat", jdbcType=JdbcType.VARCHAR),
            @Result(column="CHANNEL_TYPE", property="channelType", jdbcType=JdbcType.INTEGER),
            @Result(column="CHANNEL_ACCOUNT", property="channelAccount", jdbcType=JdbcType.VARCHAR),
            @Result(column="BUS_NAME", property="busName", jdbcType=JdbcType.VARCHAR),
            @Result(column="BUSLICENSE_NO", property="buslicenseNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="BUSLICENSE_PIC_PATH", property="buslicensePicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="ACCOUNT_NAME", property="accountName", jdbcType=JdbcType.VARCHAR),
            @Result(column="PUBLIC_ACCOUNT", property="publicAccount", jdbcType=JdbcType.VARCHAR),
            @Result(column="BANK_ACCOUNT", property="bankAccount", jdbcType=JdbcType.VARCHAR),
            @Result(column="BANK_ACCOUNT_ADDRESS", property="bankAccountAddress", jdbcType=JdbcType.VARCHAR),
            @Result(column="AGREEMENT_PIC_PATH", property="agreementPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="AUDIT", property="audit", jdbcType=JdbcType.BIT),
            @Result(column="ID_CARD_NUM", property="idCardNum", jdbcType=JdbcType.VARCHAR),
            @Result(column="HOLD_ID_POTO_PIC_PATH", property="holdIdPotoPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="POSITIVE_ID_POTO_PIC_PATH", property="positiveIdPotoPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="INTRODUCTION", property="introduction", jdbcType=JdbcType.VARCHAR),
            @Result(column="BIG_PIC_PATH", property="bigPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="MEDIUM_PIC_PATH", property="mediumPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="SMALL_PIC_PATH", property="smallPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
            @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    Seller selectByPrimaryKey(Integer sellerId);

    @Select({
            "select",
            "SELLER_ID, SELLER_CODE, SELLER_NAME, SELLER_TYPE, LINKMAN, MOBILE, TELEPHONE, ",
            "EMAIL, PROVINCE, CITY, DISTRICT, PROVINCE_NAME, CITY_NAME, DISTRICT_NAME, POSITION_X, ",
            "POSITION_Y, SELLER_ADDRESS, QQ, WECHAT, CHANNEL_TYPE, CHANNEL_ACCOUNT, BUS_NAME, ",
            "BUSLICENSE_NO, BUSLICENSE_PIC_PATH, ACCOUNT_NAME, PUBLIC_ACCOUNT, BANK_ACCOUNT, ",
            "BANK_ACCOUNT_ADDRESS, AGREEMENT_PIC_PATH, AUDIT, ID_CARD_NUM, HOLD_ID_POTO_PIC_PATH, ",
            "POSITIVE_ID_POTO_PIC_PATH, INTRODUCTION, BIG_PIC_PATH, MEDIUM_PIC_PATH, SMALL_PIC_PATH, ",
            "CREATE_TIME, ENABLED, REMARK",
            "from yx_seller"
    })
    @Results({
            @Result(column="SELLER_ID", property="sellerId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="SELLER_CODE", property="sellerCode", jdbcType=JdbcType.VARCHAR),
            @Result(column="SELLER_NAME", property="sellerName", jdbcType=JdbcType.VARCHAR),
            @Result(column="SELLER_TYPE", property="sellerType", jdbcType=JdbcType.INTEGER),
            @Result(column="LINKMAN", property="linkman", jdbcType=JdbcType.VARCHAR),
            @Result(column="MOBILE", property="mobile", jdbcType=JdbcType.VARCHAR),
            @Result(column="TELEPHONE", property="telephone", jdbcType=JdbcType.VARCHAR),
            @Result(column="EMAIL", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="PROVINCE", property="province", jdbcType=JdbcType.VARCHAR),
            @Result(column="CITY", property="city", jdbcType=JdbcType.VARCHAR),
            @Result(column="DISTRICT", property="district", jdbcType=JdbcType.VARCHAR),
            @Result(column="PROVINCE_NAME", property="provinceName", jdbcType=JdbcType.VARCHAR),
            @Result(column="CITY_NAME", property="cityName", jdbcType=JdbcType.VARCHAR),
            @Result(column="DISTRICT_NAME", property="districtName", jdbcType=JdbcType.VARCHAR),
            @Result(column="POSITION_X", property="positionX", jdbcType=JdbcType.VARCHAR),
            @Result(column="POSITION_Y", property="positionY", jdbcType=JdbcType.VARCHAR),
            @Result(column="SELLER_ADDRESS", property="sellerAddress", jdbcType=JdbcType.VARCHAR),
            @Result(column="QQ", property="qq", jdbcType=JdbcType.VARCHAR),
            @Result(column="WECHAT", property="wechat", jdbcType=JdbcType.VARCHAR),
            @Result(column="CHANNEL_TYPE", property="channelType", jdbcType=JdbcType.INTEGER),
            @Result(column="CHANNEL_ACCOUNT", property="channelAccount", jdbcType=JdbcType.VARCHAR),
            @Result(column="BUS_NAME", property="busName", jdbcType=JdbcType.VARCHAR),
            @Result(column="BUSLICENSE_NO", property="buslicenseNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="BUSLICENSE_PIC_PATH", property="buslicensePicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="ACCOUNT_NAME", property="accountName", jdbcType=JdbcType.VARCHAR),
            @Result(column="PUBLIC_ACCOUNT", property="publicAccount", jdbcType=JdbcType.VARCHAR),
            @Result(column="BANK_ACCOUNT", property="bankAccount", jdbcType=JdbcType.VARCHAR),
            @Result(column="BANK_ACCOUNT_ADDRESS", property="bankAccountAddress", jdbcType=JdbcType.VARCHAR),
            @Result(column="AGREEMENT_PIC_PATH", property="agreementPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="AUDIT", property="audit", jdbcType=JdbcType.BIT),
            @Result(column="ID_CARD_NUM", property="idCardNum", jdbcType=JdbcType.VARCHAR),
            @Result(column="HOLD_ID_POTO_PIC_PATH", property="holdIdPotoPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="POSITIVE_ID_POTO_PIC_PATH", property="positiveIdPotoPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="INTRODUCTION", property="introduction", jdbcType=JdbcType.VARCHAR),
            @Result(column="BIG_PIC_PATH", property="bigPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="MEDIUM_PIC_PATH", property="mediumPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="SMALL_PIC_PATH", property="smallPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
            @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    List<Seller> selectAll();

    @Update({
            "update yx_seller",
            "set SELLER_CODE = #{sellerCode,jdbcType=VARCHAR},",
            "SELLER_NAME = #{sellerName,jdbcType=VARCHAR},",
            "SELLER_TYPE = #{sellerType,jdbcType=INTEGER},",
            "LINKMAN = #{linkman,jdbcType=VARCHAR},",
            "MOBILE = #{mobile,jdbcType=VARCHAR},",
            "TELEPHONE = #{telephone,jdbcType=VARCHAR},",
            "EMAIL = #{email,jdbcType=VARCHAR},",
            "PROVINCE = #{province,jdbcType=VARCHAR},",
            "CITY = #{city,jdbcType=VARCHAR},",
            "DISTRICT = #{district,jdbcType=VARCHAR},",
            "PROVINCE_NAME = #{provinceName,jdbcType=VARCHAR},",
            "CITY_NAME = #{cityName,jdbcType=VARCHAR},",
            "DISTRICT_NAME = #{districtName,jdbcType=VARCHAR},",
            "POSITION_X = #{positionX,jdbcType=VARCHAR},",
            "POSITION_Y = #{positionY,jdbcType=VARCHAR},",
            "SELLER_ADDRESS = #{sellerAddress,jdbcType=VARCHAR},",
            "QQ = #{qq,jdbcType=VARCHAR},",
            "WECHAT = #{wechat,jdbcType=VARCHAR},",
            "CHANNEL_TYPE = #{channelType,jdbcType=INTEGER},",
            "CHANNEL_ACCOUNT = #{channelAccount,jdbcType=VARCHAR},",
            "BUS_NAME = #{busName,jdbcType=VARCHAR},",
            "BUSLICENSE_NO = #{buslicenseNo,jdbcType=VARCHAR},",
            "BUSLICENSE_PIC_PATH = #{buslicensePicPath,jdbcType=VARCHAR},",
            "ACCOUNT_NAME = #{accountName,jdbcType=VARCHAR},",
            "PUBLIC_ACCOUNT = #{publicAccount,jdbcType=VARCHAR},",
            "BANK_ACCOUNT = #{bankAccount,jdbcType=VARCHAR},",
            "BANK_ACCOUNT_ADDRESS = #{bankAccountAddress,jdbcType=VARCHAR},",
            "AGREEMENT_PIC_PATH = #{agreementPicPath,jdbcType=VARCHAR},",
            "AUDIT = #{audit,jdbcType=BIT},",
            "ID_CARD_NUM = #{idCardNum,jdbcType=VARCHAR},",
            "HOLD_ID_POTO_PIC_PATH = #{holdIdPotoPicPath,jdbcType=VARCHAR},",
            "POSITIVE_ID_POTO_PIC_PATH = #{positiveIdPotoPicPath,jdbcType=VARCHAR},",
            "INTRODUCTION = #{introduction,jdbcType=VARCHAR},",
            "BIG_PIC_PATH = #{bigPicPath,jdbcType=VARCHAR},",
            "MEDIUM_PIC_PATH = #{mediumPicPath,jdbcType=VARCHAR},",
            "SMALL_PIC_PATH = #{smallPicPath,jdbcType=VARCHAR},",
            "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
            "ENABLED = #{enabled,jdbcType=BIT},",
            "REMARK = #{remark,jdbcType=VARCHAR}",
            "where SELLER_ID = #{sellerId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Seller record);

    @Select({
            "SELECT city,CITY_NAME " +
            "FROM yx_seller " +
            "WHERE city_name IS NOT NULL GROUP BY CITY_NAME "
    })
    @Results({
            @Result(column="CITY", property="city", jdbcType=JdbcType.VARCHAR),
            @Result(column="CITY_NAME", property="cityName", jdbcType=JdbcType.VARCHAR)
    })
    List<Seller> getAllSellerAddress();
}