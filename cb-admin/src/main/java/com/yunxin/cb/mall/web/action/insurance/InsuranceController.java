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
import javax.servlet.http.HttpServletResponse;


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
    @RequestMapping(value="excelInsuranceOrder",method = RequestMethod.GET)
    public String excelInsuranceOrder(HttpServletResponse response){
//        String title = "保单";
//        String[] rowsName = new String[]{"序号","保单编号","合同编号"};
//        List<Object[]> dataList = new ArrayList<Object[]>();
//        Object[] objs = null;
//        Page<InsuranceOrder> page = iInsuranceOrderService.pageInsuranceOrder(new PageSpecification<InsuranceOrder>());
//        for (int i = 0; i < page.getContent().size(); i++) {
//            InsuranceOrder insuranceOrder = page.getContent().get(i);
//            objs = new Object[rowsName.length];
//            objs[0] = i;
//            objs[1] = insuranceOrder.getOrderCode();
//            objs[2] = insuranceOrder.getContractNo();
//            dataList.add(objs);
//        }
//        ExportExcel exportExcel = new ExportExcel(title, rowsName,dataList,response);
//        try {
//            exportExcel.export();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return "redirect:insurances.do";
    }
    @RequestMapping(method = RequestMethod.GET)
    public String prints(@RequestParam("orderId") int orderId,ModelMap modelMap) {
        InsuranceOrder InsuranceOrder= iInsuranceOrderService.getInsuranceOrderDetailById(orderId);
        modelMap.addAttribute("insuranceOrder",InsuranceOrder);
        modelMap.addAttribute("matterList",iInsuranceOrderService.findMatter(orderId));
        return "insurance/orderDetailPrint";
    }
}
