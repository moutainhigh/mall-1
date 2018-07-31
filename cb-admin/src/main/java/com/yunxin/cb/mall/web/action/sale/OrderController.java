package com.yunxin.cb.mall.web.action.sale;

import com.yunxin.cb.mall.entity.*;
import com.yunxin.cb.mall.entity.meta.AuditState;
import com.yunxin.cb.mall.entity.meta.ChannelType;
import com.yunxin.cb.mall.entity.meta.OrderState;
import com.yunxin.cb.mall.exception.BusinessNotInStockException;
import com.yunxin.cb.mall.exception.CommonException;
import com.yunxin.cb.mall.exception.ProductBarterException;
import com.yunxin.cb.mall.exception.ProductReturnException;
import com.yunxin.cb.mall.service.IOrderService;
import com.yunxin.cb.mall.service.IPayService;
import com.yunxin.cb.mall.web.vo.JsonResult;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.core.persistence.PageSpecification;
import com.yunxin.core.util.LogicUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;

/**
 * @author gonglei
 */
@Controller
@RequestMapping(value = "/sale")
public class OrderController {

    @Resource
    private IOrderService orderService;

    @Resource
    private IPayService payService;

    @RequestMapping(value = "orders")
    public String orderForms(HttpSession session,ModelMap modelMap) {
        Seller seller = (Seller) session.getAttribute(SecurityConstants.LOGIN_SELLER);
        modelMap.put("seller",seller);
        return "sale/orders";
    }

    @RequestMapping(value = "pageOrders", method = RequestMethod.POST)
    @ResponseBody
    public Page<Order> pageOrders(@RequestBody PageSpecification query, HttpServletRequest request) {
        Page<Order> page = orderService.pageOrders(query, null);
        return page;
    }

    @RequestMapping(value = "getOrderDetailById",method = RequestMethod.GET)
    public String getOrderDetailById(@RequestParam("orderId") int orderId,ModelMap modelMap){
        Order order = orderService.getOrderDetailById(orderId);
        if(null != order){
            if(LogicUtils.isNotNullAndEmpty(order.getOriginOrderCode())){
                Order oldOrder = orderService.getOrderDetailByCode(order.getOriginOrderCode());
                modelMap.put("oldOrder",oldOrder);
            }
        }
        modelMap.put("order",order);
        return "sale/orderDetail";
    }

    @ResponseBody
    @RequestMapping(value = "changeOrderPrice",method = RequestMethod.POST)
    public boolean changeOrderPrice(@RequestParam("orderId") int orderId,double changePrice) {
        Order order =orderService.changeOrderPrice(orderId, changePrice);
        if(null != order){
            return true;
        }else{
            return false;
        }
    }

    @ResponseBody
    @RequestMapping(value = "changeDelivery",method = RequestMethod.POST)
    public boolean changeDelivery(int orderId,DeliveryAddress deliveryAddress) {
        Order order =orderService.changeOrderDelivery(orderId, deliveryAddress);
        if(null != order){
            return true;
        }else{
            return false;
        }
    }

    @ResponseBody
    @RequestMapping(value = "editOrderState", method = RequestMethod.GET)
    public String editOrderState(@RequestParam("orderId") int orderId,@RequestParam("status") OrderState status) {

        return  orderService.updateOrderState(status,orderId);
    }

    @ResponseBody
    @RequestMapping(value = "editOrderLogistic",method = RequestMethod.POST)
    public boolean editOrderLogistic(@RequestParam("orderId") int orderId,@RequestParam("logisticId") int logisticId,@RequestParam("courierNumber") String courierNumber) {

        Order order= orderService.editOrderLogistic(orderId,logisticId,courierNumber);
        if(null != order){
            return true;
        }
        return false;
    }

    @RequestMapping(value = "returnOrders",method = RequestMethod.GET)
    public String returnOrders(HttpSession session,ModelMap modelMap) {
        Seller seller = (Seller) session.getAttribute(SecurityConstants.LOGIN_SELLER);
        modelMap.put("seller",seller);
        return "sale/returnOrders";
    }

    @ResponseBody
    @RequestMapping(value = "pageReturnOrders",method = RequestMethod.POST)
    public Page<ProductReturn> pageReturnOrders(@RequestBody PageSpecification query, HttpServletRequest request) {

        Page<ProductReturn> page = orderService.pageReturnOrders(query);
        return page;
    }

    @RequestMapping(value = "barterOrders",method = RequestMethod.GET)
    public String barterOrders(HttpSession session,ModelMap modelMap) {
        Seller seller = (Seller) session.getAttribute(SecurityConstants.LOGIN_SELLER);
        modelMap.put("seller",seller);
        return "sale/barterOrders";
    }

    @ResponseBody
    @RequestMapping(value = "pageBarterOrders",method = RequestMethod.POST)
    public Page<ProductBarter> pageBarterOrders(@RequestBody PageSpecification query, HttpServletRequest request) {
        Page<ProductBarter> page = orderService.pageBarterOrders(query);
        return page;
    }


