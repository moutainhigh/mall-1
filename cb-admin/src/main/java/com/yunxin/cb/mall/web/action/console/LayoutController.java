package com.yunxin.cb.mall.web.action.console;

import com.yunxin.cb.cms.service.IArticleService;
import com.yunxin.cb.cms.service.IProgramaService;
import com.yunxin.cb.console.service.ISecurityService;
import com.yunxin.cb.mall.entity.Complaint;
import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.mall.entity.ProductEvaluate;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.cb.mall.service.*;
import com.yunxin.cb.monitor.service.IConcentService;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.persistence.PageSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author ThinkPad
 */
@Controller
@RequestMapping(value = "/layouts")
public class LayoutController {

    protected static Logger logger = LoggerFactory.getLogger(LayoutController.class);



    @RequestMapping(value = "default")
    public String defaultDecorator() {
        return "layouts/default";

    }
}
