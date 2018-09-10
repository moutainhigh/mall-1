package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.mapper.ProductAttributeMapper;
import com.yunxin.cb.mall.service.ProductAttributeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @title: 货品属性表 服务实现类
 * @auther: eleven
 * @date: 2018/7/18 18:08
 */
@Service
public class ProductAttributeServiceImpl implements ProductAttributeService {

	private static final Log log = LogFactory.getLog(ProductAttributeServiceImpl.class);
	
	@Resource
	private ProductAttributeMapper productAttributeMapper;

}
