package com.yunxin.cb.rest.mall.action.base;

import com.yunxin.cb.common.utils.PageSpecification;
import com.yunxin.cb.mall.common.PageFinder;
import com.yunxin.cb.mall.common.Query;
import com.yunxin.cb.mall.entity.CarBrand;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.cb.mall.service.AttachmentService;
import com.yunxin.cb.mall.service.CarBrandService;
import com.yunxin.cb.rest.mall.action.BaseController;
import com.yunxin.cb.util.IdGenerate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
* @Description:
* @Author:         likang
* @CreateDate:     2018/9/10 10:47
*/
@Controller
@RequestMapping("brand")
public class CarBrandController extends BaseController {


    @Resource
    private CarBrandService brandService;

    @Resource
    private AttachmentService attachmentService;

    @RequestMapping("brands.do")
    public String brands(HttpServletRequest request, HttpServletResponse response) {
        return "brand/brands";
    }


    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public PageFinder<CarBrand> pageBrands(@RequestBody PageSpecification<CarBrand> ps) {
        Query q = new Query(ps.getPage(),ps.getPageSize());
        PageFinder<CarBrand> page =  brandService.getCarBrandPagedList(q);
        return page;
    }

    @RequestMapping("addPage.do")
    public String addPage(CarBrand carBrand, ModelMap modelMap) {
        modelMap.addAttribute("carBrand", carBrand);
        return "brand/addbrand";
    }

    @RequestMapping(value = "addBrand", method = RequestMethod.POST)
    public String addBrand(@ModelAttribute("brand") CarBrand carBrand, BindingResult result, ModelMap modelMap, Locale locale, HttpServletRequest request) {
        try {
            String[] imgurl = request.getParameterValues("imgurl");
            if(imgurl.length>0){
                carBrand.setDefaultPic(imgurl[0].split(",")[0]);
                carBrand.setBrandNo(IdGenerate.genBrandID());
                int carBrandId = brandService.addCarBrand(carBrand);
                //保存图片路径
                attachmentService.deleteAttachmentPictures(ObjectType.CARBRAND.toString(),carBrandId);
                for (String imgpath:imgurl) {
                    attachmentService.addAttachment(ObjectType.CARBRAND,carBrandId,imgpath);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:../common/success.do?reurl=brand/brands.do";
    }
}
