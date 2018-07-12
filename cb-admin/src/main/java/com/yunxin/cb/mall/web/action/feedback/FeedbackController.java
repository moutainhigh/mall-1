package com.yunxin.cb.mall.web.action.feedback;

import com.yunxin.cb.mall.entity.Feedback;
import com.yunxin.cb.mall.service.ICustomerService;
import com.yunxin.cb.mall.service.IFeedbackService;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.persistence.PageSpecification;
import net.sf.json.JSONArray;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        List<String> list=new ArrayList<String>();
        String info="";
        if(null!=feedback.getImages()&&!"".equals(feedback.getImages())){
            JSONArray jsonArray = JSONArray.fromObject(feedback.getImages());
            list=(List)JSONArray.toCollection(jsonArray);
        }else{
            info="无";
        }
        modelMap.addAttribute("feedback", feedback);
        modelMap.addAttribute("list", list);
        modelMap.addAttribute("info", info);
        return "feedback/feedbackDetail";
    }


}
