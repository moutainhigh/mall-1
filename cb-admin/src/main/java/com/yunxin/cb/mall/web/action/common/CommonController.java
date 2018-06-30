/**
 * 
 */
package com.yunxin.cb.mall.web.action.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author tanggangyi
 * 
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController {

	@RequestMapping(value = "success", method = RequestMethod.GET)
	public String success() {
		return "common/success";
	}

	@RequestMapping(value = "failure", method = RequestMethod.GET)
	public String failure(String reurl,String msgTitle,String msgContent,ModelMap mod) {
		mod.put("reurl", reurl);
		mod.put("msgTitle", msgTitle);
		mod.put("msgContent", msgContent);
		return "common/failure";
	}

	@RequestMapping(value = "denial", method = RequestMethod.GET)
	public String denial() {
		return "common/denial";
	}

}
