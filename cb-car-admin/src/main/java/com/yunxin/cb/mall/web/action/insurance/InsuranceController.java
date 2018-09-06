package com.yunxin.cb.mall.web.action.insurance;

import com.yunxin.cb.insurance.entity.InsuranceLog;
import com.yunxin.cb.insurance.entity.InsuranceOrder;
import com.yunxin.cb.insurance.meta.InsuranceOrderState;
import com.yunxin.cb.insurance.service.IInsuranceLogService;
import com.yunxin.cb.insurance.service.IInsuranceOrderOffsiteService;
import com.yunxin.cb.insurance.service.IInsuranceOrderService;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @author wangteng
 */
@Controller
@RequestMapping(value = "/insurance")
public class InsuranceController {

    @Resource
    private IInsuranceOrderService iInsuranceOrderService;

    @Resource
    private IInsuranceOrderOffsiteService InsuranceOrderOffsiteService;
    @Resource
    private IInsuranceLogService iInsuranceLogService;
    @RequestMapping(value = "insurances", method = RequestMethod.GET)
    public String insurances() {
        return "insurance/insurances";
    }

    /**
     * 分页列表
     *
     * @param query
     * @return
     */
    @RequestMapping(value = "pageInsuranceOrder", method = RequestMethod.POST)
    @ResponseBody
    public Page<InsuranceOrder> pageInsuranceOrder(@RequestBody PageSpecification<InsuranceOrder> query) {
        /**查询时间格式化*/
        List<PageSpecification.FilterDescriptor> list = query.getFilter().getFilters();
        list.stream().forEach(p->{
            if(p.getField().equals("createTime")){
                if(p.getOperator().equals("gte")){
                    p.setValue(p.getValue()+" 00:00:00");
                }else{
                    p.setValue(p.getValue()+" 23:59:59");
                }
            }
        });
        Page<InsuranceOrder> page = iInsuranceOrderService.pageInsuranceOrder(query);
        return page;
    }

    /**
     * 获取详情
     *
     * @param orderId
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "insuranceOrderDetail", method = RequestMethod.GET)
    public String insuranceOrderDetail(@RequestParam("orderId") int orderId, ModelMap modelMap) throws Exception {
        InsuranceOrder InsuranceOrder = iInsuranceOrderService.getInsuranceOrderDetailById(orderId);
        modelMap.addAttribute("insuranceOrder", InsuranceOrder);

        modelMap.addAttribute("matterList", iInsuranceOrderService.findMatter(orderId));
        return "insurance/insuranceOrderDetail";
    }



    /**
     * 修改支付状态
     *
     * @param orderId
     * @param orderState
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "updInsuranceOrderState", method = RequestMethod.GET)
    public boolean updInsuranceOrderState(@RequestParam("orderId") int orderId, @RequestParam("orderState") InsuranceOrderState orderState, HttpServletRequest request) {
        return iInsuranceOrderService.updInsuranceOrderState(orderId, orderState,request);
    }
    /**
     * 获取异地投保详情
     *
     * @param orderId
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "toEditInsuranceOrderOffsite", method = RequestMethod.GET)
    public String toEditInsuranceOrderOffsite(@RequestParam("orderId") int orderId, ModelMap modelMap) throws Exception {
        InsuranceOrder InsuranceOrder = iInsuranceOrderService.getInsuranceOrderDetailById(orderId);
        modelMap.addAttribute("insuranceOrder", InsuranceOrder);
        modelMap.addAttribute("matterList", iInsuranceOrderService.findMatter(orderId));
        return "insurance/toEditInsuranceOrderOffsite";
    }
    /**
     * 修改异地投保
     *
     * @return
     */

    @RequestMapping(value = "editInsuranceOrderOffsite", method = RequestMethod.POST)
    public String editInsuranceOrderOffsite(@ModelAttribute("insuranceOrder") InsuranceOrder insuranceOrder) {
         InsuranceOrderOffsiteService.upInsuranceOrderOffsite(insuranceOrder.getInsuranceOrderOffsite());
        return "redirect:insuranceOrderDetail.do?orderId="+insuranceOrder.getOrderId();
    }

    @RequestMapping(value = "excelInsuranceOrder", method = RequestMethod.GET)
    public String excelInsuranceOrder(HttpServletResponse response) {
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

    /**
     * 保单打印
     * @param orderId
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "prints", method = RequestMethod.GET)
    public String prints(@RequestParam("orderId") int orderId, ModelMap modelMap) {
            modelMap.addAttribute("map", iInsuranceOrderService.insuranceOrder(orderId));
        return "insurance/orderDetailPrint";
    }


    @RequestMapping(value = "printsSurvey", method = RequestMethod.GET)
    public String printsSurvey(@RequestParam("orderId") int orderId, ModelMap modelMap) {
        InsuranceOrder insuranceOrder = iInsuranceOrderService.getInsuranceOrderDetailById(orderId);


        modelMap.addAttribute("insuranceOrder", insuranceOrder);
        modelMap.addAttribute("orderOffsite", insuranceOrder.getInsuranceOrderOffsite());

        return "insurance/leavePlacePinter";
    }

    @RequestMapping(value = "downloadPdf", method = RequestMethod.GET)
    public String downloadPdf(@RequestParam("orderId") int orderId, ModelMap modelMap, HttpServletResponse response) throws Exception {
        modelMap.addAttribute("map", iInsuranceOrderService.insuranceOrder(orderId));
//        response.setHeader("Content-Disposition", "attachment; filename=\"insurance-" + orderId + ".pdf\"");
//        response.setContentType("application/octet-stream;charset=UTF-8");
//        ITextRenderer renderer = new ITextRenderer();
//        ITextFontResolver fontResolver = renderer.getFontResolver();
//        fontResolver.addFont("F:\\vistaBold.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//        OutputStream os = response.getOutputStream();
//        try {
//            String htmlstr = HttpsUtils.doGet("http://localhost:8080/admin/insurance/prints.do?orderId=" + orderId);//HttpHandler.sendGet只是单纯获得指定网页的html字符串内容
//            renderer.setDocumentFromString(htmlstr);
//            renderer.layout();
//            renderer.createPDF(os);
//            os.flush();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            os.close();
//        }
        return "insurance/orderDetailPrint";
    }

    /**
     * 操作日志
     * @return
     */
    @RequestMapping(value = "insuranceLog", method = RequestMethod.GET)
    public String insuranceLog() {
        return "insurance/insuranceLog";
    }

    /**
     * 操作日志分页
     * @param query
     * @return
     */
    @RequestMapping(value = "pageInsuranceLog", method = RequestMethod.POST)
    @ResponseBody
    public Page<InsuranceLog> pageInsuranceLog(@RequestBody PageSpecification<InsuranceLog> query) {
        Page<InsuranceLog> page = iInsuranceLogService.pageInsuranceLog(query);
        return page;
    }

    /**
     * 操作日志详情
     *
     * @param orderId
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "insuranceLogDetail", method = RequestMethod.GET)
    public String insuranceLogDetail(@RequestParam("orderId") int orderId, ModelMap modelMap) throws Exception {
        InsuranceOrder InsuranceOrder = iInsuranceOrderService.getInsuranceOrderDetailById(orderId);
        modelMap.addAttribute("insuranceOrder", InsuranceOrder);

        modelMap.addAttribute("matterList", iInsuranceOrderService.findMatter(orderId));
        return "insurance/insuranceLogDetail";
    }

}
