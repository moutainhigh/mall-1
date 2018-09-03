/**
 *
 */
package com.yunxin.cb.mall.service.impl;

import com.jpay.vo.AjaxResult;
import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.mall.entity.Payment;
import com.yunxin.cb.mall.entity.ProductReturn;
import com.yunxin.cb.mall.entity.meta.*;
import com.yunxin.cb.mall.mapper.OrderMapper;
import com.yunxin.cb.mall.mapper.PaymentMapper;
import com.yunxin.cb.mall.mapper.ProductReturnMapper;
import com.yunxin.cb.mall.service.PaymentService;
import com.yunxin.cb.pay.service.PayService;
import com.yunxin.cb.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @author Aidy
 */
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    private static Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Resource
    private PaymentMapper paymentMapper;

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private ProductReturnMapper productReturnMapper;
    @Resource(name="aliPayService")
    private PayService aliPayService;
    @Resource(name="wxPayService")
    private PayService wxPayService;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public AjaxResult pay(Integer orderId, Integer customerId, ChannelType channelType, TerminalType terminalType) throws Exception {
        try {
            Order order = orderMapper.selectByOrderIdAndCustomerId(orderId, customerId);
            if (order != null) {
                Payment payment = new Payment();
                payment.setOrder(order);
                String payFlowCode = "PAY" + DateUtils.getSeriNo();
                payment.setChannelType(channelType);
                payment.setOrderId(order.getOrderId());
                payment.setPayFlowCode(payFlowCode);
                payment.setCustomerId(customerId);
                payment.setPayAmount(order.getFeeTotal());
                payment.setCreateTime(new Date());
                payment.setPayType(PayType.PAYMENT);
                payment.setPayState(PayState.PROCESSING);
                payment.setPayNotifyState(PayNotifyState.NOT_NOTIFY);
                // 组织支付参数、加密，发送支付请求(根据支付通道选择相应的方法 方法计3个)。支付订单编号用payId
                if (channelType == ChannelType.ALIPAY) {// 支付宝
                    if (terminalType == TerminalType.APP) {
                        return aliPayService.appPay(payment);
                    }
                } else if (channelType == ChannelType.WXPAY) { //微信
                    if (terminalType == TerminalType.APP) {
                        return wxPayService.appPay(payment);
                    }
                }
            } else {
                return new AjaxResult().addError("查询不到对应的订单");
            }

        } catch (Exception e) {
            return new AjaxResult().addError("代码异常");
        }
        return null;
    }


    /**
     * 支付回调
     * @param params
     * @return
     */
    public String payCallBack(Map<String, String> params, ChannelType channelType) {
        if (channelType == ChannelType.ALIPAY) {// 支付宝
            return aliPayService.payCallBack(params);
        } else if (channelType == ChannelType.WXPAY) { //微信
            return wxPayService.payCallBack(params);
        }
        return null;
    }

    /**
     * 退款
     * @param returnId
     * @param channelType
     * @return
     * @throws Exception
     */
    public String refund(Integer returnId, ChannelType channelType) throws Exception {
        ProductReturn productReturn = productReturnMapper.selectByPrimaryKey(returnId);
        Order order = orderMapper.selectByPrimaryKey(productReturn.getOrderId());
        Payment payment = paymentMapper.selectByOrderId(order.getOrderId());
        if (null == payment) {
            throw new Exception("该订单无付款记录");
        }
        logger.info("用户提交退款请求 订单号：" + order.getOrderCode());
        Payment payment1 = new Payment();
        payment1.setChannelType(channelType);
        payment1.setOrder(order);
        payment1.setBatchNo(productReturn.getReturnCode());
        payment1.setPayFlowCode(payment.getPayFlowCode());
        payment1.setReturnCode(productReturn.getReturnCode());
        payment1.setCustomerId(payment.getCustomerId());
        payment1.setCreateTime(new Date());
        payment1.setPayType(PayType.REIMBURSE);
        payment1.setPayState(PayState.PROCESSING);
        payment1.setPayNotifyState(PayNotifyState.NOT_NOTIFY);
        payment1.setPayAmount(productReturn.getRefundPrice());
        payment1.setBatchNum(1);
        // 组织支付参数、加密，发送支付请求(根据支付通道选择相应的方法 方法计3个)。支付订单编号用payId
        if (channelType == ChannelType.ALIPAY) {// 支付宝
            return aliPayService.refund(payment1);
        } else if (channelType == ChannelType.WXPAY) { //微信
            return wxPayService.refund(payment1);
        }
        return null;
    }

    /**
     * 退款回调
     * @param params
     * @return
     */
    public String refundCallBack(Map<String, String> params, ChannelType channelType) {
        if (channelType == ChannelType.ALIPAY) {// 支付宝
            return aliPayService.refundCallBack(params);
        } else if (channelType == ChannelType.WXPAY) { //微信
            return wxPayService.refundCallBack(params);
        }
        return null;
    }

    private void setPaymentSate(Payment payment, String notify_time, String success_num) {
        payment.setPayState(PayState.PROCESSED_SUCCESS);
        payment.setPayNotifyState(PayNotifyState.NOTIFY_SUCCESS);
        payment.setNotifyTime(DateUtils.parseDate(notify_time));
        payment.setBatchNum(Integer.valueOf(success_num));
        paymentMapper.insert(payment);

        ProductReturn productReturn = productReturnMapper.selectByReturnCode(payment.getReturnCode());
        if (productReturn.getRefundOnly()) {
            productReturn.setReturnRefundState(ReturnRefundState.REFUNDED);
        } else {
            productReturn.setReturnRefundState(ReturnRefundState.RETURNED_REFUND);
        }
        logger.info("支付宝退款成功！更新退款号：" + productReturn.getReturnCode() + " 状态为成功！通知时间：" + notify_time);
    }


    @Override
    public Payment addPay(Payment payment) {
        payment.setCreateTime(new Date());
        paymentMapper.insert(payment);
        return payment;
    }





}
