package com.yunxin.cb.mall.web.action.cms;

import com.alibaba.fastjson.JSON;
import com.yunxin.cb.cms.service.IArticleService;
import com.yunxin.cb.mall.entity.Advertisement;
import com.yunxin.cb.mall.entity.Attachment;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.cb.mall.service.IAdvertisementService;
import com.yunxin.cb.mall.service.IAttachmentService;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.IdGenerate;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;


/**
 * @author k001389
 */
@Controller
@RequestMapping(value = "/cms")
public class AdvertisementController {

    @Resource
    private IArticleService articleService;

    @Resource
    private IAdvertisementService advertisementService;

    @Resource
    private MessageSource messageSource;

    @Resource
    private IAttachmentService attachmentService;

    @RequestMapping(value = "advertisements", method = RequestMethod.GET)
    public String advertisements(ModelMap modelMap) {
        return "cms/advertisements";
    }

    @RequestMapping(value = "pageAdvertisements", method = RequestMethod.POST)
    @ResponseBody
    public Page<Advertisement> pageAdvertisements(@RequestBody PageSpecification<Advertisement> query) {
        Page<Advertisement> page = advertisementService.pageAdvertisements(query);
        return page;
    }

    /**
     *
     * @param query
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "chooseAdvertment",method = RequestMethod.POST)
    public Page<Advertisement> chooseAdvertment(@RequestBody PageSpecification<Advertisement> query, HttpServletRequest request) {
        Page<Advertisement> page = advertisementService.pageAdvertisements(query);
        return page;
    }

    @RequestMapping(value = "toAddAdvertisement", method = RequestMethod.GET)
    public String toAddAdvertisement(@ModelAttribute("advertisement") Advertisement advertisement, ModelMap modelMap) {
        advertisement.setAdvertCode(IdGenerate.genAdvID());
        modelMap.addAttribute("advertisement", advertisement);
        return "cms/addAdvertisement";
    }

    @RequestMapping(value = "addAdvertisement", method = RequestMethod.POST)
    public String addAdvertisement(@Valid @ModelAttribute("advertisement") Advertisement advertisement, BindingResult result, ModelMap modelMap, Locale locale,HttpServletRequest request) {
        if (result.hasErrors()) {
            return toAddAdvertisement(advertisement, modelMap);
        }
        try {
            String[] imgurl = request.getParameterValues("imgurl");
            if(imgurl.length>0){
                advertisement.setPicPath(imgurl[0].split(",")[0]);
                advertisementService.addAdvertisement(advertisement);
                //保存图片路径
                attachmentService.deleteAttachmentPictures(ObjectType.ADVERT,advertisement.getAdvertId());
                for (String imgpath:imgurl) {
                    attachmentService.addAttachmentPictures(ObjectType.ADVERT,advertisement.getAdvertId(),imgpath);
                }
            }
        } catch (EntityExistException e) {
            result.addError(new FieldError("advertisement", "advertTitle", advertisement.getAdvertTitle(), true, null, null,
                    e.getMessage()));
            modelMap.put("errerMsg",e.getMessage());
            return toAddAdvertisement(advertisement, modelMap);
//            return "redirect:../common/failure.do?reurl=cms/addAdvertisement.do";
        }
        return "redirect:../common/success.do?reurl=cms/advertisements.do";

    }

    @RequestMapping(value = "toEditAdvertisement", method = RequestMethod.GET)
    public String toEditAdvertisement(@RequestParam("advertId") int advertId, ModelMap modelMap) {
        Advertisement advertisement = advertisementService.getAdvertisementById(advertId);
        modelMap.addAttribute("advertisement", advertisement);
        List<Attachment> listAttachment=attachmentService.findAttachmentByObjectTypeAndObjectId(ObjectType.ADVERT,advertId);
        modelMap.addAttribute("listAttachment",JSON.toJSON(listAttachment));
        return editAdvertisement(advertisement, modelMap);
    }

    private String editAdvertisement(Advertisement advertisement, ModelMap modelMap) {

        return "cms/editAdvertisement";
    }

    @RequestMapping(value = "editAdvertisement", method = RequestMethod.POST)
    public String editAdvertisement(@Valid @ModelAttribute("advertisement") Advertisement advertisement, BindingResult result, ModelMap modelMap, Locale locale,HttpServletRequest request) {
        if (result.hasErrors()) {
            return toEditAdvertisement(advertisement.getAdvertId(), modelMap);
        }
        try {
            String[] imgurl = request.getParameterValues("imgurl");
            if(imgurl.length>0){
                advertisement.setPicPath(imgurl[0].split(",")[0]);
                advertisementService.updateAdvertisement(advertisement);
                //保存图片路径
                attachmentService.deleteAttachmentPictures(ObjectType.ADVERT,advertisement.getAdvertId());
                for (String imgpath:imgurl) {
                    attachmentService.addAttachmentPictures(ObjectType.ADVERT,advertisement.getAdvertId(),imgpath);
                }
            }
        } catch (EntityExistException e) {
            result.addError(new FieldError("advertisement", "advertTitle", advertisement.getAdvertTitle(), true, null, null,
                    messageSource.getMessage("article_articleTitle_repeat", null, locale)));
            return "redirect:../common/failure.do?reurl=cms/toEditAdvertisement.do?advertId=" + advertisement.getAdvertId();
        }
        return "redirect:../common/success.do?reurl=cms/advertisements.do";
    }

    @RequestMapping(value = "removeAdvertisementById", method = RequestMethod.GET)
    @ResponseBody
    public boolean removeAdvertisementById(@RequestParam("advertId") int advertId) {
        try {
            attachmentService.deleteAttachmentPictures(ObjectType.ADVERT,advertId);
            advertisementService.removeAdvertisementById(advertId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @RequestMapping(value = "enableAdvertisementById", method = RequestMethod.GET)
    @ResponseBody
    public int enableAdvertisementById(@RequestParam("advertId") int advertId, @RequestParam("enabled") boolean enabled) {
        advertisementService.enableByAdvertisementId(enabled, advertId);
        return advertId;
    }
}
