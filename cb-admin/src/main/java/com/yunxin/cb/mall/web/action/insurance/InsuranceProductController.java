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
* @Description:    保险产品控制器
* @Author:         likang
* @CreateDate:     2018/7/17 21:01
*/
@Controller
@RequestMapping(value = "/insuranceproduct")
public class InsuranceProductController {

    @Resource
    private IInsuranceProductService insuranceProductService;
    @Resource
    private IInsuranceInformedMatterService insuranceInformedMatterService;

    /**
     * @Description:    跳转到保险产品页面
     * @Author:         likang
     * @CreateDate:     2018/7/17 21:02
     */
    @RequestMapping(value = "insuranceproducts", method = RequestMethod.GET)
    public String insuranceproducts() {
        return "insuranceproduct/insuranceproducts";
    }

    /**
     * 保险产品的分页列表
     * @author      likang
     * @param query
     * @return      org.springframework.data.domain.Page<com.yunxin.cb.insurance.entity.InsuranceProduct>
     * @exception
     * @date        2018/7/17 21:03
     */
    @RequestMapping(value = "pageInsuranceProduct", method = RequestMethod.POST)
    @ResponseBody
    public Page<InsuranceProduct> pageInsuranceProduct(@RequestBody PageSpecification<InsuranceProduct> query) {
        Page<InsuranceProduct> page = insuranceProductService.pageInsuranceProduct(query);
        return page;
    }

    /**
     * 加载事项列表
     * @author      likang
     * @param matterDescription
    * @param prodId
    * @param modelMap
     * @return      java.util.List<com.yunxin.cb.insurance.entity.InsuranceInformedMatter>
     * @exception
     * @date        2018/7/17 21:03
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
     * 跳转保险产品详情页面
     * @author      likang
     * @param prodId
    * @param modelMap
     * @return      java.lang.String
     * @exception
     * @date        2018/7/17 21:05
     */
    @RequestMapping(value = "toEditProduct", method = RequestMethod.GET)
    public String toEditProduct(@RequestParam("prodId") int prodId, ModelMap modelMap) throws Exception {
        InsuranceProduct insuranceProduct = insuranceProductService.getInsuranceProductById(prodId);
        modelMap.addAttribute("insuranceProduct", insuranceProduct);
        return "insuranceproduct/editinsuranceproduct";
    }

    /**
     * 跳转到保险产品添加页面
     * @author      likang
     * @param insuranceProduct
    * @param modelMap
     * @return      java.lang.String
     * @exception
     * @date        2018/7/17 21:05
     */
    @RequestMapping(value = "toAddProduct", method = RequestMethod.GET)
    public String toAddProduct(@ModelAttribute("insuranceProduct") InsuranceProduct insuranceProduct, ModelMap modelMap) {
        modelMap.addAttribute("insuranceProduct", insuranceProduct);
        return "insuranceproduct/addinsuranceproduct";
    }


    /**
     * 添加保险产品
     * @author      likang
     * @param insuranceProduct
     * @return      java.lang.String
     * @exception
     * @date        2018/7/17 21:06
     */
    @RequestMapping(value = "addInsuranceProduct", method = RequestMethod.POST)
    public String addInsuranceProduct(@ModelAttribute("InsuranceProduct") InsuranceProduct insuranceProduct) {
        insuranceProduct.setCreateTime(new Date());
        insuranceProductService.addInsuranceProduct(insuranceProduct);
        return "redirect:../common/success.do?reurl=insuranceproduct/insuranceproducts.do";
    }


    /**
     * 更新保险产品
     * @author      likang
     * @param insuranceProduct
     * @return      java.lang.String
     * @exception
     * @date        2018/7/17 21:09
     */
    @RequestMapping(value = "updateInsuranceProduct", method = RequestMethod.POST)
    public String updateInsuranceProduct(@ModelAttribute("insuranceProduct") InsuranceProduct insuranceProduct) {
        insuranceProductService.updateInsuranceProduct(insuranceProduct);
        return "redirect:../common/success.do?reurl=insuranceproduct/insuranceproducts.do";
    }

    /**
     * 根据id删除保险产品
     * @author      likang
     * @param prodId
     * @return      boolean
     * @exception
     * @date        2018/7/17 21:08
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
     * 在保险产品下删除事项
     * @author      likang
     * @param prodId
    * @param matterId
     * @return      boolean
     * @exception
     * @date        2018/7/17 21:07
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
     * 在产品下添加事项
     * @author      likang
     * @param prodId
    * @param matterId
     * @return      boolean
     * @exception
     * @date        2018/7/17 21:07
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
