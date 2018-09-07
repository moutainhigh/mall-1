package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.CommodityCategory;
import com.yunxin.cb.mall.mapper.CommodityCategoryMapper;
import com.yunxin.cb.mall.service.CommodityCategoryService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @title: 商品分类关联表 服务实现类
 * @auther: eleven
 * @date: 2018/7/18 18:08
 */
@Service
public class CommodityCategoryServiceImpl implements CommodityCategoryService {

	private static final Log log = LogFactory.getLog(CommodityCategoryServiceImpl.class);
	
	@Resource
	private CommodityCategoryMapper commodityCategoryMapper;

	@Override
	public List<CommodityCategory> selectByCategoryId(Integer categoryId) {
		return commodityCategoryMapper.selectByCategoryId(categoryId);
	}
}
