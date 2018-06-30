package com.yunxin.cb.mall.web.action.system;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by gonglei on 16/2/11.
 */
@Controller
@RequestMapping(value = "/system")
public class ConfigController {

    @RequestMapping(method = RequestMethod.GET)
    public String configs(ModelMap modelMap ){
        return "system/configs";
    }

}
