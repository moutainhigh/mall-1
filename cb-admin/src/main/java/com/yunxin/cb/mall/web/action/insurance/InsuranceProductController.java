package com.yunxin.cb.mall.web.action.insurance;

import com.yunxin.cb.insurance.entity.*;
import com.yunxin.cb.insurance.meta.InsuranceOrderState;
import com.yunxin.cb.insurance.service.IInsuranceInformedMatterService;
import com.yunxin.cb.insurance.service.IInsuranceOrderService;
import com.yunxin.cb.insurance.service.IInsuranceProductService;
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
import java.util.*;


/**
 * @author likang
 */
@Controller
@RequestMapping(value = "/insuranceproduct")
public class InsuranceProductController {

    @Resource
    private IInsuranceProductService insuranceProductService;
    @Resource
    private IInsuranceInformedMatterService insuranceInformedMatterService;

    @RequestMapping(value = "insuranceproducts", method = RequestMethod.GET)
    public String insuranceproducts() {
        return "insuranceproduct/insuranceproducts";
    }

    /**
     * 分页列表
     *
     * @param query
     * @return
     */
    @RequestMapping(value = "pageInsuranceProduct", method = RequestMethod.POST)
    @ResponseBody
    public Page<InsuranceProduct> pageInsuranceProduct(@RequestBody PageSpecification<InsuranceProduct> query) {
        Page<InsuranceProduct> page = insuranceProductService.pageInsuranceProduct(query);
        return page;
    }

    /**
     * 加载事项列表(此处得优化)
     *
     * @param prodId
     * @return
     */
    @RequestMapping(value = "getmatterList")
    @ResponseBody
    public List<InsuranceInformedMatter> getmatterList(@RequestParam("matterDescription") String matterDescription,@RequestParam("prodId") int prodId, ModelMap modelMap) {

        List<InsuranceInformedMatter> list = insuranceInformedMatterService.getListByName(matterDescription);
        InsuranceProduct insuranceProduct = insuranceProductService.getInsuranceProductById(prodId);
        Set<InsuranceInformedMatter> insuranceInformedMatters = insuranceProduct.getInsuranceInformedMatters();
        Set<Integer> setId=new HashSet<Integer>();
        /**
         * 筛选出已经存在的事项，(后期再优化)
         */
        for (InsuranceInformedMatter matters:insuranceInformedMatters) {
            setId.add(matters.getMatterId());
        }
        List<InsuranceInformedMatter> result=new ArrayList<InsuranceInformedMatter>();
        for (InsuranceInformedMatter insuranceInformedMatter : list) {
             if(!setId.contains(insuranceInformedMatter.getMatterId())){
                 result.add(insuranceInformedMatter);
             }
        }
        return result;
    }

    /**
     * 加载事项列表
     *
     * @param prodId
     * @return
     */
    @RequestMapping(value = "toaddMatter")
    public String toaddMatter(@RequestParam("prodId") int prodId, ModelMap modelMap) {
        modelMap.addAttribute("prodId", prodId);
        return "insuranceproduct/getmatterList";
    }

    /**
     * 获取详情
     *
     * @param prodId
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "toEditProduct", method = RequestMethod.GET)
    public String toEditProduct(@RequestParam("prodId") int prodId, ModelMap modelMap) throws Exception {
        InsuranceProduct insuranceProduct = insuranceProductService.getInsuranceProductById(prodId);
        modelMap.addAttribute("insuranceProduct", insuranceProduct);
        return "insuranceproduct/editinsuranceproduct";
    }

    /**
     * @param insuranceProduct
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "toAddProduct", method = RequestMethod.GET)
    public String toAddProduct(@ModelAttribute("insuranceProduct") InsuranceProduct insuranceProduct, ModelMap modelMap) {
        modelMap.addAttribute("insuranceProduct", insuranceProduct);
        return "insuranceproduct/addinsuranceproduct";
    }


    /**
     * @param insuranceProduct
     * @return
     */
    @RequestMapping(value = "addInsuranceProduct", method = RequestMethod.POST)
    public String addInsuranceProduct(@ModelAttribute("InsuranceProduct") InsuranceProduct insuranceProduct) {
        insuranceProduct.setCreateTime(new Date());
        insuranceProductService.addInsuranceProduct(insuranceProduct);
        return "redirect:../common/success.do?reurl=insuranceproduct/insuranceproducts.do";
    }


    /**
     * @param insuranceProduct
     * @return
     */
    @RequestMapping(value = "updateInsuranceProduct", method = RequestMethod.POST)
    public String updateInsuranceProduct(@ModelAttribute("insuranceProduct") InsuranceProduct insuranceProduct) {
        insuranceProductService.updateInsuranceProduct(insuranceProduct);
        return "redirect:../common/success.do?reurl=insuranceproduct/insuranceproducts.do";
    }

    /**
     * @param prodId
     * @return
     */
    @RequestMapping(value = "removeById", method = RequestMethod.GET)
    @ResponseBody
    public boolean removeById(@RequestParam("prodId") int prodId) {
        try {
            insuranceProductService.removeById(prodId);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * @param prodId
     * @return
     */
    @RequestMapping(value = "removeMetterById", method = RequestMethod.GET)
    @ResponseBody
    public boolean removeMetterById(@RequestParam("prodId") int prodId, @RequestParam("matterId") int matterId) {
        try {
            insuranceProductService.removeInsuranceProductMatter(prodId, matterId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param prodId
     * @param matterId
     * @return
     */
    @RequestMapping(value = "addMetterById", method = RequestMethod.GET)
    @ResponseBody
    public boolean addMetterById(@RequestParam("prodId") int prodId, @RequestParam("matterId") int matterId) {
        try {
            insuranceProductService.addInsuranceProductMatter(prodId, matterId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
