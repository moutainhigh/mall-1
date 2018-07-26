package com.yunxin.cb.mall.web.action.cms;

import com.alibaba.fastjson.JSON;
import com.yunxin.cb.mall.entity.Attachment;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.cb.mall.service.IAttachmentService;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.cb.cms.entity.ArticleChannel;
import com.yunxin.cb.cms.entity.Programa;
import com.yunxin.cb.cms.service.IProgramaService;
import com.yunxin.cb.cms.entity.ArticleChannel;
import com.yunxin.cb.cms.entity.Programa;
import com.yunxin.cb.cms.service.IProgramaService;
import com.yunxin.core.exception.EntityExistException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author z001075
 */
@Controller
@RequestMapping(value = "/cms")
public class ProgramaController {

    @Resource
    private IProgramaService programaService;
    @Resource
    private IAttachmentService attachmentService;

    @RequestMapping(value = "toAddPrograma", method = RequestMethod.GET)
    public String toAddPrograma(@ModelAttribute("programa") Programa programa, ModelMap modelMap ) {
        List<ArticleChannel> articleChannels = programaService.getArticleChannels();
        modelMap.addAttribute("articleChannels",articleChannels);
        return "cms/addPrograma";
    }

    @RequestMapping(value = "addPrograma", method = RequestMethod.POST)
    public String addPrograma(@ModelAttribute("programa") Programa programa, BindingResult result, ModelMap modelMap,HttpServletRequest request) {
        if (result.hasErrors()) {
            return toAddPrograma(programa, modelMap);
        }
        try {
            String[] imgurl = request.getParameterValues("imgurl");
            if(imgurl.length>0){
                programa.setOperaImgPath(imgurl[0].split(",")[0]);
                programa=programaService.addPrograma(programa);
                //保存图片路径
                attachmentService.deleteAttachmentPictures(ObjectType.PROGRAMA,programa.getProgramaId());
                for (String imgpath:imgurl) {
                    attachmentService.addAttachmentPictures(ObjectType.PROGRAMA,programa.getProgramaId(),imgpath);
                }
            }

        } catch (EntityExistException e) {
            result.addError(new FieldError("programa", "programaName", programa.getProgramaName(), true, null, null,
                    e.getMessage()));
            return toAddPrograma(programa, modelMap);
        }
        return "redirect:../common/success.do?reurl=cms/channelProgramas.do";
    }

    @RequestMapping(value = "toEditPrograma", method = RequestMethod.GET)
    public String toEditPrograma(@RequestParam("programaId") int programaId, ModelMap modelMap ) {
        Programa programa = programaService.getProgramaById(programaId);
        modelMap.addAttribute("programa", programa);
        List<Attachment> listAttachment=attachmentService.findAttachmentByObjectTypeAndObjectId(ObjectType.PROGRAMA,programaId);
        modelMap.addAttribute("listAttachment",JSON.toJSON(listAttachment));
        return toEditPrograma(programa, modelMap);
    }

    private String toEditPrograma( Programa programa, ModelMap modelMap ) {
        List<ArticleChannel> articleChannels = programaService.getArticleChannels();
        modelMap.addAttribute("articleChannels",articleChannels);
        return "cms/editPrograma";
    }

    @RequestMapping(value = "editPrograma", method = RequestMethod.POST)
    public String editPrograma(@Valid @ModelAttribute("programa") Programa programa, BindingResult result,
                                    ModelMap modelMap ,HttpServletRequest request) {
        if (result.hasErrors()) {
            return toEditPrograma(programa, modelMap);
        }
        try {
            String[] imgurl = request.getParameterValues("imgurl");
            if(imgurl.length>0){
                programa.setOperaImgPath(imgurl[0].split(",")[0]);
                programaService.updatePrograma(programa);
                //保存图片路径
                attachmentService.deleteAttachmentPictures(ObjectType.PROGRAMA,programa.getProgramaId());
                for (String imgpath:imgurl) {
                    attachmentService.addAttachmentPictures(ObjectType.PROGRAMA,programa.getProgramaId(),imgpath);
                }
            }

        } catch (EntityExistException e) {
            result.addError(new FieldError("programa", "programaName", programa.getProgramaName(), true, null, null,
                    e.getMessage()));
            return toEditPrograma(programa, modelMap);
        }
        return "redirect:../common/success.do?reurl=cms/channelProgramas.do";
    }

    @RequestMapping(value = "removePrograma", method = RequestMethod.GET)
    @ResponseBody
    public String removePrograma(@RequestParam("programaId") int programaId ) {
        try {
            programaService.removeProgramaById(programaId);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }




}

