package com.yunxin.cb.mall.web.action.log;

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
import java.util.List;

/**
 * @author likang
 */
@Controller
@RequestMapping(value = "/logs")
@SessionAttributes({SecurityConstants.LOGIN_SESSION})
public class LogController {

    @RequestMapping(value = "logs")
    public String logs(ModelMap modelMap) {
        return "logs/logs";
    }

    @RequestMapping(value = "errorLogs")
    public String errorLogs(ModelMap modelMap) {
        return "logs/errorLogs";
    }

}