    @ResponseBody
    @RequestMapping(value = "confirmReceivedBarterProduct",method = RequestMethod.GET)
    public JsonResult confirmReceivedBarterProduct(@RequestParam("barterId") int barterId) {
        JsonResult jsonResult =new JsonResult();
        try {
            orderService.confirmReceivedBarterProduct(barterId);
            jsonResult.setStatus(true);
        } catch (ProductBarterException e) {
            jsonResult.setStatus(false);
            jsonResult.setError(e.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "productBarterAudit",method = RequestMethod.GET)
    @ResponseBody
    public boolean productBarterAudit(@RequestParam("barterId") int barterId, @RequestParam("auditState") AuditState auditState, @RequestParam("auditRemark") String auditRemark) {
        try {
            orderService.productBarterAudit(barterId, auditState, auditRemark);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping(value = "productBarter",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult productBarter(@RequestParam("orderItemId") int orderItemId){
        JsonResult jsonResult=new JsonResult();
        try {
            boolean result= orderService.productBarter(orderItemId);
            jsonResult.setStatus(result);
        } catch (BusinessNotInStockException e) {
            jsonResult.setStatus(false);
            jsonResult.setMsg(e.getMessage());
        } catch (ProductBarterException e) {
            jsonResult.setStatus(false);
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }


    @RequestMapping(value = "productReturnAudit",method = RequestMethod.GET)
    @ResponseBody
    public boolean productReturnAudit(@RequestParam("returnId") int returnId, @RequestParam("auditState") AuditState auditState,@RequestParam("auditRemark") String auditRemark) {
        try {
            orderService.productReturnAudit(returnId, auditState, auditRemark);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @ResponseBody
    @RequestMapping(value = "confirmReceivedReturnProduct",method = RequestMethod.GET)
    public JsonResult confirmReceivedReturnProduct(@RequestParam("returnId") int returnId) {
        JsonResult jsonResult =new JsonResult();
        try {
            orderService.confirmReceivedReturnProduct(returnId);
            jsonResult.setStatus(true);
        } catch (ProductReturnException e) {
            jsonResult.setStatus(false);
            jsonResult.setError(e.getMessage());
        }
        return jsonResult;
    }


    @RequestMapping(value = "toReturnRefundOrder",method = RequestMethod.GET)
    public String toReturnRefundOrder(@RequestParam("returnCode") String returnCode,ModelMap modelMap) {
        ProductReturn productReturn = orderService.getProductReturnByReturnCode(returnCode);
        modelMap.put("productReturn", productReturn);
        return "sale/returnOrder";
    }

    @RequestMapping(value = "returnRefundOrder",method = RequestMethod.POST)
    public String returnRefundOrder(@Valid @ModelAttribute("productReturn") ProductReturn productReturn, BindingResult result, ModelMap modelMap, HttpServletRequest request, Locale locale) {
        if (result.hasErrors()) {
            return toReturnRefundOrder(productReturn.getReturnCode(), modelMap);
        }
        String reqPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        String orderCode = orderService.returnRefundOrder(productReturn);

        String payHtml = null;
        try {
            payHtml = payService.refund(reqPath, orderCode, ChannelType.ALIPAY);
            modelMap.addAttribute("payHtml",payHtml);
            return "refund/submitRefund";
        } catch (CommonException e) {
            return "redirect:../common/failure.do?reurl=sale/returnOrders.do&msgContent="+e.getMessage();
        }
    }

    @RequestMapping(value = "loanOrders",method = RequestMethod.GET)
    public String loanOrders(HttpSession session,ModelMap modelMap) {
        Seller seller = (Seller) session.getAttribute(SecurityConstants.LOGIN_SELLER);
        modelMap.put("seller",seller);
        return "sale/loanOrders";
    }

    @ResponseBody
    @RequestMapping(value = "pageLoanOrders",method = RequestMethod.POST)
    public Page<OrderLoanApply> pageLoanOrders(@RequestBody PageSpecification query, HttpServletRequest request) {
        Page<OrderLoanApply> page = orderService.pageLoanOrders(query);
        return page;
    }

    @RequestMapping(value = "orderLoanApplyAudit",method = RequestMethod.GET)
    @ResponseBody
    public boolean orderLoanApplyAudit(@RequestParam("loanId") int loanId, @RequestParam("auditState") AuditState auditState,@RequestParam("auditRemark") String auditRemark) {
        try {
            return orderService.orderLoanApplyAudit(loanId, auditState, auditRemark);
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping(value = "underLinePayConfirm",method = RequestMethod.GET)
    @ResponseBody
    public boolean underLinePayConfirm(@RequestParam("orderId") int orderId,ModelMap modelMap){
        try {
            return orderService.underLinePayConfirm(orderId);
        } catch (Exception e) {
            return false;
        }
    }

}

