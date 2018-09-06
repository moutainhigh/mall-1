package com.yunxin.cb.mall.web.action.config;

import com.yunxin.cb.console.service.ILogsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 
 * @author x001393
 *
 */
@Controller
@RequestMapping(value = "/config")
public class SystemConfigController {


	@Resource
	private ILogsService systemLogsService;
	
	@RequestMapping(value="systemConfigs")
	public String systemConfigs(){
		return "config/systemConfigs";
	}



	
}
