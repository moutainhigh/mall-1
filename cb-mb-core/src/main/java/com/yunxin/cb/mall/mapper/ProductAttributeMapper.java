package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.ProductAttribute;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface ProductAttributeMapper {
    @Delete({
        "delete from product_attribute",
        "where PRO_ATTR_ID = #{proAttrId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer proAttrId);

    @Insert({
        "insert into product_attribute (PRO_ATTR_ID, ATTRIBUTE_ID, ",
        "PRODUCT_ID)",
        "values (#{proAttrId,jdbcType=INTEGER}, #{attributeId,jdbcType=INTEGER}, ",
        "#{productId,jdbcType=INTEGER})"
    })
    int insert(ProductAttribute record);

    @Select({
        "select",
        "PRO_ATTR_ID, ATTRIBUTE_ID, PRODUCT_ID",
        "from product_attribute",
        "where PRO_ATTR_ID = #{proAttrId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="PRO_ATTR_ID", property="proAttrId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ATTRIBUTE_ID", property="attributeId", jdbcType=JdbcType.INTEGER),
        @Result(column="PRODUCT_ID", property="productId", jdbcType=JdbcType.INTEGER)
    })
    ProductAttribute selectByPrimaryKey(Integer proAttrId);

    @Select({
        "select",
        "PRO_ATTR_ID, ATTRIBUTE_ID, PRODUCT_ID",
        "from product_attribute"
    })
    @Results({
        @Result(column="PRO_ATTR_ID", property="proAttrId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ATTRIBUTE_ID", property="attributeId", jdbcType=JdbcType.INTEGER),
        @Result(column="PRODUCT_ID", property="productId", jdbcType=JdbcType.INTEGER)
    })
    List<ProductAttribute> selectAll();

    @Select({
            "select",
            "PRO_ATTR_ID, ATTRIBUTE_ID, PRODUCT_ID",
            "from product_attribute where PRODUCT_ID = #{productId,jdbcType=INTEGER} order by PRO_ATTR_ID"
    })
    @Results({
            @Result(column="PRO_ATTR_ID", property="proAttrId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="ATTRIBUTE_ID", property="attributeId", jdbcType=JdbcType.INTEGER),
            @Result(column="PRODUCT_ID", property="productId", jdbcType=JdbcType.INTEGER),
            @Result(column="ATTRIBUTE_ID", property="attribute", jdbcType=JdbcType.INTEGER,
                    one = @One(select = "com.yunxin.cb.mall.mapper.AttributeMapper.selectByPrimaryKey"))
            })
    List<ProductAttribute> selectAllByProductId(Integer productId);

    @Update({
        "update product_attribute",
        "set ATTRIBUTE_ID = #{attributeId,jdbcType=INTEGER},",
          "PRODUCT_ID = #{productId,jdbcType=INTEGER}",
        "where PRO_ATTR_ID = #{proAttrId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ProductAttribute record);
}