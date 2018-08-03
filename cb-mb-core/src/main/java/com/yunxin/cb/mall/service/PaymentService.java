/**
 *
 */
package com.yunxin.cb.mall.service;

import com.jpay.vo.AjaxResult;
import com.yunxin.cb.mall.entity.Payment;
import com.yunxin.cb.mall.entity.meta.ChannelType;
import com.yunxin.cb.mall.entity.meta.TerminalType;

import java.util.Map;

/**
 * @author Aidy
 */
public interface PaymentService {

    /**
     * 支付
     * @param orderId
     * @param customerId
     * @param channelType
     * @param terminalType PC,H5,APP
     * @return
     * @throws Exception
     */
    public AjaxResult pay(Integer orderId, Integer customerId, ChannelType channelType, TerminalType terminalType) throws Exception;

    /****
     * 支付回调
     * @param params
     * @return
     */
    public String payCallBack(Map<String, String> params, ChannelType channelType);
    /***
     * 退款
     * @param returnId
     * @param channelType
     * @return
     * @throws Exception
     */
    public String refund(Integer returnId, ChannelType channelType) throws Exception;

    /***
     * 支付退款回调
     * @param params
     * @return
     */
    public String refundCallBack(Map<String, String> params, ChannelType channelType) throws Exception;

//    public Payment getById(int payId);

    public Payment addPay(Payment payment);

}
