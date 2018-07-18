package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.mapper.SpecMapper;
import com.yunxin.cb.mall.service.SpecService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @title: 规格表 服务实现类
 * @auther: eleven
 * @date: 2018/7/18 18:09
 */
@Service
public class SpecServiceImpl implements SpecService {

	private static final Log log = LogFactory.getLog(SpecServiceImpl.class);
	
	@Resource
	private SpecMapper specMapper;

}
