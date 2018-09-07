/**
 *
 */
package com.yunxin.cb.pay.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.jpay.alipay.AliPayApi;
import com.jpay.vo.AjaxResult;
import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.mall.entity.Payment;
import com.yunxin.cb.mall.entity.meta.*;
import com.yunxin.cb.mall.mapper.OrderMapper;
import com.yunxin.cb.mall.mapper.PaymentMapper;
import com.yunxin.cb.mall.mapper.ProductReturnMapper;
import com.yunxin.cb.pay.entity.AliPayBean;
import com.yunxin.cb.pay.service.PayService;
import com.yunxin.cb.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @author gws
 */
@Service("aliPayService")
@Transactional
public class AliPayServiceImpl implements PayService {

    private static Logger logger = LoggerFactory.getLogger(PayService.class);

    @Resource
    private PaymentMapper paymentMapper;

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private ProductReturnMapper productReturnMapper;
    @Autowired
    AliPayBean aliPayBean;

    private static String notify_url = "/alipay/notify_url";
    private AjaxResult result = new AjaxResult();


    public AjaxResult appPay(Payment payment){
        try {
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
            model.setBody("我是测试数据-By Javen"); //订单描述
            model.setSubject("App支付测试-By Javen"); //订单名称
            model.setOutTradeNo(payment.getOrder().getOrderCode()); //订单编码
            model.setTimeoutExpress("30m"); //超时
            model.setTotalAmount(String.valueOf(payment.getPayAmount()));//支付金额
            model.setPassbackParams("callback params"); //回调参数，回调原样返回
            model.setProductCode("QUICK_MSECURITY_PAY"); //该参数固定
            String orderInfo = AliPayApi.startAppPay(model, aliPayBean.getDomain() + notify_url);
            result.success(orderInfo);
            paymentMapper.insert(payment);
            return result;
        } catch (AlipayApiException e) {
            logger.error("error is "+e);
            result.addError("system error:"+e.getMessage());
            return result;
        }
    }

    @Override
    public String payCallBack(Map<String, String> params) {
        try {
            logger.info("支付回调验证开始。。。");
            AliPayBean aliPayBean = new AliPayBean();
            // 计算得出通知验证结果
            boolean verify_result = AlipaySignature.rsaCheckV1(params, aliPayBean.getPublicKey(), "UTF-8", "RSA2");
            logger.info("支付回调验证结果：" + verify_result);
            // 验证成功
            if (verify_result) {
                //回调内容
                String msg = params.get("body");
                // 商户订单号
                String out_trade_no = params.get("out_trade_no");
                // 交易流水号
                String trade_no = params.get("trade_no");
                // 交易金额
                String total_fee = params.get("total_fee");
                // 买家付宝账号
                String buyer_email = params.get("buyer_email");
                // 交易状态
                String trade_status = params.get("trade_status");
                // 购买时间
                String notify_time = params.get("notify_time");
                //错误码
                String error_code = params.get("error_code");
                Order order = orderMapper.selectByOrderCode(out_trade_no);
                Payment payment = paymentMapper.selectByOrderIdAndPayState(order.getOrderId(), PayState.PROCESSING);
                if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
                    /**已有支付记录**/
                    if (order.getOrderState() == OrderState.PAID_PAYMENT && payment.getChannelType() == ChannelType.ALIPAY) {
                        logger.info("已有支付记录！支付宝前台同步回调已经执行，后台回调不再次做订单处理");
                    } else {
                        logger.info("支付宝支付开始。。。");
                        Date curDate = new Date();
                        payment.setBuyerAccount(buyer_email);
                        payment.setPayFlowCode(trade_no);
                        payment.setPayNotifyState(PayNotifyState.NOTIFY_SUCCESS);
                        payment.setNotifyTime(DateUtils.parseTimestamp(notify_time));
                        //支付信息
                        if (payment.getPayState() == PayState.PROCESSING) {/**0:处理中1:处理成功2:处理失败3:部分成功*/
                            if (payment.getErrorCode() != null) {
                                payment.setErrorCode(error_code);
                                payment.setPayState(!payment.getErrorCode().equals("00") ? PayState.PROCESSED_FAILURE : PayState.PROCESSED_SUCCESS);//'0：处理中1：处理成功2：处理失败'
                            } else if (payment.getErrorCode() == null || payment.getErrorCode().equals("00")) {
                                payment.setPayState(PayState.PROCESSED_SUCCESS);
                                logger.info("支付宝支付处理成功！");
                            }
                            payment.setCompleteTime(curDate);
                        }

                        order.setOrderState(OrderState.PAID_PAYMENT);
                        order.setUpdateTime(curDate);
                        //order.setPaymentType(PaymentType.ALIPAY);
                        order.setPaymentSequence(trade_no);
                        order.setPaymentTime(curDate);
                        //找到当前用户，更新用户积分，根据当前积分，更新用户等级 并在积分记录表插入数据
                        //rankService.updateRankAndIntegral(payment.getCustomer().getCustomerId(), Double.valueOf(total_fee));
                        logger.info("支付宝支付成功！更新用户等级并在积分记录表插入数据");
                    }
                    logger.info("支付回调验证结束。。。");
                    return "success";
                } else {
                    payment.setPayNotifyState(PayNotifyState.NOTIFY_FAILURE);
                    payment.setNotifyTime(DateUtils.parseTimestamp(notify_time));
                    payment.setPayState(PayState.PROCESSED_FAILURE);
                    payment.setErrorCode(error_code);
                    logger.info("支付回调验证状态失败。。。失败信息，error_code=" + error_code);
                    return "failure";
                }
            } else {
                logger.info("alipay verify fail");
                logger.info("支付回调验证失败。。。");
                return "failure";
            }
        } catch (AlipayApiException e) {
            logger.error("error is "+e);
            return "failure";
        }
    }

    @Override
    public String refund(Payment payment) {
        try {
            AlipayTradeRefundModel model = new AlipayTradeRefundModel();
            model.setOutTradeNo(payment.getOrder().getOrderCode());
            model.setTradeNo(payment.getPayFlowCode());
            model.setRefundAmount(String.valueOf(payment.getPayAmount()));
            model.setRefundReason("正常退款");
            return AliPayApi.tradeRefund(model);
        } catch (AlipayApiException e) {
            logger.error("error is "+e);
        }
        paymentMapper.insert(payment);
        return null;
    }

    @Override
    public String refundCallBack(Map<String, String> params) {
        try {
            logger.info("支付宝退款回调验证开始。。。");
            AliPayBean aliPayBean = new AliPayBean();
            // 计算得出通知验证结果
            boolean verify_result = AlipaySignature.rsaCheckV1(params, aliPayBean.getPublicKey(), "UTF-8", "RSA2");
            logger.info("支付宝退款回调验证结果：" + verify_result);
            // 验证成功
            if (verify_result) {
                //业务逻辑
            } else {
                logger.info("alipay verify fail");
                logger.info("支付宝退款回调验证失败。。。");
                return "failure";
            }
        } catch (AlipayApiException e) {
            logger.error("error is "+e);
            return "failure";
        }
        return null;
    }


}
