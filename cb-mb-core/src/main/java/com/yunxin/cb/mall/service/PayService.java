/**
 *
 */
package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.Payment;
import com.yunxin.cb.mall.entity.meta.ChannelType;

import java.util.Map;

/**
 * @author Aidy
 */
public interface PayService {

    // 支付
    public String pay(String orderCode, Customer customer, ChannelType channelType, String servicePath) throws Exception;

    /****
     * 支付宝回调
     * @param params
     * @return
     */
    public boolean callBackAlipay(Map<String, String> params);

    /***
     * 退款
     * @param servicePath
     * @param orderCode
     * @param channelType
     * @return
     * @throws Exception
     */
    public String refund(String servicePath, String orderCode, ChannelType channelType) throws Exception;

    /***
     * 支付宝退款回调
     * @param params
     * @return
     */
    public boolean refundAlipayCallBack(Map<String, String> params) throws Exception;

    public Payment getById(int payId);

    public Payment addPay(Payment payment);

}
