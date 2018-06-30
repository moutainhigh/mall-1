package com.yunxin.cb.mall.web.action.cms;

import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.cb.cms.entity.ArticleChannel;
import com.yunxin.cb.cms.entity.Programa;
import com.yunxin.cb.cms.entity.SpecialSubject;
import com.yunxin.cb.cms.service.IArticleService;
import com.yunxin.cb.cms.service.IProgramaService;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.cb.cms.entity.ArticleChannel;
import com.yunxin.cb.cms.entity.Programa;
import com.yunxin.cb.cms.entity.SpecialSubject;
import com.yunxin.cb.cms.service.IArticleService;
import com.yunxin.cb.cms.service.IProgramaService;
import com.yunxin.core.exception.EntityExistException;
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

//import com.yunxin.cb.mall.entity.meta.FreeRepairCleanRecordType;

/**
 * @author k001389
 */
@Controller
@RequestMapping(value = "/cms")
public class SubjectController {

    @Resource
    private IArticleService articleService;

    @Resource
    private IProgramaService programaService;

    @Resource
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public String specialSubjects(ModelMap modelMap) {
        List<Programa> programas = programaService.getProgramas(true);
        List<ArticleChannel> articleChannels = programaService.getArticleChannels(true);
        modelMap.addAttribute("programas", programas);
        modelMap.addAttribute("articleChannels", articleChannels);
        return "cms/specialSubjects";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Page<SpecialSubject> pageSpecialSubjects(@RequestBody PageSpecification<SpecialSubject> query) {
        Page<SpecialSubject> page = programaService.pageSpecialSubjects(query);
        return page;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String toAddSpecialSubject(@ModelAttribute("subject") SpecialSubject subject, ModelMap modelMap) {
        List<Programa> programas = programaService.getProgramas(true);
        List<ArticleChannel> articleChannels = programaService.getArticleChannels(true);
        modelMap.addAttribute("programas", programas);
        modelMap.addAttribute("articleChannels", articleChannels);
        return "cms/addSpecialSubject";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addSpecialSubject(@Valid @ModelAttribute("subject") SpecialSubject subject, BindingResult result, ModelMap modelMap, Locale locale) {
        if (result.hasErrors()) {
            return toAddSpecialSubject(subject, modelMap);
        }
        try {
            programaService.addSpecialSubject(subject);
        } catch (EntityExistException e) {
            result.addError(new FieldError("subject", "subjectTitle", subject.getSubjectName(), true, null, null,e.getMessage()));
            return toAddSpecialSubject(subject, modelMap);
        }
        return "redirect:../common/success.do?reurl=cms/specialSubjects.do";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String toEditSpecialSubject(@RequestParam("subjectId") int subjectId, ModelMap modelMap) {
        SpecialSubject subject = programaService.getSpecialSubjectById(subjectId);
        modelMap.addAttribute("subject", subject);
        return editSpecialSubject(subject, modelMap);
    }

    private String editSpecialSubject(SpecialSubject subject, ModelMap modelMap) {
        List<Programa> programas = programaService.getProgramas(true);
        List<ArticleChannel> articleChannels = programaService.getArticleChannels(true);
        modelMap.addAttribute("programas", programas);
        modelMap.addAttribute("articleChannels", articleChannels);
        return "cms/editSpecialSubject";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String editSpecialSubject(@Valid @ModelAttribute("subject") SpecialSubject subject, BindingResult result, HttpServletRequest request, ModelMap modelMap, Locale locale) {
        if (result.hasErrors()) {
            return toEditSpecialSubject(subject.getSubjectId(), modelMap);
        }
        try {
            programaService.updateSpecialSubject(subject);
        } catch (EntityExistException e) {
            result.addError(new FieldError("subject", "subjectName", subject.getSubjectName(), true, null, null,e.getMessage()));
            return toEditSpecialSubject(subject.getSubjectId(), modelMap);
        }
        return "redirect:../common/success.do?reurl=cms/specialSubjects.do";
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public int removeSpecialSubjectById(@RequestParam("subjectId") int subjectId) {
        programaService.removeSpecialSubjectById(subjectId);
        return subjectId;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public int enableSpecialSubjectById(@RequestParam("subjectId") int subjectId, @RequestParam("enabled") boolean enabled) {
        programaService.enableSpecialSubjectById(subjectId, enabled);
        return subjectId;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<SpecialSubject> getSpecialSubjectsByProgramaId(@RequestParam("programaId") int programaId) {
        return programaService.getSpecialSubjectsByProgramaId(programaId);
    }
}
