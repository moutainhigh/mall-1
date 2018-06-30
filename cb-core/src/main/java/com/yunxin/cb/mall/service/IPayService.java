/**
 *
 */
package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.Payment;
import com.yunxin.cb.mall.entity.meta.ChannelType;
import com.yunxin.cb.mall.exception.CommonException;
import com.yunxin.cb.mall.query.PayQuery;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * @author Aidy
 */
public interface IPayService {

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
     * @throws CommonException
     */
    public String refund(String servicePath, String orderCode, ChannelType channelType) throws CommonException;

    /***
     * 支付宝退款回调
     * @param params
     * @return
     */
    public boolean refundAlipayCallBack(Map<String, String> params) throws CommonException;

    public Payment getById(int payId);

    public Payment addPay(Payment payment);

    public Page<Payment> pagePayment(PayQuery query);

    public long countPayment(PayQuery query);

    public boolean checkPayFlowCode(String payFlowCode);

    Page<Payment> pagePaymentforGoodsReutrn(PayQuery query);

    long pagePaymentforGoodsReutrnCount(PayQuery query);

    /**
     * 更改订单为全额付款
     **/
    public Payment fullPaid(int orderId);

    /**
     * 14.12.22
     *
     * @param orderId
     * @return
     */
    Double getPayByOrderId(int orderId);
}
