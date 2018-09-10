package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.mapper.AttributeMapper;
import com.yunxin.cb.mall.service.AttributeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @title: 属性表 服务实现类
 * @auther: eleven
 * @date: 2018/7/18 18:08
 */
@Service
public class AttributeServiceImpl implements AttributeService {

	private static final Log log = LogFactory.getLog(AttributeServiceImpl.class);
	
	@Resource
	private AttributeMapper attributeMapper;

}
