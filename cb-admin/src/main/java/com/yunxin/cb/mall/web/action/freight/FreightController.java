package com.yunxin.cb.mall.web.action.freight;

import com.yunxin.cb.console.service.ILogsService;
import com.yunxin.cb.mall.entity.Freight;
import com.yunxin.cb.mall.query.FreightQuery;
import com.yunxin.cb.mall.service.IFreightService;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.LogicUtils;
import com.yunxin.cb.mall.entity.Freight;
import com.yunxin.cb.mall.query.FreightQuery;
import com.yunxin.cb.mall.service.IFreightService;
import com.yunxin.cb.console.service.ILogsService;
import com.yunxin.cb.security.SecurityConstants;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author qulei
 * 
 */
@Controller
@RequestMapping(value = "/freight")
@SessionAttributes({ SecurityConstants.LOGIN_SESSION })
public class FreightController {

	@Resource
	private IFreightService freightService;
	
	@Resource
	private ILogsService logsService;

	@RequestMapping(value = "freightList")
	public String brands(FreightQuery freightQuery , ModelMap modelMap) {
		if (freightQuery.getFreightId() > 0) {
			modelMap.put("parentId", freightQuery.getFreightId());
			Freight parent = freightService.getFreightById(freightQuery.getFreightId());
			modelMap.put("parent", parent);
		}
		return "freight/freightList";
	}

	@RequestMapping(value = "pageFreight", method = RequestMethod.POST)
	@ResponseBody
	public Page<Freight> pageFreight(@RequestBody PageSpecification<Freight> freightQuery) {
		return freightService.pageFreight(freightQuery);
	}
	
	@RequestMapping(value = "editFreight")
	@ResponseBody
	public String editFreight(@RequestParam("codes") String[] codes, @RequestParam("prices") Float[] prices,HttpServletRequest request) {
		if(!LogicUtils.isNullOrEmpty(codes)&&!LogicUtils.isNullOrEmpty(prices)){
			for(int i=0;i<codes.length;i++){
				Freight freight  = new Freight();
				freight.setAreaCode(codes[i]);
//				freight.setPrice(prices[i]);
				freightService.updatefreight(freight);
				logsService.log(request, "修改运费信息,freightId="+freight.getFreightId());
			}			
			return "success";
		}else{
			return "failure";
		}
	}
	
	@RequestMapping(value = "listFreight")
	@ResponseBody
	public Page<Freight> listFreight(@RequestBody FreightQuery freightQuery ,ModelMap modelMap) {
//		if(freightQuery.getFreightId()>0){
//			Freight pf=freightService.getFreightById(freightQuery.getFreightId());
//			modelMap.put("parent", pf);
//			String areaCode=pf.getAreaCode();
//			if(areaCode.endsWith("0000")){
//				areaCode=areaCode.substring(0,2)+"__00";
//			}else if(areaCode.endsWith("00")){
//				areaCode=areaCode.substring(0,4)+"%";
//			}
//			return freightService.listFreightByParenCode(freightQuery,areaCode);
//		}else{
//			return freightService.listProvinceFreight(freightQuery);
//		}
		return null;
	}
}
