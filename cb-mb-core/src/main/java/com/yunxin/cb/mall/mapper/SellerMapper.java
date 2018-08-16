package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.Seller;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface SellerMapper {
    @Delete({
        "delete from seller",
        "where SELLER_ID = #{sellerId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer sellerId);

    @Insert({
        "insert into seller (SELLER_ID, ACCOUNT_NAME, ",
        "AGREEMENT_PIC_PATH, AUDIT, ",
        "BANK_ACCOUNT, BANK_ACCOUNT_ADDRESS, ",
        "BUS_NAME, BUSLICENSE_NO, ",
        "BUSLICENSE_PIC_PATH, CHANNEL_ACCOUNT, ",
        "CHANNEL_TYPE, CREATE_TIME, ",
        "EMAIL, ENABLED, HOLD_ID_POTO_PIC_PATH, ",
        "ID_CARD_NUM, INTRODUCTION, ",
        "LINKMAN, MOBILE, ",
        "POSITIVE_ID_POTO_PIC_PATH, PUBLIC_ACCOUNT, ",
        "QQ, REMARK, SELLER_ADDRESS, ",
        "SELLER_CODE, SELLER_NAME, ",
        "SELLER_TYPE, TELEPHONE, ",
        "WECHAT",
        "values (#{sellerId,jdbcType=INTEGER}, #{accountName,jdbcType=VARCHAR}, ",
        "#{agreementPicPath,jdbcType=VARCHAR}, #{audit,jdbcType=BIT}, ",
        "#{bankAccount,jdbcType=VARCHAR}, #{bankAccountAddress,jdbcType=VARCHAR}, ",
        "#{busName,jdbcType=VARCHAR}, #{buslicenseNo,jdbcType=VARCHAR}, ",
        "#{buslicensePicPath,jdbcType=VARCHAR}, #{channelAccount,jdbcType=VARCHAR}, ",
        "#{channelType,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{email,jdbcType=VARCHAR}, #{enabled,jdbcType=BIT}, #{holdIdPotoPicPath,jdbcType=VARCHAR}, ",
        "#{idCardNum,jdbcType=VARCHAR}, #{introduction,jdbcType=VARCHAR}, ",
        "#{linkman,jdbcType=VARCHAR}, #{mobile,jdbcType=VARCHAR}, ",
        "#{positiveIdPotoPicPath,jdbcType=VARCHAR}, #{publicAccount,jdbcType=VARCHAR}, ",
        "#{qq,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, #{sellerAddress,jdbcType=VARCHAR}, ",
        "#{sellerCode,jdbcType=VARCHAR}, #{sellerName,jdbcType=VARCHAR}, ",
        "#{sellerType,jdbcType=INTEGER}, #{telephone,jdbcType=VARCHAR}, ",
        "#{wechat,jdbcType=VARCHAR},#{positionX,jdbcType=VARCHAR}, #{positionY,jdbcType=VARCHAR})"
    })
    int insert(Seller record);

    @Select({
        "select",
        "SELLER_ID, ACCOUNT_NAME, AGREEMENT_PIC_PATH, AUDIT, BANK_ACCOUNT, BANK_ACCOUNT_ADDRESS, ",
        "BUS_NAME, BUSLICENSE_NO, BUSLICENSE_PIC_PATH, CHANNEL_ACCOUNT, CHANNEL_TYPE, ",
        "CREATE_TIME, EMAIL, ENABLED, HOLD_ID_POTO_PIC_PATH, ID_CARD_NUM, INTRODUCTION, ",
        "LINKMAN, MOBILE, POSITIVE_ID_POTO_PIC_PATH, PUBLIC_ACCOUNT, QQ, REMARK, SELLER_ADDRESS, ",
        "SELLER_CODE, SELLER_NAME, SELLER_TYPE, TELEPHONE, WECHAT, POSITION_X, POSITION_Y, PROVINCE, CITY, DISTRICT ",
        "from seller",
        "where SELLER_ID = #{sellerId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="SELLER_ID", property="sellerId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ACCOUNT_NAME", property="accountName", jdbcType=JdbcType.VARCHAR),
        @Result(column="AGREEMENT_PIC_PATH", property="agreementPicPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="AUDIT", property="audit", jdbcType=JdbcType.BIT),
        @Result(column="BANK_ACCOUNT", property="bankAccount", jdbcType=JdbcType.VARCHAR),
        @Result(column="BANK_ACCOUNT_ADDRESS", property="bankAccountAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="BUS_NAME", property="busName", jdbcType=JdbcType.VARCHAR),
        @Result(column="BUSLICENSE_NO", property="buslicenseNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="BUSLICENSE_PIC_PATH", property="buslicensePicPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHANNEL_ACCOUNT", property="channelAccount", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHANNEL_TYPE", property="channelType", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="EMAIL", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
        @Result(column="HOLD_ID_POTO_PIC_PATH", property="holdIdPotoPicPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="ID_CARD_NUM", property="idCardNum", jdbcType=JdbcType.VARCHAR),
        @Result(column="INTRODUCTION", property="introduction", jdbcType=JdbcType.VARCHAR),
        @Result(column="LINKMAN", property="linkman", jdbcType=JdbcType.VARCHAR),
        @Result(column="MOBILE", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="POSITIVE_ID_POTO_PIC_PATH", property="positiveIdPotoPicPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="PUBLIC_ACCOUNT", property="publicAccount", jdbcType=JdbcType.VARCHAR),
        @Result(column="QQ", property="qq", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="SELLER_ADDRESS", property="sellerAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="SELLER_CODE", property="sellerCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="SELLER_NAME", property="sellerName", jdbcType=JdbcType.VARCHAR),
        @Result(column="SELLER_TYPE", property="sellerType", jdbcType=JdbcType.INTEGER),
        @Result(column="TELEPHONE", property="telephone", jdbcType=JdbcType.VARCHAR),
        @Result(column="WECHAT", property="wechat", jdbcType=JdbcType.VARCHAR),
        @Result(column="POSITION_X", property="positionX", jdbcType=JdbcType.VARCHAR),
        @Result(column="POSITION_Y", property="positionY", jdbcType=JdbcType.VARCHAR),
        @Result(column="PROVINCE", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="CITY", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="DISTRICT", property="district", jdbcType=JdbcType.VARCHAR)
    })
    Seller selectByPrimaryKey(Integer sellerId);

    @Select({
        "select",
        "SELLER_ID, ACCOUNT_NAME, AGREEMENT_PIC_PATH, AUDIT, BANK_ACCOUNT, BANK_ACCOUNT_ADDRESS, ",
        "BUS_NAME, BUSLICENSE_NO, BUSLICENSE_PIC_PATH, CHANNEL_ACCOUNT, CHANNEL_TYPE, ",
        "CREATE_TIME, EMAIL, ENABLED, HOLD_ID_POTO_PIC_PATH, ID_CARD_NUM, INTRODUCTION, ",
        "LINKMAN, MOBILE, POSITIVE_ID_POTO_PIC_PATH, PUBLIC_ACCOUNT, QQ, REMARK, SELLER_ADDRESS, ",
        "SELLER_CODE, SELLER_NAME, SELLER_TYPE, TELEPHONE, WECHAT, POSITION_X, POSITION_Y, PROVINCE, CITY, DISTRICT ",
        "from seller"
    })
    @Results({
        @Result(column="SELLER_ID", property="sellerId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ACCOUNT_NAME", property="accountName", jdbcType=JdbcType.VARCHAR),
        @Result(column="AGREEMENT_PIC_PATH", property="agreementPicPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="AUDIT", property="audit", jdbcType=JdbcType.BIT),
        @Result(column="BANK_ACCOUNT", property="bankAccount", jdbcType=JdbcType.VARCHAR),
        @Result(column="BANK_ACCOUNT_ADDRESS", property="bankAccountAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="BUS_NAME", property="busName", jdbcType=JdbcType.VARCHAR),
        @Result(column="BUSLICENSE_NO", property="buslicenseNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="BUSLICENSE_PIC_PATH", property="buslicensePicPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHANNEL_ACCOUNT", property="channelAccount", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHANNEL_TYPE", property="channelType", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="EMAIL", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
        @Result(column="HOLD_ID_POTO_PIC_PATH", property="holdIdPotoPicPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="ID_CARD_NUM", property="idCardNum", jdbcType=JdbcType.VARCHAR),
        @Result(column="INTRODUCTION", property="introduction", jdbcType=JdbcType.VARCHAR),
        @Result(column="LINKMAN", property="linkman", jdbcType=JdbcType.VARCHAR),
        @Result(column="MOBILE", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="POSITIVE_ID_POTO_PIC_PATH", property="positiveIdPotoPicPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="PUBLIC_ACCOUNT", property="publicAccount", jdbcType=JdbcType.VARCHAR),
        @Result(column="QQ", property="qq", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="SELLER_ADDRESS", property="sellerAddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="SELLER_CODE", property="sellerCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="SELLER_NAME", property="sellerName", jdbcType=JdbcType.VARCHAR),
        @Result(column="SELLER_TYPE", property="sellerType", jdbcType=JdbcType.INTEGER),
        @Result(column="TELEPHONE", property="telephone", jdbcType=JdbcType.VARCHAR),
        @Result(column="WECHAT", property="wechat", jdbcType=JdbcType.VARCHAR),
        @Result(column="POSITION_X", property="positionX", jdbcType=JdbcType.VARCHAR),
        @Result(column="POSITION_Y", property="positionY", jdbcType=JdbcType.VARCHAR),
        @Result(column="PROVINCE", property="province", jdbcType=JdbcType.VARCHAR),
        @Result(column="CITY", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="DISTRICT", property="district", jdbcType=JdbcType.VARCHAR)
    })
    List<Seller> selectAll();

    @Update({
        "update seller",
        "set ACCOUNT_NAME = #{accountName,jdbcType=VARCHAR},",
          "AGREEMENT_PIC_PATH = #{agreementPicPath,jdbcType=VARCHAR},",
          "AUDIT = #{audit,jdbcType=BIT},",
          "BANK_ACCOUNT = #{bankAccount,jdbcType=VARCHAR},",
          "BANK_ACCOUNT_ADDRESS = #{bankAccountAddress,jdbcType=VARCHAR},",
          "BUS_NAME = #{busName,jdbcType=VARCHAR},",
          "BUSLICENSE_NO = #{buslicenseNo,jdbcType=VARCHAR},",
          "BUSLICENSE_PIC_PATH = #{buslicensePicPath,jdbcType=VARCHAR},",
          "CHANNEL_ACCOUNT = #{channelAccount,jdbcType=VARCHAR},",
          "CHANNEL_TYPE = #{channelType,jdbcType=INTEGER},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "EMAIL = #{email,jdbcType=VARCHAR},",
          "ENABLED = #{enabled,jdbcType=BIT},",
          "HOLD_ID_POTO_PIC_PATH = #{holdIdPotoPicPath,jdbcType=VARCHAR},",
          "ID_CARD_NUM = #{idCardNum,jdbcType=VARCHAR},",
          "INTRODUCTION = #{introduction,jdbcType=VARCHAR},",
          "LINKMAN = #{linkman,jdbcType=VARCHAR},",
          "MOBILE = #{mobile,jdbcType=VARCHAR},",
          "POSITIVE_ID_POTO_PIC_PATH = #{positiveIdPotoPicPath,jdbcType=VARCHAR},",
          "PUBLIC_ACCOUNT = #{publicAccount,jdbcType=VARCHAR},",
          "QQ = #{qq,jdbcType=VARCHAR},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "SELLER_ADDRESS = #{sellerAddress,jdbcType=VARCHAR},",
          "SELLER_CODE = #{sellerCode,jdbcType=VARCHAR},",
          "SELLER_NAME = #{sellerName,jdbcType=VARCHAR},",
          "SELLER_TYPE = #{sellerType,jdbcType=INTEGER},",
          "TELEPHONE = #{telephone,jdbcType=VARCHAR},",
          "WECHAT = #{wechat,jdbcType=VARCHAR},",
          "POSITION_X = #{positionX,jdbcType=VARCHAR},",
          "POSITION_Y = #{positionY,jdbcType=VARCHAR}",
        "where SELLER_ID = #{sellerId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Seller record);
}