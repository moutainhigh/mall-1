package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.mapper.CommoditySpecMapper;
import com.yunxin.cb.mall.service.CommoditySpecService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @title: 商品规格关联表 服务实现类
 * @auther: eleven
 * @date: 2018/7/18 18:07
 */
@Service
public class CommoditySpecServiceImpl implements CommoditySpecService {

	private static final Log log = LogFactory.getLog(CommoditySpecServiceImpl.class);
	
	@Resource
	private CommoditySpecMapper commoditySpecMapper;

}
