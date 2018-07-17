package com.yunxin.cb.mall.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommodityMapper {
	
	@Select("SELECT * from commodity")
	public List<Object> listCommodity(@Param("demandId") Long demandId, @Param("startNo") Integer pageNo, @Param("pageSize") Integer pageSize);
}