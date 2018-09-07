/**
 *
 */
package com.yunxin.cb.pay.service.impl;

import com.alibaba.fastjson.JSON;
import com.jpay.ext.kit.PaymentKit;
import com.jpay.vo.AjaxResult;
import com.jpay.weixin.api.WxPayApi;
import com.jpay.weixin.api.WxPayApiConfigKit;
import com.yunxin.cb.mall.entity.Payment;
import com.yunxin.cb.mall.mapper.OrderMapper;
import com.yunxin.cb.mall.mapper.PaymentMapper;
import com.yunxin.cb.mall.mapper.ProductReturnMapper;
import com.yunxin.cb.pay.entity.WxPayBean;
import com.yunxin.cb.pay.service.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gws
 */
@Service("wxPayService")
@Transactional
public class WxPayServiceImpl implements PayService {

    private static Logger logger = LoggerFactory.getLogger(PayService.class);

    @Resource
    private PaymentMapper paymentMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private ProductReturnMapper productReturnMapper;
    private static String notify_url = "";
    private AjaxResult result = new AjaxResult();
    @Autowired
    private WxPayBean wxPayBean;

    /**
     * 微信APP支付
     */
    public AjaxResult appPay(Payment payment) {
        Map<String, String> params = WxPayApiConfigKit.getWxPayApiConfig()
                .setAttach("")//附加数据,在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
                .setBody("") //商品描述
                .setSpbillCreateIp(payment.getUserIPAddress())//ip
                .setTotalFee(String.valueOf(payment.getPayAmount()))//支付金额
                .setTradeType(WxPayApi.TradeType.APP)
                .setNotifyUrl(notify_url)
                .setOutTradeNo(payment.getOrder().getOrderCode()) //订单编码
                .build();
        String xmlResult = WxPayApi.pushOrder(false, params);
        logger.info(xmlResult);
        Map<String, String> resultMap = PaymentKit.xmlToMap(xmlResult);

        String return_code = resultMap.get("return_code");
        String return_msg = resultMap.get("return_msg");
        if (!PaymentKit.codeIsOK(return_code)) {
            logger.info(xmlResult);
            result.addError(return_msg);
            return result;
        }
        String result_code = resultMap.get("result_code");
        if (!PaymentKit.codeIsOK(result_code)) {
            logger.info(xmlResult);
            result.addError(return_msg);
            return result;
        }
        // 以下字段在return_code 和result_code都为SUCCESS的时候有返回
        String prepay_id = resultMap.get("prepay_id");
        //封装调起微信支付的参数 https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_12
        Map<String, String> packageParams = new HashMap<String, String>();
        packageParams.put("appid", WxPayApiConfigKit.getWxPayApiConfig().getAppId());
        packageParams.put("partnerid", WxPayApiConfigKit.getWxPayApiConfig().getMchId());
        packageParams.put("prepayid", prepay_id);
        packageParams.put("package", "Sign=WXPay");
        packageParams.put("noncestr", System.currentTimeMillis() + "");
        packageParams.put("timestamp", System.currentTimeMillis() / 1000 + "");
        String packageSign = PaymentKit.createSign(packageParams, WxPayApiConfigKit.getWxPayApiConfig().getPaternerKey());
        packageParams.put("sign", packageSign);
        String jsonStr = JSON.toJSONString(packageParams);
        logger.info("最新返回apk的参数:" + jsonStr);
        paymentMapper.insert(payment);
        result.success(jsonStr);
        return result;
    }

    @Override
    public String payCallBack(Map<String, String> params) {
        //String appid  = params.get("appid");
//		//商户号
//		String mch_id  = params.get("mch_id");
        String result_code = params.get("result_code");
//		String openId      = params.get("openid");
//		//交易类型
//		String trade_type      = params.get("trade_type");
//		//付款银行
//		String bank_type      = params.get("bank_type");
//		// 总金额
//		String total_fee     = params.get("total_fee");
//		//现金支付金额
//		String cash_fee     = params.get("cash_fee");
//		// 微信支付订单号
//		String transaction_id      = params.get("transaction_id");
//		// 商户订单号
//		String out_trade_no      = params.get("out_trade_no");
//		// 支付完成时间，格式为yyyyMMddHHmmss
//		String time_end      = params.get("time_end");

        /////////////////////////////以下是附加参数///////////////////////////////////

        String attach = params.get("attach");
//		String fee_type      = params.get("fee_type");
//		String is_subscribe      = params.get("is_subscribe");
//		String err_code      = params.get("err_code");
//		String err_code_des      = params.get("err_code_des");
        // 注意重复通知的情况，同一订单号可能收到多次通知，请注意一定先判断订单状态
        // 避免已经成功、关闭、退款的订单被再次更新
//		Order order = Order.dao.getOrderByTransactionId(transaction_id);
//		if (order==null) {
        if (PaymentKit.verifyNotify(params, WxPayApiConfigKit.getWxPayApiConfig().getPaternerKey())) {
            if (("SUCCESS").equals(result_code)) {
                //更新订单信息
                logger.warn("更新订单信息:" + attach);
                //发送通知等
                Map<String, String> xml = new HashMap<String, String>();
                xml.put("return_code", "SUCCESS");
                xml.put("return_msg", "OK");
                return PaymentKit.toXml(xml);
            }
        }
        return null;
    }

    @Override
    public String refund(Payment payment) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", wxPayBean.getAppId());
        params.put("mch_id", wxPayBean.getMchId());
        params.put("nonce_str", System.currentTimeMillis() + "");
        params.put("out_trade_no", payment.getOrder().getOrderCode());
        params.put("out_refund_no", System.currentTimeMillis() + "");
        params.put("total_fee", String.valueOf(payment.getPayAmount()));
        params.put("refund_fee", String.valueOf(payment.getPayAmount()));
        params.put("sign", PaymentKit.createSign(params, wxPayBean.getPartnerKey()));
        String refund = WxPayApi.orderRefund(false, params, wxPayBean.getCertPath(), wxPayBean.getMchId());
        return refund;
    }

    @Override
    public String refundCallBack(Map<String, String> params) {
        return null;
    }

}
