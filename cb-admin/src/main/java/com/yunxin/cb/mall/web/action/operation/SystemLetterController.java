package com.yunxin.cb.mall.web.action.operation;

import com.yunxin.cb.console.service.ILogsService;
import com.yunxin.cb.mall.entity.SystemLetter;
import com.yunxin.cb.mall.service.ILetterService;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.cb.mall.entity.SystemLetter;
import com.yunxin.cb.mall.service.ILetterService;
import com.yunxin.cb.console.service.ILogsService;
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
import java.util.Locale;

@Controller
@RequestMapping(value = "/operation")
public class SystemLetterController {

    @Resource
    private ILetterService letterService;

    @Resource
    private ILogsService logsService;

    @Resource
    private MessageSource messageSource;

    @RequestMapping(value = "systemLetters",method = RequestMethod.GET)
    public String systemLetters() {
        return "operation/systemLetters";
    }

    @RequestMapping(value = "pageSystemLetters",method = RequestMethod.POST)
    @ResponseBody
    public Page<SystemLetter> pageSystemLetters(@RequestBody PageSpecification<SystemLetter> query) {
        Page<SystemLetter> systemLetterPages = letterService.pageSystemLetters(query);
        return systemLetterPages;
    }

    @RequestMapping(value = "toAddSystemLetter",method = RequestMethod.GET)
    public String toAddSystemLetter(@ModelAttribute("systemLetter") SystemLetter systemLetter, ModelMap modelMap) {
        return "operation/addSystemLetter";
    }

    @RequestMapping(value = "addSystemLetter",method = RequestMethod.POST)
    public String addSystemLetter(@Valid @ModelAttribute("systemLetter") SystemLetter systemLetter, BindingResult result, HttpServletRequest request, ModelMap modelMap, Locale locale) {
        if (result.hasErrors()) {
            return toAddSystemLetter(systemLetter, modelMap);
        }
        try {
            letterService.addSystemLetter(systemLetter);
            return "redirect:../common/success.do?reurl=operation/systemLetters.do";
        } catch (Exception e) {
            result.addError(new FieldError("systemLetter", "title", systemLetter.getTitle(), true, null, null,
                    messageSource.getMessage("title", null, locale)));
            e.printStackTrace();
            request.getSession().setAttribute("exception", e.getMessage());
            return "redirect:../common/failure.do?reurl=operation/systemLetters.do";
        }
    }

    @RequestMapping(value = "toSystemLetterDetail",method = RequestMethod.GET)
    public String toSystemLetterDetail(HttpServletRequest request, ModelMap modelMap) {
        int letterId = Integer.valueOf(request.getParameter("letterId"));
//		SystemLetter systemLetterNew = letterService.findLetterDetailByLetterId(letterId);
//		modelMap.addAttribute("letterNew", systemLetterNew);
        modelMap.addAttribute("systemLetter", letterService.getSystemLetterById(letterId));
        return "operation/systemLetterDetail";
    }

    @RequestMapping(value = "toEditSystemLetter",method = RequestMethod.GET)
    public String toEditSystemLetter(@RequestParam("letterId") int letterId, ModelMap modelMap) {
        modelMap.addAttribute("systemLetter", letterService.getSystemLetterById(letterId));
        return "operation/editSystemLetter";
    }

    @RequestMapping(value = "editSystemLetter",method = RequestMethod.POST)
    public String editSystemLetter(@Valid @ModelAttribute("systemLetter") SystemLetter systemLetter, BindingResult result, HttpServletRequest request, ModelMap modelMap, Locale locale) {

        if (result.hasErrors()) {
            return systemLetters();
        }
        try {
            letterService.updateSystemLetter(systemLetter);
            return "redirect:../common/success.do?reurl=operation/systemLetters.do";
        } catch (Exception e) {
            result.addError(new FieldError("systemLetter", "title", systemLetter.getTitle(), true, null, null,
                    messageSource.getMessage("title", null, locale)));
            e.printStackTrace();
            request.getSession().setAttribute("exception", e.getMessage());
            return "redirect:../common/failure.do?reurl=operation/systemLetters.do";
        }
    }

    @RequestMapping(value = "publishSystemLetter",method = RequestMethod.GET)
    @ResponseBody
    public String publishSystemLetter(@RequestParam("letterId") int letterId, @RequestParam("published") boolean published) {


        SystemLetter systemLetter = null;

        try {
            systemLetter = letterService.getSystemLetterById(letterId);
            if(null !=systemLetter && systemLetter.isPublished()){
                return "published";
            }
            letterService.publishSystemLetter(letterId, published);
        }catch (Exception e){
            e.printStackTrace();
            return "failure";
        }
        return "success";
    }

    @RequestMapping(value = "removeSystemLetterById",method = RequestMethod.GET)
    @ResponseBody
    public int removeSystemLetterById(@RequestParam("letterId") int letterId) {
        letterService.deleteSystemLetterById(letterId);
        return letterId;
    }

}
