package com.yunxin.cb.rest.mall.action.console;

import com.yunxin.cb.rest.mall.action.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
/**
 * @author ThinkPad
 */
@Controller
@RequestMapping(value = "/console")
public class ConsoleController extends BaseController {

    protected static Logger logger = LoggerFactory.getLogger(ConsoleController.class);

    @RequestMapping(value = "dashboard")
    public String dashboard(ModelMap modelMap, HttpServletRequest request) {
        HttpSession session = request.getSession();

        Calendar calendar = Calendar.getInstance();
        Date endDate = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startDate = calendar.getTime();
        double orderMonthTotalPrice = 0.00;
        double orderPayedTotalPrice = 0.00;
        long orderMonthCount = 0;
        long orderMonthPayedCount = 0;
        modelMap.addAttribute("orderMonthTotalPrice", orderMonthTotalPrice);
        modelMap.addAttribute("orderMonthPayedTotalPrice", orderPayedTotalPrice);
        modelMap.addAttribute("orderMonthCount", orderMonthCount);
        modelMap.addAttribute("orderMonthPayedCount", orderMonthPayedCount);

        modelMap.addAttribute("evaluates", null);

        modelMap.addAttribute("complaints", null);

        //统计 客户数量
        modelMap.addAttribute("customerCount", 0);
        modelMap.addAttribute("customerCountMonthly",  0);
        modelMap.addAttribute("sellerCount", 0);
        modelMap.addAttribute("commodityCount", 0);
        modelMap.addAttribute("commodityUpShelvesCount", 0);
        modelMap.addAttribute("commodityUpShelvesPopularCount", 0);
        modelMap.addAttribute("commodityUpShelvesRecommendCount", 0);
        modelMap.addAttribute("productCount", 0);
        modelMap.addAttribute("articleCount", 0);
        modelMap.addAttribute("channelCount", 0);
        modelMap.addAttribute("programaCount", 0);
        modelMap.addAttribute("subjectCount", 0);
        modelMap.addAttribute("orderCount", 0);
        modelMap.addAttribute("orderCommodityCount", 0);
        modelMap.addAttribute("orderProductCount", 0);
        modelMap.addAttribute("orderTotalPrice",0.00 );
        modelMap.addAttribute("orderAvgPrice", 0.00);
        modelMap.addAttribute("concentCount", 0);
        modelMap.addAttribute("deviceCount", 0);
        return "console/dashboard";

    }
}
