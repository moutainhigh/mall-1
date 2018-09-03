package com.yunxin.cb.mall.mapper;

import com.yunxin.cb.mall.entity.FloorAdvert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface FloorAdvertMapper {

    @Select({
            "select",
            "ADVERT_ID, FLOOR_ID, SORT_ORDER",
            "from floor_advert",
            "where FLOOR_ID = #{floorId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="ADVERT_ID", property="advertId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="FLOOR_ID", property="floorId", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="SORT_ORDER", property="sortOrder", jdbcType=JdbcType.INTEGER)
    })
    List<FloorAdvert> selectByFloorId(Integer floorId);
}
