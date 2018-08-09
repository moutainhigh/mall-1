package com.yunxin.cb.mall.web.action.statistics;

import com.yunxin.cb.mall.query.HotSalesQuery;
import com.yunxin.cb.mall.query.SalesChartsQuery;
import com.yunxin.cb.mall.service.IStatisticsBillService;
import com.yunxin.cb.mall.service.IStatisticsMoneyService;
import com.yunxin.cb.mall.service.IStatisticsOrderService;
import com.yunxin.cb.mall.service.IStatisticsService;
import com.yunxin.cb.mall.view.*;
import com.yunxin.cb.mall.vo.HotSalesVo;
import com.yunxin.cb.mall.vo.SalesYearVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Aidy_He on 16/3/10.
 */
@Controller
@RequestMapping(value = "/statistics")
public class StatisticsController {

    @Resource
    private IStatisticsService statisticsService;

    @Resource
    private IStatisticsOrderService statisticsOrderService;

    @Resource
    private IStatisticsMoneyService statisticsMoneyService;

    @Autowired
    private IStatisticsBillService statisticsBillService;


    @RequestMapping(value = "orderStatistics",method = RequestMethod.GET)
    public String orderStatistics() {
        return "statistics/orderStatistics";
    }

    @RequestMapping(value = "moneyStatistics",method = RequestMethod.GET)
    public String moneyStatistics() {
        return "statistics/moneyStatistics";
    }


    @RequestMapping(value = "productStatistics",method = RequestMethod.GET)
    public String productStatistics() {
        return "statistics/productStatistics";
    }

    @RequestMapping(value = "customerStatistics",method = RequestMethod.GET)
    public String customerStatistics() {
        return "statistics/customerStatistics";
    }


    @RequestMapping(value = "getDayOrder",method = RequestMethod.GET)
    @ResponseBody
    public List<StatisticsDayOrderView> getDayOrder(@RequestParam("year") int year, @RequestParam("month") int month) {
        List<StatisticsDayOrderView> statisticsOrderDayViews = statisticsOrderService.getDayOrder(year, month);
        return statisticsOrderDayViews;
    }

    @RequestMapping(value = "getDayOrderPaid",method = RequestMethod.GET)
    @ResponseBody
    public List<StatisticsDayOrderPaidView> getDayOrderPaid(@RequestParam("year") int year, @RequestParam("month") int month) {
        List<StatisticsDayOrderPaidView> statisticsPaidOrderDayViews = statisticsOrderService.getDayOrderPaid(year, month);
        return statisticsPaidOrderDayViews;
    }


    @RequestMapping(value = "getMonthOrder",method = RequestMethod.GET)
    @ResponseBody
    public List<StatisticsMonthOrderView> getMonthOrder(@RequestParam("year") int year) {
        List<StatisticsMonthOrderView> statisticsOrderMonthViews = statisticsOrderService.getMonthOrder(year);
        return statisticsOrderMonthViews;
    }

    @RequestMapping(value = "getMonthOrderPaid",method = RequestMethod.GET)
    @ResponseBody
    public List<StatisticsMonthOrderPaidView> getMonthOrderPaid(@RequestParam("year") int year) {
        List<StatisticsMonthOrderPaidView> statisticsPaidOrderMonthViews = statisticsOrderService.getMonthOrderPaid(year);
        return statisticsPaidOrderMonthViews;
    }


    @RequestMapping(value = "getDayMoney",method = RequestMethod.GET)
    @ResponseBody
    public List<StatisticsDayMoneyView> getDayMoney(@RequestParam("year") int year, @RequestParam("month") int month) {
        List<StatisticsDayMoneyView> statisticsDayMoneyViews = statisticsMoneyService.getDayMoney(year, month);
        return statisticsDayMoneyViews;
    }

