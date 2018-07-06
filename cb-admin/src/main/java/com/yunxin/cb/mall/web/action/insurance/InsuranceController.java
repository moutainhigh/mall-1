package com.yunxin.cb.mall.web.action.insurance;

import com.yunxin.cb.insurance.entity.InsuranceOrder;
import com.yunxin.cb.insurance.service.IInsuranceOrderService;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author wangteng
 *
 */
@Controller
@RequestMapping(value = "/insurance")
public class InsuranceController {

    @Resource
    private IInsuranceOrderService IInsuranceOrderService;

    @RequestMapping(method = RequestMethod.GET)
    public String insurances() {
        return "insurance/insurances";
    }
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Page<InsuranceOrder> pageInsuranceOrder(@RequestBody PageSpecification<InsuranceOrder> query) {
        Page<InsuranceOrder> page = IInsuranceOrderService.pageInsuranceOrder(query);
        return page;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String insuranceOrderDetail(@RequestParam("orderId") int orderId, ModelMap modelMap) throws Exception {
        InsuranceOrder InsuranceOrder= IInsuranceOrderService.getInsuranceOrderDetailById(orderId);
        modelMap.addAttribute("insuranceOrder",InsuranceOrder);
        return "insurance/insuranceOrderDetail";
    }
}
