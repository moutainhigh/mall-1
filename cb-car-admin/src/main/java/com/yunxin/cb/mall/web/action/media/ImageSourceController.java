package com.yunxin.cb.mall.web.action.media;

import com.yunxin.cb.console.service.ILogsService;
import com.yunxin.core.util.CommonUtils;
import com.yunxin.cb.console.service.ILogsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/images")
public class ImageSourceController {
	

	@Resource
	private ILogsService logsService;
	
	@RequestMapping(value = "list")
	public String tolist(ModelMap modelMap) {
		return "imageSource/imageList";
	}
	

	
	@RequestMapping(value = "toAddImg")
	public String toAddImage(ModelMap modelMap,HttpServletRequest request) {
		String imgCode=CommonUtils.randomString(8,CommonUtils.RANDRULE.RAND_IGNORE);
		modelMap.addAttribute("imgCode",imgCode);
		return "imageSource/addImgSource";
	}

}