    @RequestMapping(value = "getDayMoneyPaid",method = RequestMethod.GET)
    @ResponseBody
    public List<StatisticsDayMoneyPaidView> getDayMoneyPaid(@RequestParam("year") int year, @RequestParam("month") int month) {
        List<StatisticsDayMoneyPaidView> statisticsDayMoneyPaidViews = statisticsMoneyService.getDayMoneyPaid(year, month);
        return statisticsDayMoneyPaidViews;
    }


    @RequestMapping(value = "getMonthMoney",method = RequestMethod.GET)
    @ResponseBody
    public List<StatisticsMonthMoneyView> getMonthMoney(@RequestParam("year") int year) {
        List<StatisticsMonthMoneyView> statisticsMonthMoneyViews = statisticsMoneyService.getMonthMoney(year);
        return statisticsMonthMoneyViews;
    }

    @RequestMapping(value = "getMonthMoneyPaid",method = RequestMethod.GET)
    @ResponseBody
    public List<StatisticsMonthMoneyPaidView> getMonthMoneyPaid(@RequestParam("year") int year) {
        List<StatisticsMonthMoneyPaidView> statisticsMonthMoneyPaidViews = statisticsMoneyService.getMonthMoneyPaid(year);
        return statisticsMonthMoneyPaidViews;
    }


    @RequestMapping(value = "salesCharts", method = RequestMethod.GET)
    public String salesCharts(@ModelAttribute("monthQuery") SalesChartsQuery monthQuery, ModelMap modelMap, HttpServletRequest request) {
        if (monthQuery.getYear() == 0) {
            Calendar ca = Calendar.getInstance();
            monthQuery.setYear(ca.get(Calendar.YEAR));
        }
        modelMap.addAttribute("monthQuery", monthQuery);
        return "statistics/productsSalesCharts";
    }

    @RequestMapping(value = "pageSalesCharts", method = RequestMethod.POST)
    @ResponseBody
    public Page<HotSalesVo> pageSalesCharts(@RequestBody HotSalesQuery hotSalesQuery, HttpServletRequest request) {
        //添加日志
//		systemLogsService.log(request, "查看了热门销售统计列表！");
//		return salesChartsService.hotSales(hotSalesQuery);
        return null;
    }

    @RequestMapping(value = "pageProductsYear", method = RequestMethod.POST)
    @ResponseBody
    public Page<SalesYearVo> pageProductsYear(@RequestBody SalesChartsQuery monthQuery, HttpServletRequest request) {
        //添加日志
//		systemLogsService.log(request, "查看了销售商品统计列表! ");
//		return salesChartsService.pageProductsYear(monthQuery);
        return null;
    }

    @RequestMapping(value = "hotSales", method = RequestMethod.GET)
    public String hotSales(@ModelAttribute("hotQuery") HotSalesQuery hotQuery, ModelMap modelMap, HttpServletRequest request) {
        if (hotQuery.getTopNum() == 0) {
            hotQuery.setTopNum(5);
        }
        modelMap.addAttribute("hotQuery", hotQuery);
        return "statistics/hotSales";
    }

    @RequestMapping(value = "pageHotSales", method = RequestMethod.POST)
    @ResponseBody
    public Page<HotSalesVo> pageHotSales(@RequestBody HotSalesQuery hotSalesQuery, HttpServletRequest request) {
        //添加日志
//		systemLogsService.log(request, "查看了热门销售统计列表！");
//		return salesChartsService.hotSales(hotSalesQuery);
        return null;
    }

    @GetMapping(value = "billStatistics")
    public String billStatistics() {
        return "statistics/billStatistics";
    }

    @GetMapping(value = "getDayBill")
    @ResponseBody
    public List<StatisticsDayBillView> getDayBill(@RequestParam("year") int year, @RequestParam("month") int month) {

        return statisticsBillService.getDayBill(year, month);
    }

    @GetMapping(value = "getMonthBill")
    @ResponseBody
    public List<StatisticsDayBillView> getMonthBill(@RequestParam("year") int year) {

        return statisticsBillService.getMonthBill(year);
    }
}
