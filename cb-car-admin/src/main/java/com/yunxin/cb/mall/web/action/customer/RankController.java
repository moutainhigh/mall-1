package com.yunxin.cb.mall.web.action.customer;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.Rank;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.mall.service.IRankService;
import com.yunxin.core.util.LogicUtils;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.Rank;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.mall.service.IRankService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author z001075
 *
 */
@Controller
@RequestMapping(value = "/customer")
public class RankController {
	
	@Resource
	private ICustomerService customerService;
	
	@Resource
	private IRankService rankService;
	
	@RequestMapping(value = "ranks")
	public String ranks(ModelMap modelMap) {
		modelMap.addAttribute("ranks",rankService.getAllRanks());
		return "customer/ranks";
	}
	
	@RequestMapping(value = "toAddRank", method = RequestMethod.GET)
	public String toAddRank(@ModelAttribute("rank") Rank rank) {
		return "customer/addRank";
	}

	@RequestMapping(value = "addRank", method = RequestMethod.POST)
	public String addRank(@Valid @ModelAttribute("rank") Rank rank,HttpServletRequest request) {
		rankService.addRank(rank);
		return "redirect:../common/success.do?reurl=customer/ranks.do";
	}
	
	@RequestMapping(value = "toEditRank", method = RequestMethod.GET)
	public String toEditRank(@RequestParam("rankId") int rankId, ModelMap modelMap) {
		modelMap.addAttribute("rank", rankService.getRankById(rankId));
		return "customer/editRank";
	}

	@RequestMapping(value = "editRank", method = RequestMethod.POST)
	public String editRank(@Valid @ModelAttribute("rank") Rank rank,HttpServletRequest request) {
		rankService.updateRank(rank);
		return "redirect:../common/success.do?reurl=customer/ranks.do";
	}

	@RequestMapping(value = "removeRankById", method = RequestMethod.GET)
	@ResponseBody
	public boolean removeRankById(@RequestParam("rankId") int rankId,HttpServletRequest request) {
		List<Customer> customers=customerService.findByRankId(rankId);
		if(LogicUtils.isNotNullAndEmpty(customers)){
			return false;
		}
		try{
			rankService.removeRankById(rankId);
			return true;
		}catch (Exception e){
			return false;
		}
	}
	
	@RequestMapping(value = "setDefaultRankByRankId", method = RequestMethod.GET)
	@ResponseBody
	public boolean setDefaultRankByRankId(@RequestParam("rankId") int rankId) {
		try{
			rankService.setDefaulRankByRankId(rankId);
			return true;
		}catch (Exception e){
			return false;
		}

	}

}
