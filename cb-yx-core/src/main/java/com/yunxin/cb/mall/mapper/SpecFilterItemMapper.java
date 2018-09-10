package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.SpecFilterItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface SpecFilterItemMapper {

    @Select({
            "select *",
            "from spec_filter_item",
            "where FILTER_ID = #{filterId,jdbcType=INTEGER}",
            "order by",
            "SORT_ORDER ASC"
    })
    @Results(id = "filterItemList",value = {
            @Result(column="ITEM_ID", property="itemId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="ITEM_VALUE", property="itemValue", jdbcType=JdbcType.VARCHAR),
            @Result(column="SORT_ORDER", property="sortOrder", jdbcType=JdbcType.INTEGER)
    })
    List<SpecFilterItem> selectListByFilterId(Integer filterId);

}