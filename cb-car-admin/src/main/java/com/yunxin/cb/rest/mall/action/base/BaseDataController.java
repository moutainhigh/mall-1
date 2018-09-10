package com.yunxin.cb.rest.mall.action.base;

import com.yunxin.cb.mall.entity.CarBaseData;
import com.yunxin.cb.mall.service.CarBaseDataService;
import com.yunxin.cb.rest.mall.action.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Title:
 * @Auther: eleven
 * @Date: 2018/9/10 11:09
 * @Description:
 */
@Controller
@RequestMapping("/base")
public class BaseDataController extends BaseController {

    @Resource
    private CarBaseDataService carBaseDataService;

    @RequestMapping(value = "carBaseData",method = RequestMethod.GET)
    public String catalogs(ModelMap modelMap, HttpServletRequest request) {
        List<CarBaseData> datas = carBaseDataService.getCarBaseDataList(null);
        modelMap.addAttribute("datas", datas);
        return "base/baseDatas";
    }
}
