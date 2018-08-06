package com.yunxin.cb.mall.web.action.feedback;

import com.alibaba.fastjson.JSON;
import com.yunxin.cb.mall.entity.Feedback;
import com.yunxin.cb.mall.service.IFeedbackService;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.persistence.PageSpecification;
import net.sf.json.JSONArray;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @Description:    用户反馈
* @Author:         likang
* @CreateDate:     2018/7/17 19:46
*/
@Controller
@RequestMapping(value = "/feedback")
@SessionAttributes({SecurityConstants.LOGIN_SESSION})
public class FeedbackController {

    @Resource
    private IFeedbackService feedbackService;


    /**
     * 页面跳转
     * @author      likang
     * @param modelMap
     * @return      java.lang.String
     * @exception
     * @date        2018/7/17 20:21
     */
    @RequestMapping(value = "feedbacks")
    public String feedbacks(ModelMap modelMap) {
        return "feedback/feedbacks";
    }

    /**
     * 分页
     * @author      likang
     * @param feedBackQuery
     * @return      org.springframework.data.domain.Page<com.yunxin.cb.mall.entity.Feedback>
     * @exception
     * @date        2018/7/17 19:48
     */
    @RequestMapping(value = "pageFeedback",method = RequestMethod.POST)
    @ResponseBody
    public Page<Feedback> pageFeedback(@RequestBody PageSpecification<Feedback> feedBackQuery) {
        return feedbackService.pageFeedback(feedBackQuery);
    }


    /**
     * 去用户反馈详情页面
     * @author      likang
     * @param id
     * @param modelMap
     * @return      java.lang.String
     * @exception
     * @date        2018/7/17 20:20
     */
    @RequestMapping(value = "feedBackDetail",method = RequestMethod.GET)
    public String feedBackDetail(@RequestParam("id") int id,ModelMap modelMap) {
        Feedback feedback = feedbackService.getFeedbackByid(id);
        List<String> list=new ArrayList<String>();
        String info="";
        JSONArray jsonArray=null;
        if(null!=feedback.getImages()&&!"".equals(feedback.getImages())){
            jsonArray = JSONArray.fromObject(feedback.getImages());
            list=(List)JSONArray.toCollection(jsonArray);
        }else{
            info="无";
        }
        modelMap.addAttribute("feedback", feedback);
        modelMap.addAttribute("list", JSON.toJSON(list));
        modelMap.addAttribute("info", info);
        return "feedback/feedbackDetail";
    }


}
