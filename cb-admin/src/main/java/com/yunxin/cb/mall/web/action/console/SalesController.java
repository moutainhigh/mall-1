package com.yunxin.cb.mall.web.action.console;

import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.console.service.ISecurityService;
import com.yunxin.cb.mall.vo.SalesData;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.cb.console.entity.User;
import com.yunxin.cb.console.service.ISecurityService;
import com.yunxin.cb.mall.vo.SalesData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.List;

/**
 * @author x001393
 *
 */
@Controller
@RequestMapping(value = "/console")
public class SalesController {
	
//	@Resource
//	private ISalesChartsService salesChartsService;

    @Resource
    private ISecurityService securityService;
	/**
	 * 统计月销售量和月销售额
	 * @param 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getSalesVolumeAndSalesAmount", method = RequestMethod.GET)
	@ResponseBody
	public List<SalesData> getSalesVolumeAndSalesAmount() {
		Calendar cal = Calendar.getInstance();
//		List<SalesData> salesDatas = salesChartsService.getSalesVolumeAndSalesAmount(cal.get(Calendar.YEAR));
//		return salesDatas;
        return null;
	}

    /**
     * 定时查询是否有新订单生成
     * 并判断是否拥有处理权限
     * @return
     */
    @RequestMapping(value = "getOrderForm", method = RequestMethod.GET)
    @ResponseBody
    public String getOrderForm(ModelMap modelMap, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(SecurityConstants.LOGIN_SESSION);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -30);
//        calendar.add(Calendar.HOUR_OF_DAY,-12);
        try {
//            return orderFormService.getOrderFormByStatus(menus,calendar.getTime());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
