package com.yunxin.cb.mall.web.action.console;

import com.yunxin.cb.console.service.ISecurityService;
import com.yunxin.cb.mall.entity.Complaint;
import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.mall.entity.ProductEvaluate;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.cb.mall.service.*;
import com.yunxin.cb.monitor.service.IConcentService;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.cb.cms.service.IArticleService;
import com.yunxin.cb.cms.service.IProgramaService;
import com.yunxin.cb.console.service.ISecurityService;
import com.yunxin.cb.mall.entity.Complaint;
import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.mall.entity.ProductEvaluate;
import com.yunxin.cb.mall.entity.meta.PublishState;
import com.yunxin.cb.mall.service.*;
import com.yunxin.cb.monitor.service.IConcentService;
import org.slf4j.Logger; import org.slf4j.LoggerFactory;
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
@RequestMapping(value = "/console")
@SessionAttributes({SecurityConstants.LOGIN_SESSION})
public class ConsoleController {

    protected static Logger logger = LoggerFactory.getLogger(ConsoleController.class);

    @Resource
    private ISecurityService securityService;

    @Resource
    private ISellerService sellerService;

    @Resource
    private ICatalogService catalogService;

    @Resource
    private IOrderService orderService;

    @Resource
    private ICustomerService customerService;

    @Resource
    private IEvaluateService evaluateService;

    @Resource
    private IComplaintService complaintService;

    @Resource
    private ICommodityService commodityService;

    @Resource
    private IProductService productService;

    @Resource
    private IArticleService articleService;

    @Resource
    private IProgramaService programaService;

    @Resource
    private IConcentService concentService;

    @Resource
    private IRankService rankService;

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
        double orderMonthTotalPrice = orderService.getOrderTotalPriceSum(startDate, endDate);
        double orderPayedTotalPrice = orderService.getPayedOrderTotalPriceSum(startDate, endDate);
        long orderMonthCount = orderService.getOrderCount(startDate, endDate);
        long orderMonthPayedCount = orderService.getOrderCount(startDate, endDate);
        modelMap.addAttribute("orderMonthTotalPrice", orderMonthTotalPrice);
        modelMap.addAttribute("orderMonthPayedTotalPrice", orderPayedTotalPrice);
        modelMap.addAttribute("orderMonthCount", orderMonthCount);
        modelMap.addAttribute("orderMonthPayedCount", orderMonthPayedCount);
        // 商品分类
//			Long categoryNumber = catalogService.getCategoryNumber();
//			session.setAttribute("categoryNumber", categoryNumber);
//			//二级商品分类名称和其下数量
//			Map<String, Long> categoryNum = catalogService.getCategoryNum();
//			session.setAttribute("categoryNum", categoryNum);
//
//			// 订单率统计
//			String orderRate = new DecimalFormat("##.###%").format(orderFormService.getOrderRate());
//			session.setAttribute("orderRate", orderRate);
//			//统计 已支付，未支付，已下单，已取消数量
//			Map<String, Long> orderFormNum = orderFormService.getOrderFormNumbers();
//			session.setAttribute("orderFormNum", orderFormNum);
//



        List<Order> orders = orderService.getLastedOrders(20);
        modelMap.addAttribute("orders", orders);

        List<ProductEvaluate> evaluates = evaluateService.pageProductEvaluates(new PageSpecification<ProductEvaluate>(1,30)).getContent();
        modelMap.addAttribute("evaluates", evaluates);

        List<Complaint> complaints = complaintService.getLastedComplaints(30);
        modelMap.addAttribute("complaints", complaints);

        //统计 客户数量
        modelMap.addAttribute("customerCount", customerService.getCustomerCount());
        modelMap.addAttribute("customerCountMonthly",  customerService.countByCreateTimeBetween(startDate,endDate));
        modelMap.addAttribute("sellerCount", sellerService.getSellerCount());
        modelMap.addAttribute("commodityCount", commodityService.getCommodityCount());
        modelMap.addAttribute("commodityUpShelvesCount", commodityService.countByPublishState(PublishState.UP_SHELVES));
        modelMap.addAttribute("commodityUpShelvesPopularCount", commodityService.countByPublishStateAndPopular(PublishState.UP_SHELVES,true));
        modelMap.addAttribute("commodityUpShelvesRecommendCount", commodityService.countByPublishStateAndRecommend(PublishState.UP_SHELVES,true));
        modelMap.addAttribute("productCount", productService.getProductCount());
        modelMap.addAttribute("articleCount", articleService.getArticleCount());
        modelMap.addAttribute("channelCount", programaService.getArticleChannelCount());
        modelMap.addAttribute("programaCount", programaService.getProgramaCount());
        modelMap.addAttribute("subjectCount", programaService.getSubjectCount());
        modelMap.addAttribute("orderCount", orderService.getOrderCount());
        modelMap.addAttribute("orderCommodityCount", orderService.getDisctinctCommodityCount());
        modelMap.addAttribute("orderProductCount", orderService.getOrderProductCount());
        double orderTotalPrice=orderService.getOrderTotalPrice();
        modelMap.addAttribute("orderTotalPrice",orderTotalPrice );
        long payedCount=orderService.getPayedOrderCount();
        double orderAvgPrice=payedCount!=0?orderTotalPrice/payedCount:0;
        modelMap.addAttribute("orderAvgPrice", orderAvgPrice);
        modelMap.addAttribute("concentCount", concentService.getConcentCount());
        modelMap.addAttribute("deviceCount", concentService.getDeviceCount());
        return "console/dashboard";

    }
}
