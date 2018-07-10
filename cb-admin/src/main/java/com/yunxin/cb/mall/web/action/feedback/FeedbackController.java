package com.yunxin.cb.mall.web.action.feedback;

import com.yunxin.cb.mall.entity.Brand;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.Feedback;
import com.yunxin.cb.mall.service.IBrandService;
import com.yunxin.cb.mall.service.ICategoryService;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.mall.service.IFeedbackService;
import com.yunxin.cb.mall.vo.TreeViewItem;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.exception.EntityExistException;
import com.yunxin.core.persistence.PageSpecification;
import net.sf.json.JSONArray;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * @author likang
 */
@Controller
@RequestMapping(value = "/feedback")
@SessionAttributes({SecurityConstants.LOGIN_SESSION})
public class FeedbackController {

    @Resource
    private IFeedbackService feedbackService;

    private ICustomerService customerService;


    @RequestMapping(value = "feedbacks")
    public String feedbacks(ModelMap modelMap) {
        return "feedback/feedbacks";
    }

    /**
     * Feedback分页
     *
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Page<Feedback> pageFeedback(@RequestBody PageSpecification<Feedback> feedBackQuery) {
        return feedbackService.pageFeedback(feedBackQuery);
    }



    /**
     * Feedback详情
     *
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String feedBackDetail(@RequestParam("id") int id,ModelMap modelMap) {
        Feedback feedback = feedbackService.getFeedbackByid(id);
        JSONArray jsonArray = JSONArray.fromObject(feedback.getImages());
        List<String> list=(List)JSONArray.toCollection(jsonArray);
        modelMap.addAttribute("feedback", feedback);
        modelMap.addAttribute("list", list);
        return "feedback/feedbackDetail";
    }


}
