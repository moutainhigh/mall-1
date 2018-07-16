package com.yunxin.cb.mall.web.action.insurance;

import com.yunxin.cb.insurance.entity.InsuranceOrder;
import com.yunxin.cb.insurance.entity.InsuranceOrderInsured;
import com.yunxin.cb.insurance.entity.InsuranceOrderPolicyholder;
import com.yunxin.cb.insurance.meta.InsuranceOrderState;
import com.yunxin.cb.insurance.service.IInsuranceOrderService;
import com.yunxin.cb.util.HttpsUtils;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.CalendarUtils;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @author wangteng
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
     *
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
     *
     * @param orderId
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET)
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
    @RequestMapping(method = RequestMethod.GET)
    public boolean updInsuranceOrderState(@RequestParam("orderId") int orderId, @RequestParam("orderState") InsuranceOrderState orderState) {
        return iInsuranceOrderService.updInsuranceOrderState(orderId, orderState);
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

    @RequestMapping(method = RequestMethod.GET)
    public String prints(@RequestParam("orderId") int orderId, ModelMap modelMap) {
        InsuranceOrder insuranceOrder = iInsuranceOrderService.getInsuranceOrderDetailById(orderId);

        if(insuranceOrder!=null){
            /**
             * 被保人
             */
            InsuranceOrderInsured insuranceOrderInsured= insuranceOrder.getInsuranceOrderInsured();
            /**
             * 投保人
             */
            InsuranceOrderPolicyholder insuranceOrderPolicyholder= insuranceOrder.getInsuranceOrderPolicyholder();
            Map<String,Object> map=new HashMap<String,Object>();
            SimpleDateFormat simpleDateFormats=new SimpleDateFormat("yyyy-MM-dd");

            if(null!=insuranceOrderInsured&& Hibernate.isInitialized(insuranceOrderInsured)){
                Date birthday=insuranceOrderInsured.getInsuredBirthday();
                String insuredBirthday= simpleDateFormats.format(birthday);
                String insuredCardPeriod= simpleDateFormats.format(insuranceOrderInsured.getInsuredCardPeriod());

                map.put("insurance_b_year",insuredBirthday.substring(0,4));
                map.put("insurance_b_month",insuredBirthday.substring(5,7));
                map.put("insurance_b_day",insuredBirthday.substring(8,10));

                if(null!=insuranceOrderInsured.getInsuredTel()&&!"".equals(insuranceOrderInsured.getInsuredTel())){
                    map.put("insurance_q_tel",insuranceOrderInsured.getInsuredTel().substring(0,4));
                    map.put("insurance_h_tel",insuranceOrderInsured.getInsuredTel().substring(4,7));
                }

                    try {
                       int age= CalendarUtils.getAge(birthday);
                        map.put("age",age);
                        if(null!=insuredCardPeriod){
                            map.put("insurance_p_year",insuredCardPeriod.substring(0,4));
                            map.put("insurance_p_month",insuredCardPeriod.substring(5,7));
                            map.put("insurance_p_day",insuredCardPeriod.substring(8,10));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }

            if(null!=insuranceOrderPolicyholder&&Hibernate.isInitialized(insuranceOrderPolicyholder)){

                Date birthday=insuranceOrderPolicyholder.getPolicyholderBirthday();
                String policyholderBirthday= simpleDateFormats.format(birthday);
                String policyholderCardPeriod= simpleDateFormats.format(insuranceOrderPolicyholder.getPolicyholderCardPeroid());
                map.put("policy_b_year",policyholderBirthday.substring(0,4));
                map.put("policy_b_month",policyholderBirthday.substring(5,7));
                map.put("policy_b_day",policyholderBirthday.substring(8,10));

                if(null!=insuranceOrderPolicyholder.getPolicyholderTel()&&!"".equals(insuranceOrderPolicyholder.getPolicyholderTel())){
                    map.put("policy_q_tel",insuranceOrderInsured.getInsuredTel().substring(0,4));
                    map.put("policy_h_tel",insuranceOrderInsured.getInsuredTel().substring(4,7));
                }
                if(null!=policyholderCardPeriod){
                    map.put("policy_p_year",policyholderCardPeriod.substring(0,4));
                    map.put("policy_p_month",policyholderCardPeriod.substring(5,7));
                    map.put("policy_p_day",policyholderCardPeriod.substring(8,10));
                }

            }

            /**
             * 受益人
             */
            Map<String,Object> maps= iInsuranceOrderService.insuranceOrder(orderId);
            if(null!=maps)
                map.put("beneficiaryList",maps);
            modelMap.addAttribute("map", map);
        }

        modelMap.addAttribute("insuranceOrder", insuranceOrder);
        //modelMap.addAttribute("matterList", iInsuranceOrderService.findMatter(orderId));
        return "insurance/orderDetailPrint";
    }


    @RequestMapping(method = RequestMethod.GET)
    public String printsSurvey(@RequestParam("orderId") int orderId, ModelMap modelMap) {
        InsuranceOrder insuranceOrder = iInsuranceOrderService.getInsuranceOrderDetailById(orderId);


        modelMap.addAttribute("insuranceOrder", insuranceOrder);
        modelMap.addAttribute("orderOffsite", insuranceOrder.getInsuranceOrderOffsite());

        return "insurance/leavePlacePinter";
    }

    @RequestMapping(method = RequestMethod.GET)
    public void downloadPdf(@RequestParam("orderId") int orderId, HttpServletResponse response) throws Exception {
        response.setHeader("Content-Disposition", "attachment; filename=\"insurance-" + orderId + ".pdf\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ITextRenderer renderer = new ITextRenderer();
        ITextFontResolver fontResolver = renderer.getFontResolver();
//        fontResolver.addFont("/Users/hehe/share/Fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        OutputStream os = response.getOutputStream();
        try {
            String htmlstr = HttpsUtils.doGet("http://localhost:8080/admin/insurance/prints.do?orderId=" + orderId);//HttpHandler.sendGet只是单纯获得指定网页的html字符串内容
            renderer.setDocumentFromString(htmlstr);
            renderer.layout();
            renderer.createPDF(os);
            os.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            os.close();
        }
    }
}
