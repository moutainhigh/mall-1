/**
 * 
 */
package com.yunxin.cb.mall.web.action.console;

import com.yunxin.cb.console.service.ISecurityService;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.cb.console.service.ISecurityService;
import com.yunxin.cb.security.SecurityConstants;
import org.slf4j.Logger; import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;

/**
 * @author ThinkPad
 * 
 */
@Controller
@RequestMapping(value = "/console")
@SessionAttributes({ SecurityConstants.LOGIN_SESSION })
public class MainController {

	protected static Logger logger = LoggerFactory.getLogger(MainController.class);

	@Resource
	private ISecurityService securityService;


}
