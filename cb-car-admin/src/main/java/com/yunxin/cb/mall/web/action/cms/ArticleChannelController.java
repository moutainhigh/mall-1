package com.yunxin.cb.mall.web.action.cms;

import com.alibaba.fastjson.JSON;
import com.yunxin.cb.cms.entity.*;
import com.yunxin.cb.mall.entity.Attachment;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.cb.mall.service.IAttachmentService;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.cb.cms.service.IProgramaService;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.cb.cms.entity.ArticleChannel;
import com.yunxin.cb.cms.entity.Programa;
import com.yunxin.cb.cms.service.IProgramaService;
import org.springframework.context.MessageSource;
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
 * @author z001075
 */
@Controller
@RequestMapping(value = "/cms")
public class ArticleChannelController {

    @Resource
    private IProgramaService programaService;
    @Resource
    private IAttachmentService attachmentService;


    @RequestMapping(value = "channelProgramas",method = RequestMethod.GET)
    public String channelProgramas(ModelMap modelMAap) {
        return "cms/channelProgramas";
    }


    @RequestMapping(value = "getArticleChannels", method = RequestMethod.POST)
    @ResponseBody
    public List<ArticleChannel> getArticleChannels() {
        List<ArticleChannel> page = programaService.getArticleChannels();
        return page;
    }

    @RequestMapping(value = "getProgramasByChannelId", method = RequestMethod.POST)
    @ResponseBody
    public List<Programa> getProgramasByChannelId(@RequestBody PageSpecification pageRequest) {
        String channelId = (String) pageRequest.getData().get("channelId");
        return programaService.getProgramasByChannelId(Integer.parseInt(channelId));
    }

    @RequestMapping(value = "toAddArticleChannel", method = RequestMethod.GET)
    public String toAddArticleChannel(@ModelAttribute("channel") ArticleChannel channel, ModelMap modelMap) {
//        List<ArticleChannelCategory> articleChannelCategories = programaService.getAllArticleChannelCategories();
//        modelMap.addAttribute("articleChannelCategories", articleChannelCategories);
        return "cms/addArticleChannel";
    }

    @RequestMapping(value = "addArticleChannel", method = RequestMethod.POST)
    public String addArticleChannel(@ModelAttribute("channel") ArticleChannel channel, BindingResult result, ModelMap modelMap,HttpServletRequest request) {
        if (result.hasErrors()) {
            return toAddArticleChannel(channel, modelMap);
        }
        try {
            String[] imgurl = request.getParameterValues("imgurl");
            if(imgurl.length>0){
                channel.setOperaImgPath(imgurl[0].split(",")[0]);
                channel=programaService.addArticleChannel(channel);
                //保存图片路径
                attachmentService.deleteAttachmentPictures(ObjectType.CHANNER,channel.getChannelId());
                for (String imgpath:imgurl) {
                    attachmentService.addAttachmentPictures(ObjectType.CHANNER,channel.getChannelId(),imgpath);
                }
            }

        } catch (EntityExistException e) {
            result.addError(new FieldError("channel", "channelName", channel.getChannelName(), true, null, null,e.getMessage()));
            return toAddArticleChannel(channel, modelMap);
        }
        return "redirect:../common/success.do?reurl=cms/channelProgramas.do";
    }

    @RequestMapping(value = "toEditArticleChannel", method = RequestMethod.GET)
    public String toEditArticleChannel(@RequestParam("channelId") int channelId, ModelMap modelMap) {

        ArticleChannel channel = programaService.getArticleChannelById(channelId);
        modelMap.addAttribute("channel", channel);
        List<Attachment> listAttachment=attachmentService.findAttachmentByObjectTypeAndObjectId(ObjectType.CHANNER,channelId);
        modelMap.addAttribute("listAttachment",JSON.toJSON(listAttachment));
        return "cms/editArticleChannel";
    }

    @RequestMapping(value = "editArticleChannel", method = RequestMethod.POST)
    public String editArticleChannel(@Valid @ModelAttribute("channel") ArticleChannel channel, BindingResult result,
                                     ModelMap modelMap, Locale locale,HttpServletRequest request) {
        if (result.hasErrors()) {
            return toEditArticleChannel(channel.getChannelId(), modelMap);
        }
        try {
            String[] imgurl = request.getParameterValues("imgurl");
            if(imgurl.length>0){
                channel.setOperaImgPath(imgurl[0].split(",")[0]);
                programaService.updateArticleChannel(channel);
                //保存图片路径
                attachmentService.deleteAttachmentPictures(ObjectType.CHANNER,channel.getChannelId());
                for (String imgpath:imgurl) {
                    attachmentService.addAttachmentPictures(ObjectType.CHANNER,channel.getChannelId(),imgpath);
                }
            }
            return "redirect:../common/success.do?reurl=cms/channelProgramas.do";
        } catch (EntityExistException e) {
            result.addError(new FieldError("channel", "channelName", channel.getChannelName(), true, null, null,e.getMessage()));
            return toEditArticleChannel(channel.getChannelId(), modelMap);
        }
    }

    @RequestMapping(value = "removeArticleChannel", method = RequestMethod.GET)
    @ResponseBody
    public String removeArticleChannel(@RequestParam("channelId") int channelId) {
        try {
            attachmentService.deleteAttachmentPictures(ObjectType.CHANNER,channelId);
            programaService.removeArticleChannelById(channelId);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

}

