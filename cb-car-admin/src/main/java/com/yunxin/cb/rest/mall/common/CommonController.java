/**
 * 
 */
package com.yunxin.cb.rest.mall.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author tanggangyi
 * 
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController {

	private static Logger logger = LoggerFactory.getLogger(CommonController.class);

	@RequestMapping(value = "success", method = RequestMethod.GET)
	public String success() {
		return "common/success";
	}

	@RequestMapping(value = "failure", method = RequestMethod.GET)
	public String failure(@ModelAttribute("reurl") String reurl, @ModelAttribute("msgTitle") String msgTitle, @ModelAttribute("msgContent") String msgContent, ModelMap mod) {
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
