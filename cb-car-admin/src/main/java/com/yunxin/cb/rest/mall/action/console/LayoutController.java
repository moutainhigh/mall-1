package com.yunxin.cb.rest.mall.action.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
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
