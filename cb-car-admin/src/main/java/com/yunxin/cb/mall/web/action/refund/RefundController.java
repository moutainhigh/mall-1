package com.yunxin.cb.mall.web.action.refund;

import com.yunxin.cb.config.GlobalConfig;
import com.yunxin.cb.mall.entity.meta.ChannelType;
import com.yunxin.cb.mall.exception.CommonException;
import com.yunxin.cb.mall.service.IOrderService;
import com.yunxin.cb.mall.service.IPayService;
import com.yunxin.core.util.DateUtils;
import com.yunxin.cb.config.GlobalConfig;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.mall.entity.meta.ChannelType;
import com.yunxin.cb.mall.exception.CommonException;
import com.yunxin.cb.mall.service.IOrderService;
import com.yunxin.cb.mall.service.IPayService;
import com.yunxin.cb.pay.aliPay.AlipayConfig;
import com.yunxin.cb.pay.aliPay.AlipayCore;
import com.yunxin.cb.pay.aliPay.AlipayHelper;
import org.slf4j.Logger; import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Aidy_He on 16/1/31.
 */
@Controller
@RequestMapping("/refund")
public class RefundController {
    private static final Logger logger = LoggerFactory.getLogger(RefundController.class);

    private static String aliRefundPath = GlobalConfig.getProperty("alipay.log_path") + DateUtils.getCurrentDate() +"_alipay_refund.txt";

    @Resource
    private IOrderService orderService;

    @Resource
    private IPayService payService;

    @RequestMapping(value = "submitRefund", method = RequestMethod.POST)
    public String submitRefund(@RequestParam("orderCode") String orderCode, @RequestParam("channelType") ChannelType channelType, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

        String reqPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        try{
            String payHtml = payService.refund(reqPath, orderCode, channelType);
            model.addAttribute("payHtml",payHtml);
            return "refund/submitRefund";
        }catch (CommonException e){
            logger.error(e.getMessage());
            return "redirect:../common/failure.do?reurl=sale/returnOrders.do";
        }
    }

    @RequestMapping(value = "callBackRefundAlipayAsync", method = RequestMethod.POST)
    public String callBackRefundAlipayAsync(HttpServletRequest request,HttpServletResponse response,ModelMap model){
        try{
            AlipayCore.logInfo("支付宝退款异步回调开始。。。");
            // 获取支付宝GET过来反馈信息
            Map<String, String> params = AlipayHelper.buildCallBackParamMap(request.getParameterMap());
            AlipayCore.logInfo("支付宝退款异步回调的参数：" + params.toString());
            boolean flag = payService.refundAlipayCallBack(params);
            if(flag){
                AlipayCore.logInfo("支付宝退款异步回调的成功");
                return "redirect:../common/success.do?reurl=operation/returnOrders.do";
            }else{
                AlipayCore.logInfo("支付宝退款异步回调的失败");
                model.put("msgTitle", "支付宝退款失败");
                model.put("msgContent","支付宝退款异步回调的失败");
                return "redirect:../common/failure.do?reurl=sale/returnOrders.do";
            }
        }catch (CommonException e){
            AlipayCore.logInfo("支付宝退款异步回调异常：" + e);
            model.put("msgTitle", "支付宝退款异常");
            model.put("msgContent", "支付宝退款异步回调的异常:"+e);
            return "redirect:../common/failure.do?reurl=sale/returnOrders.do";
        }catch (Exception e){
            AlipayCore.logInfo("支付宝退款异步回调异常：" + e);
            model.put("msgTitle", "支付宝退款异常");
            model.put("msgContent", "支付宝退款异步回调的异常:"+e);
            return "redirect:../common/failure.do?reurl=sale/returnOrders.do";
        }

    }

}
