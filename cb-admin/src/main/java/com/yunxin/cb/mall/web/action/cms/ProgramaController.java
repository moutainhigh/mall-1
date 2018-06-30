package com.yunxin.cb.mall.web.action.cms;

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


    @RequestMapping(value = "toAddPrograma", method = RequestMethod.GET)
    public String toAddPrograma(@ModelAttribute("programa") Programa programa, ModelMap modelMap ) {
        List<ArticleChannel> articleChannels = programaService.getArticleChannels();
        modelMap.addAttribute("articleChannels",articleChannels);
        return "cms/addPrograma";
    }

    @RequestMapping(value = "addPrograma", method = RequestMethod.POST)
    public String addPrograma(@ModelAttribute("programa") Programa programa, BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()) {
            return toAddPrograma(programa, modelMap);
        }
        try {
            programaService.addPrograma(programa);
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
        return toEditPrograma(programa, modelMap);
    }

    private String toEditPrograma( Programa programa, ModelMap modelMap ) {
        List<ArticleChannel> articleChannels = programaService.getArticleChannels();
        modelMap.addAttribute("articleChannels",articleChannels);
        return "cms/editPrograma";
    }

    @RequestMapping(value = "editPrograma", method = RequestMethod.POST)
    public String editPrograma(@Valid @ModelAttribute("programa") Programa programa, BindingResult result,
                                    ModelMap modelMap ) {
        if (result.hasErrors()) {
            return toEditPrograma(programa, modelMap);
        }
        try {
            programaService.updatePrograma(programa);
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

