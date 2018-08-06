package com.yunxin.cb.mall.web.action.insurance;

import com.alibaba.fastjson.JSON;
import com.yunxin.cb.insurance.entity.InsuranceProduct;
import com.yunxin.cb.insurance.service.IInsuranceProductService;
import com.yunxin.cb.mall.entity.Attachment;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.cb.mall.service.IAttachmentService;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


/**
 * @Description: 保险产品控制器
 * @Author: likang
 * @CreateDate: 2018/7/17 21:01
 */
@Controller
@RequestMapping(value = "/insuranceproduct")
public class InsuranceProductController {

    @Resource
    private IInsuranceProductService insuranceProductService;
    @Resource
    private IAttachmentService attachmentService;

    /**
     * @Description: 跳转到保险产品页面
     * @Author: likang
     * @CreateDate: 2018/7/17 21:02
     */
    @RequestMapping(value = "insuranceproducts", method = RequestMethod.GET)
    public String insuranceproducts() {
        return "insuranceproduct/insuranceproducts";
    }

    /**
     * 保险产品的分页列表
     *
     * @param query
     * @return org.springframework.data.domain.Page<com.yunxin.cb.insurance.entity.InsuranceProduct>
     * @throws
     * @author likang
     * @date 2018/7/17 21:03
     */
    @RequestMapping(value = "pageInsuranceProduct", method = RequestMethod.POST)
    @ResponseBody
    public Page<InsuranceProduct> pageInsuranceProduct(@RequestBody PageSpecification<InsuranceProduct> query) {
        Page<InsuranceProduct> page = insuranceProductService.pageInsuranceProduct(query);
        return page;
    }

    /**
     * 跳转保险产品详情页面
     *
     * @param prodId
     * @param modelMap
     * @return java.lang.String
     * @throws
     * @author likang
     * @date 2018/7/17 21:05
     */
    @RequestMapping(value = "toEditProduct", method = RequestMethod.GET)
    public String toEditProduct(@RequestParam("prodId") int prodId, ModelMap modelMap) throws Exception {
        InsuranceProduct insuranceProduct = insuranceProductService.getInsuranceProductById(prodId);
        modelMap.addAttribute("insuranceProduct", insuranceProduct);
        List<Attachment> listAttachment = attachmentService.findAttachmentByObjectTypeAndObjectId(ObjectType.INSURANCEPRODUCT, prodId);
        modelMap.addAttribute("listAttachment", JSON.toJSON(listAttachment));
        List<Attachment> listAttachment1 = attachmentService.findAttachmentByObjectTypeAndObjectId(ObjectType.INSURANCEPRODUCTDETAIL, prodId);
        modelMap.addAttribute("listAttachment1", JSON.toJSON(listAttachment1));
        return "insuranceproduct/editinsuranceproduct";
    }

    /**
     * 跳转到保险产品添加页面
     *
     * @param insuranceProduct
     * @param modelMap
     * @return java.lang.String
     * @throws
     * @author likang
     * @date 2018/7/17 21:05
     */
    @RequestMapping(value = "toAddProduct", method = RequestMethod.GET)
    public String toAddProduct(@ModelAttribute("insuranceProduct") InsuranceProduct insuranceProduct, ModelMap modelMap) {
        modelMap.addAttribute("insuranceProduct", insuranceProduct);
        return "insuranceproduct/addinsuranceproduct";
    }


    /**
     * 添加保险产品
     *
     * @param insuranceProduct
     * @return java.lang.String
     * @throws
     * @author likang
     * @date 2018/7/17 21:06
     */
    @RequestMapping(value = "addInsuranceProduct", method = RequestMethod.POST)
    public String addInsuranceProduct(@ModelAttribute("InsuranceProduct") InsuranceProduct insuranceProduct, HttpServletRequest request) {
        String[] imgurl = request.getParameterValues("imgurl");
        String[] imgurl1 = request.getParameterValues("imgurl1");
        if (imgurl.length > 0 && imgurl.length > 0) {
            insuranceProduct.setProdImg(imgurl[0].split(",")[0]);
            insuranceProduct.setDescriptionImg(imgurl[0].split(",")[0]);
            insuranceProduct.setCreateTime(new Date());
            insuranceProduct = insuranceProductService.addInsuranceProduct(insuranceProduct);
            //保存图片路径
            attachmentService.deleteAttachmentPictures(ObjectType.INSURANCEPRODUCT, insuranceProduct.getProdId());
            for (String imgpath : imgurl) {
                attachmentService.addAttachmentPictures(ObjectType.INSURANCEPRODUCT, insuranceProduct.getProdId(), imgpath);
            }
            //保存图片路径
            attachmentService.deleteAttachmentPictures(ObjectType.INSURANCEPRODUCTDETAIL, insuranceProduct.getProdId());
            for (String imgpath : imgurl1) {
                attachmentService.addAttachmentPictures(ObjectType.INSURANCEPRODUCTDETAIL, insuranceProduct.getProdId(), imgpath);
            }
        }
        return "redirect:../common/success.do?reurl=insuranceproduct/insuranceproducts.do";
    }


    /**
     * 更新保险产品
     *
     * @param insuranceProduct
     * @return java.lang.String
     * @throws
     * @author likang
     * @date 2018/7/17 21:09
     */
    @RequestMapping(value = "updateInsuranceProduct", method = RequestMethod.POST)
    public String updateInsuranceProduct(@ModelAttribute("insuranceProduct") InsuranceProduct insuranceProduct, HttpServletRequest request) {
        String[] imgurl = request.getParameterValues("imgurl");
        String[] imgurl1 = request.getParameterValues("imgurl1");
        if (imgurl.length > 0 && imgurl.length > 0) {
            insuranceProduct.setProdImg(imgurl[0].split(",")[0]);
            insuranceProduct.setDescriptionImg(imgurl[0].split(",")[0]);
            insuranceProduct.setCreateTime(new Date());
            insuranceProduct = insuranceProductService.updateInsuranceProduct(insuranceProduct);
            //保存图片路径
            attachmentService.deleteAttachmentPictures(ObjectType.INSURANCEPRODUCT, insuranceProduct.getProdId());
            for (String imgpath : imgurl) {
                attachmentService.addAttachmentPictures(ObjectType.INSURANCEPRODUCT, insuranceProduct.getProdId(), imgpath);
            }
            //保存图片路径
            attachmentService.deleteAttachmentPictures(ObjectType.INSURANCEPRODUCTDETAIL, insuranceProduct.getProdId());
            for (String imgpath : imgurl1) {
                attachmentService.addAttachmentPictures(ObjectType.INSURANCEPRODUCTDETAIL, insuranceProduct.getProdId(), imgpath);
            }
        }
        return "redirect:../common/success.do?reurl=insuranceproduct/insuranceproducts.do";
    }

    /**
     * 根据id删除保险产品
     *
     * @param prodId
     * @return boolean
     * @throws
     * @author likang
     * @date 2018/7/17 21:08
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
     * 修改事项的启用状态
     * @author      likang
     * @param proId
    * @param enabled
     * @return      boolean
     * @exception
     * @date        2018/7/20 10:33
     */
    @RequestMapping(value = "enableInsuranceProductById",method = RequestMethod.GET)
    @ResponseBody
    public boolean enableInsuranceProductById(@RequestParam("proId") int proId,@RequestParam("enabled") int enabled) {
        try{
            insuranceProductService.enableInsuranceProductById(proId,enabled);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
