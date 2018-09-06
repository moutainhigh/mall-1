package com.yunxin.cb.mall.web.action.customer;

import com.yunxin.cb.mall.entity.Integral;
import com.yunxin.cb.mall.service.IIntegralService;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.cb.mall.entity.Integral;
import com.yunxin.cb.mall.service.IIntegralService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/customer")
public class IntegralController {
	@Resource
	private IIntegralService integralService;
	
	@RequestMapping(value="integrals")
	public String integrals(ModelMap modelMap) {
		return "customer/integrals";
	}
	
	@RequestMapping(value = "pageIntegrals", method = RequestMethod.POST)
	@ResponseBody
	public Page<Integral> pageIntegrals(@RequestBody PageSpecification<Integral> query) {
		return integralService.pageIntegrals(query);
	}

}
