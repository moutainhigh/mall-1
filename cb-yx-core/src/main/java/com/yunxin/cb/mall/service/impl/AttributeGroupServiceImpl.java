package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.mapper.AttributeGroupMapper;
import com.yunxin.cb.mall.service.AttributeGroupService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @title: 属性组表 服务实现类
 * @auther: eleven
 * @date: 2018/7/18 18:04
 */
@Service
public class AttributeGroupServiceImpl implements AttributeGroupService {

	private static final Log log = LogFactory.getLog(AttributeGroupServiceImpl.class);
	
	@Resource
	private AttributeGroupMapper attributeGroupMapper;

}
