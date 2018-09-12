package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.Brand;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
@Mapper
public interface BrandMapper {
    @Delete({
            "delete from yx_brand",
            "where BRAND_ID = #{brandId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer brandId);

    @Insert({
            "insert into yx_brand (BRAND_ID, BRAND_NO, ",
            "BRAND_NAME, BRAND_EN_NAME, ",
            "BRAND_TITLE, BRAND_KEY, ",
            "WEBSITE, DESCRIPTION, ",
            "SEO_DESCRIPTION, SEO_KEY, ",
            "SEO_TITLE, DISPLAY, ENABLED, ",
            "HOT, SORT, BIG_PIC_PATH, ",
            "MEDIUM_PIC_PATH, SMALL_PIC_PATH, ",
            "CREATE_TIME, REMARK, ",
            "CATEGORY_ID)",
            "values (#{brandId,jdbcType=INTEGER}, #{brandNo,jdbcType=VARCHAR}, ",
            "#{brandName,jdbcType=VARCHAR}, #{brandEnName,jdbcType=VARCHAR}, ",
            "#{brandTitle,jdbcType=VARCHAR}, #{brandKey,jdbcType=VARCHAR}, ",
            "#{website,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
            "#{seoDescription,jdbcType=VARCHAR}, #{seoKey,jdbcType=VARCHAR}, ",
            "#{seoTitle,jdbcType=VARCHAR}, #{display,jdbcType=BIT}, #{enabled,jdbcType=BIT}, ",
            "#{hot,jdbcType=BIT}, #{sort,jdbcType=INTEGER}, #{bigPicPath,jdbcType=VARCHAR}, ",
            "#{mediumPicPath,jdbcType=VARCHAR}, #{smallPicPath,jdbcType=VARCHAR}, ",
            "#{createTime,jdbcType=TIMESTAMP}, #{remark,jdbcType=VARCHAR}, ",
            "#{categoryId,jdbcType=INTEGER})"
    })
    int insert(Brand record);

    @Select({
            "select",
            "BRAND_ID, BRAND_NO, BRAND_NAME, BRAND_EN_NAME, BRAND_TITLE, BRAND_KEY, WEBSITE, ",
            "DESCRIPTION, SEO_DESCRIPTION, SEO_KEY, SEO_TITLE, DISPLAY, ENABLED, HOT, SORT, ",
            "BIG_PIC_PATH, MEDIUM_PIC_PATH, SMALL_PIC_PATH, CREATE_TIME, REMARK, CATEGORY_ID",
            "from yx_brand",
            "where BRAND_ID = #{brandId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="BRAND_ID", property="brandId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="BRAND_NO", property="brandNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="BRAND_NAME", property="brandName", jdbcType=JdbcType.VARCHAR),
            @Result(column="BRAND_EN_NAME", property="brandEnName", jdbcType=JdbcType.VARCHAR),
            @Result(column="BRAND_TITLE", property="brandTitle", jdbcType=JdbcType.VARCHAR),
            @Result(column="BRAND_KEY", property="brandKey", jdbcType=JdbcType.VARCHAR),
            @Result(column="WEBSITE", property="website", jdbcType=JdbcType.VARCHAR),
            @Result(column="DESCRIPTION", property="description", jdbcType=JdbcType.VARCHAR),
            @Result(column="SEO_DESCRIPTION", property="seoDescription", jdbcType=JdbcType.VARCHAR),
            @Result(column="SEO_KEY", property="seoKey", jdbcType=JdbcType.VARCHAR),
            @Result(column="SEO_TITLE", property="seoTitle", jdbcType=JdbcType.VARCHAR),
            @Result(column="DISPLAY", property="display", jdbcType=JdbcType.BIT),
            @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
            @Result(column="HOT", property="hot", jdbcType=JdbcType.BIT),
            @Result(column="SORT", property="sort", jdbcType=JdbcType.INTEGER),
            @Result(column="BIG_PIC_PATH", property="bigPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="MEDIUM_PIC_PATH", property="mediumPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="SMALL_PIC_PATH", property="smallPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="CATEGORY_ID", property="categoryId", jdbcType=JdbcType.INTEGER)
    })
    Brand selectByPrimaryKey(Integer brandId);

    @Select({
            "select",
            "BRAND_ID, BRAND_NO, BRAND_NAME, BRAND_EN_NAME, BRAND_TITLE, BRAND_KEY, WEBSITE, ",
            "DESCRIPTION, SEO_DESCRIPTION, SEO_KEY, SEO_TITLE, DISPLAY, ENABLED, HOT, SORT, ",
            "BIG_PIC_PATH, MEDIUM_PIC_PATH, SMALL_PIC_PATH, CREATE_TIME, REMARK, CATEGORY_ID",
            "from yx_brand"
    })
    @Results({
            @Result(column="BRAND_ID", property="brandId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="BRAND_NO", property="brandNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="BRAND_NAME", property="brandName", jdbcType=JdbcType.VARCHAR),
            @Result(column="BRAND_EN_NAME", property="brandEnName", jdbcType=JdbcType.VARCHAR),
            @Result(column="BRAND_TITLE", property="brandTitle", jdbcType=JdbcType.VARCHAR),
            @Result(column="BRAND_KEY", property="brandKey", jdbcType=JdbcType.VARCHAR),
            @Result(column="WEBSITE", property="website", jdbcType=JdbcType.VARCHAR),
            @Result(column="DESCRIPTION", property="description", jdbcType=JdbcType.VARCHAR),
            @Result(column="SEO_DESCRIPTION", property="seoDescription", jdbcType=JdbcType.VARCHAR),
            @Result(column="SEO_KEY", property="seoKey", jdbcType=JdbcType.VARCHAR),
            @Result(column="SEO_TITLE", property="seoTitle", jdbcType=JdbcType.VARCHAR),
            @Result(column="DISPLAY", property="display", jdbcType=JdbcType.BIT),
            @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
            @Result(column="HOT", property="hot", jdbcType=JdbcType.BIT),
            @Result(column="SORT", property="sort", jdbcType=JdbcType.INTEGER),
            @Result(column="BIG_PIC_PATH", property="bigPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="MEDIUM_PIC_PATH", property="mediumPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="SMALL_PIC_PATH", property="smallPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="CATEGORY_ID", property="categoryId", jdbcType=JdbcType.INTEGER)
    })
    List<Brand> selectAll();

    @Update({
            "update yx_brand",
            "set BRAND_NO = #{brandNo,jdbcType=VARCHAR},",
            "BRAND_NAME = #{brandName,jdbcType=VARCHAR},",
            "BRAND_EN_NAME = #{brandEnName,jdbcType=VARCHAR},",
            "BRAND_TITLE = #{brandTitle,jdbcType=VARCHAR},",
            "BRAND_KEY = #{brandKey,jdbcType=VARCHAR},",
            "WEBSITE = #{website,jdbcType=VARCHAR},",
            "DESCRIPTION = #{description,jdbcType=VARCHAR},",
            "SEO_DESCRIPTION = #{seoDescription,jdbcType=VARCHAR},",
            "SEO_KEY = #{seoKey,jdbcType=VARCHAR},",
            "SEO_TITLE = #{seoTitle,jdbcType=VARCHAR},",
            "DISPLAY = #{display,jdbcType=BIT},",
            "ENABLED = #{enabled,jdbcType=BIT},",
            "HOT = #{hot,jdbcType=BIT},",
            "SORT = #{sort,jdbcType=INTEGER},",
            "BIG_PIC_PATH = #{bigPicPath,jdbcType=VARCHAR},",
            "MEDIUM_PIC_PATH = #{mediumPicPath,jdbcType=VARCHAR},",
            "SMALL_PIC_PATH = #{smallPicPath,jdbcType=VARCHAR},",
            "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
            "REMARK = #{remark,jdbcType=VARCHAR},",
            "CATEGORY_ID = #{categoryId,jdbcType=INTEGER}",
            "where BRAND_ID = #{brandId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Brand record);

    @Select({
            "select",
            "BRAND_ID, BRAND_NO, BRAND_NAME, BRAND_EN_NAME, BRAND_TITLE, BRAND_KEY, WEBSITE, ",
            "DESCRIPTION, SEO_DESCRIPTION, SEO_KEY, SEO_TITLE, DISPLAY, ENABLED, HOT, SORT, ",
            "BIG_PIC_PATH, MEDIUM_PIC_PATH, SMALL_PIC_PATH, CREATE_TIME, REMARK, CATEGORY_ID",
            "from yx_brand",
            "where hot = 1 and enabled = 1 and display = 1",
            "order by sort"
    })

    @Results({
            @Result(column="BRAND_ID", property="brandId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="BRAND_NO", property="brandNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="BRAND_NAME", property="brandName", jdbcType=JdbcType.VARCHAR),
            @Result(column="BRAND_EN_NAME", property="brandEnName", jdbcType=JdbcType.VARCHAR),
            @Result(column="BRAND_TITLE", property="brandTitle", jdbcType=JdbcType.VARCHAR),
            @Result(column="BRAND_KEY", property="brandKey", jdbcType=JdbcType.VARCHAR),
            @Result(column="WEBSITE", property="website", jdbcType=JdbcType.VARCHAR),
            @Result(column="DESCRIPTION", property="description", jdbcType=JdbcType.VARCHAR),
            @Result(column="SEO_DESCRIPTION", property="seoDescription", jdbcType=JdbcType.VARCHAR),
            @Result(column="SEO_KEY", property="seoKey", jdbcType=JdbcType.VARCHAR),
            @Result(column="SEO_TITLE", property="seoTitle", jdbcType=JdbcType.VARCHAR),
            @Result(column="DISPLAY", property="display", jdbcType=JdbcType.BIT),
            @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BIT),
            @Result(column="HOT", property="hot", jdbcType=JdbcType.BIT),
            @Result(column="SORT", property="sort", jdbcType=JdbcType.INTEGER),
            @Result(column="BIG_PIC_PATH", property="bigPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="MEDIUM_PIC_PATH", property="mediumPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="SMALL_PIC_PATH", property="smallPicPath", jdbcType=JdbcType.VARCHAR),
            @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
            @Result(column="CATEGORY_ID", property="categoryId", jdbcType=JdbcType.INTEGER)
    })
    List<Brand> selectHotBrand();
}