package com.yunxin.cb.mall.web.action.insurance;

import com.yunxin.cb.insurance.entity.InsuranceOrder;
import com.yunxin.cb.insurance.service.IInsuranceOrderService;
import com.yunxin.cb.mall.entity.meta.InsuranceOrderState;
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
    private IInsuranceOrderService iInsuranceOrderService;

    @RequestMapping(method = RequestMethod.GET)
    public String insurances() {
        return "insurance/insurances";
    }

    /**
     * 分页列表
     * @param query
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Page<InsuranceOrder> pageInsuranceOrder(@RequestBody PageSpecification<InsuranceOrder> query) {
        Page<InsuranceOrder> page = iInsuranceOrderService.pageInsuranceOrder(query);
        return page;
    }

    /**
     * 获取详情
     * @param orderId
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET)
    public String insuranceOrderDetail(@RequestParam("orderId") int orderId, ModelMap modelMap) throws Exception {
        InsuranceOrder InsuranceOrder= iInsuranceOrderService.getInsuranceOrderDetailById(orderId);
        modelMap.addAttribute("insuranceOrder",InsuranceOrder);
        modelMap.addAttribute("matterList",iInsuranceOrderService.findMatter(orderId));
        return "insurance/insuranceOrderDetail";
    }

    /**
     * 修改支付状态
     * @param orderId
     * @param orderState
     * @return
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public boolean updInsuranceOrderState(@RequestParam("orderId") int orderId, @RequestParam("orderState") InsuranceOrderState orderState) {
        return iInsuranceOrderService.updInsuranceOrderState(orderId,orderState);
    }
}
