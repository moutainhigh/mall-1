package com.yunxin.cb.mall.web.action.customer;

import com.yunxin.cb.console.service.ILogsService;
import com.yunxin.cb.mall.entity.DeliveryAddress;
import com.yunxin.cb.mall.query.DeliveryAddressQuery;
import com.yunxin.cb.mall.service.IAddressService;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.mall.entity.DeliveryAddress;
import com.yunxin.cb.mall.query.DeliveryAddressQuery;
import com.yunxin.cb.mall.service.IAddressService;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.console.service.ILogsService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 
 * @author x001393
 *
 */
@Controller
@RequestMapping(value = "/deliveryAddress")
public class DeliveryAddressController {
	
	@Resource
	private IAddressService deliveryAddressService;
	
	@Resource
	private ICustomerService customerService;
	
	@Resource
	private ILogsService systemsServicelog;
	
	@RequestMapping(value="deliveryAddrs")
	public String deliveryAddrs(ModelMap modelMap,int customerId){
		modelMap.addAttribute("customerId", customerId);
		return "deliveryAddress/deliveryAddrs";
	}
	
	@RequestMapping(value = "pageDeliveryAddresses", method = RequestMethod.POST)
	@ResponseBody
	public Page<DeliveryAddress> pageDeliveryAddresses(@RequestBody DeliveryAddressQuery query) {
//		return deliveryAddressService.pageDeliveryAddresses(query);

		return null;
	}

	@RequestMapping(value = "toAddDeliveryAddr", method = RequestMethod.GET)
	public String toAddDeliveryAddr(@ModelAttribute("deliveryAddress") DeliveryAddress deliveryAddress,@RequestParam("customerId") int customerId,ModelMap modelMap,HttpServletRequest request) {
		deliveryAddress.setCustomer(customerService.getCustomerById(customerId));
		modelMap.addAttribute("deliveryAddress", deliveryAddress);
		return "deliveryAddress/addDeliveryAddr";	
	}

	@RequestMapping(value = "addDeliveryAddr", method = RequestMethod.POST)
	public String addDeliveryAddr(@Valid @ModelAttribute("deliveryAddress") DeliveryAddress deliveryAddress,ModelMap modelMap,
			HttpServletRequest request) {
		deliveryAddressService.addDeliveryAddress(deliveryAddress);
		//增加日志
//		systemsServicelog.log(request,"增加了收货地址,deliveryAddrNameName=" + deliveryAddress.getDeliveryAddrNameName());
		modelMap.addAttribute("customerId", deliveryAddress.getCustomer().getCustomerId());
		return "redirect:../common/success.do?reurl=deliveryAddress/deliveryAddrs.do?customerId="+deliveryAddress.getCustomer().getCustomerId();
	}
	
	@RequestMapping(value = "toEditDeliveryAddr", method = RequestMethod.GET)
	public String toEditDeliveryAddr(@RequestParam("deliveryAddressId") int deliveryAddressId, ModelMap modelMap) {
		modelMap.addAttribute("deliveryAddress", deliveryAddressService.getDeliveryAddressById(deliveryAddressId));
		return "deliveryAddress/editDeliveryAddr";
	}
	
	@RequestMapping(value = "editDeliveryAddr", method = RequestMethod.POST)
	public String editDeliveryAddr(@Valid @ModelAttribute("deliveryAddress") DeliveryAddress deliveryAddress,ModelMap modelMap
			,HttpServletRequest request) {
		deliveryAddressService.updateDeliveryAddress(deliveryAddress);
		//增加日志
		systemsServicelog.log(request,"修改了收货地址,deliveryAddressId=" + deliveryAddress.getAddressId());
		modelMap.addAttribute("customerId", deliveryAddress.getCustomer().getCustomerId());
		return "redirect:../common/success.do?reurl=member/customers.do";
	}
	
	@RequestMapping(value = "removeDeliveryAddressById", method = RequestMethod.GET)
	@ResponseBody
	public String removeDeliveryAddressById(@RequestParam("deliveryAddressId") int deliveryAddressId
			,HttpServletRequest request) {
		deliveryAddressService.removeDeliveryAddressById(deliveryAddressId);
		//增加日志
		systemsServicelog.log(request, "删除了收货地址,deliveryAddressId=" + deliveryAddressId);
		return "success";
	}
	
	@RequestMapping(value = "setDefaultAddrByCustomerIdAndAddrId", method = RequestMethod.GET)
	@ResponseBody
	public String setDefaultAddrByCustomerIdAndAddrId(@RequestParam("customerId") int customerId,int deliveryAddressId
			,HttpServletRequest request) {
		deliveryAddressService.setDefaultAddrByCustomerIdAndAddrId(customerId,deliveryAddressId);
		/*//增加日志
		systemsServicelog.log(request, "设置了收货地址" + deliveryAddressId);*/
		return "success";
	}
	

}

