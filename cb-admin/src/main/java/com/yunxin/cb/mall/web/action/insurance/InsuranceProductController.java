package com.yunxin.cb.mall.web.action.insurance;

import com.yunxin.cb.insurance.entity.InsuranceProduct;
import com.yunxin.cb.insurance.service.IInsuranceProductService;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;


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

}
