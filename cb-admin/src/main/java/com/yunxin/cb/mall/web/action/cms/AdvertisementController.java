package com.yunxin.cb.mall.web.action.cms;

import com.yunxin.cb.cms.service.IArticleService;
import com.yunxin.cb.mall.entity.Advertisement;
import com.yunxin.cb.mall.service.IAdvertisementService;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
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

    @RequestMapping(value = "toAddAdvertisement", method = RequestMethod.GET)
    public String toAddAdvertisement(@ModelAttribute("advertisement") Advertisement advertisement, ModelMap modelMap) {

        return "cms/addAdvertisement";
    }

    @RequestMapping(value = "addAdvertisement", method = RequestMethod.POST)
    public String addAdvertisement(@Valid @ModelAttribute("advertisement") Advertisement advertisement, BindingResult result, ModelMap modelMap, Locale locale) {
        if (result.hasErrors()) {
            return toAddAdvertisement(advertisement, modelMap);
        }
        try {
            advertisementService.addAdvertisement(advertisement);

        } catch (EntityExistException e) {
            result.addError(new FieldError("advertisement", "advertTitle", advertisement.getAdvertTitle(), true, null, null,
                    messageSource.getMessage("advertisement", null, locale)));
            return "redirect:../common/failure.do?reurl=cms/addAdvertisement.do";
        }
        return "redirect:../common/success.do?reurl=cms/advertisements.do";

    }

    @RequestMapping(value = "toEditAdvertisement", method = RequestMethod.GET)
    public String toEditAdvertisement(@RequestParam("advertId") int advertId, ModelMap modelMap) {
        Advertisement advertisement = advertisementService.getAdvertisementById(advertId);
        modelMap.addAttribute("advertisement", advertisement);
        return editAdvertisement(advertisement, modelMap);
    }

    private String editAdvertisement(Advertisement advertisement, ModelMap modelMap) {
        return "cms/editAdvertisement";
    }

    @RequestMapping(value = "editAdvertisement", method = RequestMethod.POST)
    public String editAdvertisement(@Valid @ModelAttribute("advertisement") Advertisement advertisement, BindingResult result, ModelMap modelMap, Locale locale) {
        if (result.hasErrors()) {
            return toEditAdvertisement(advertisement.getAdvertId(), modelMap);
        }
        try {
            advertisementService.updateAdvertisement(advertisement);
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
