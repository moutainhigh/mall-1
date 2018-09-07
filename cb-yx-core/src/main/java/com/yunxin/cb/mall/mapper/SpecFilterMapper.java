package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.SpecFilter;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface SpecFilterMapper {

    @Select({
            "select *",
            "from spec_filter",
            "where",
            "ENABLED = 1",
            "order by",
            "SORT_ORDER ASC"
    })
    @Results(id = "specFilterDetail", value = {
            @Result(column="FILTER_ID", property="filterId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="FILTER_NAME", property="filterName", jdbcType=JdbcType.VARCHAR),
            @Result(column="SORT_ORDER", property="sortOrder", jdbcType=JdbcType.INTEGER),
            @Result(column="ENABLED", property="enabled", jdbcType=JdbcType.BOOLEAN),
            @Result(column = "FILTER_ID", property = "filterItems", javaType = List.class,
                    many = @Many(select = "com.yunxin.cb.mall.dao.SpecFilterItemMapper.selectListByFilterId"))
    })
    List<SpecFilter> selectEnableAll();

}