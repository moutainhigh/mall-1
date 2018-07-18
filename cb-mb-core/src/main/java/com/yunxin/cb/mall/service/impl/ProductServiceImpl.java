package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.mapper.ProductMapper;
import com.yunxin.cb.mall.service.ProductService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @title: 货品表 服务实现类
 * @auther: eleven
 * @date: 2018/7/18 18:09
 */
@Service
public class ProductServiceImpl implements ProductService {

	private static final Log log = LogFactory.getLog(ProductServiceImpl.class);
	
	@Resource
	private ProductMapper productMapper;

}
