/**
 *
 */
package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.config.Config;
import com.yunxin.cb.config.GlobalConfig;
import com.yunxin.cb.mall.entity.Customer;
import com.yunxin.cb.mall.entity.Order;
import com.yunxin.cb.mall.entity.Payment;
import com.yunxin.cb.mall.entity.ProductReturn;
import com.yunxin.cb.mall.entity.meta.*;
import com.yunxin.cb.mall.mapper.OrderMapper;
import com.yunxin.cb.mall.mapper.PaymentMapper;
import com.yunxin.cb.mall.mapper.ProductReturnMapper;
import com.yunxin.cb.mall.service.PayService;
import com.yunxin.cb.pay.aliPay.*;
import com.yunxin.cb.pay.unionPay.UnionPayConf;
import com.yunxin.cb.pay.unionPay.UnionPayUtils;
import com.yunxin.cb.util.DateUtils;
import com.yunxin.cb.util.LogicUtils;
import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Aidy
 */
@Service
@Transactional
public class PayServiceImpl implements PayService {

    private static Logger logger = LoggerFactory.getLogger(PayService.class);

    @Resource
    private PaymentMapper paymentMapper;

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private ProductReturnMapper productReturnMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String pay(String orderCode, Customer customer, ChannelType channelType, String servicePath) throws Exception {
        if (null == customer) {
            throw new NullPointerException("customer is null");
        }
        Order order = orderMapper.selectByOrderCode(orderCode);
        AlipayCore.logInfo("用户提交支付请求 订单号：" + order.getOrderCode() + " 用户登录账号：" + customer.getAccountName());
        try {
            Payment payment = new Payment();
            String payFlowCode = "PAY" + DateUtils.getSeriNo();
            payment.setChannelType(channelType.ordinal());
            payment.setOrderId(order.getOrderId());
            payment.setPayFlowCode(payFlowCode);
            payment.setCustomerId(customer.getCustomerId());
            payment.setPayAmount(order.getFeeTotal());
            payment.setCreateTime(new Date());
            payment.setPayType(PayType.PAYMENT.ordinal());
            payment.setPayState(PayState.PROCESSING.ordinal());
            payment.setPayNotifyState(PayNotifyState.NOT_NOTIFY.ordinal());
            String htmlText = null;
            // 组织支付参数、加密，发送支付请求(根据支付通道选择相应的方法 方法计3个)。支付订单编号用payId
            if (channelType == ChannelType.ALIPAY) {// 支付宝
                htmlText = aliPay(payment, servicePath);
            } else if (payment.getChannelType() == ChannelType.UNIONPAY.ordinal()) {
                //htmlText = unionPay(payment, request);
            }
            return htmlText;
        } catch (Exception e) {
            AlipayCore.logInfo("支付请求异常订单：" + order.getOrderCode() + " 错误信息：" + e);
            return null;
        }


    }

    @Override
    public Payment getById(int payId) {
        return paymentMapper.selectByPrimaryKey(payId);
    }

    // 支付宝支付请求
    protected String aliPay(Payment payment, String servicePath) {
        // 支付类型 必填，不能修改
        String payment_type = "1";
        // 服务器异步通知页面路径 //需http://格式的完整路径，不能加?id=123这类自定义参数
        String notify_url = servicePath + GlobalConfig.getProperty(Config.ALIPAY_NOTIFY_URL);

        // 页面跳转同步通知页面路径
        //需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/
        String return_url = servicePath + GlobalConfig.getProperty(Config.ALIPAY_RETURN_URL);

        // 卖家支付宝帐户 必填
        String seller_email = AlipayConfig.seller_email;

        // 商户订单号 商户网站订单系统中唯一订单号，必填
        String out_trade_no = payment.getOrder().getOrderCode();

        // 订单名称 可以为空
        String subject = "兜兜云厨商品";

        // 付款 必填
        String total_fee = String.valueOf(payment.getPayAmount());

        // 订单描述 可以为空
        String body = "";

        // 商品展示地址 需以http://开头的完整路径
        String show_url = "http://www.13906.com/member/orders.htm";

        // 防钓鱼时间戳 若要使用请调用类文件submit中的query_timestamp函数
        String anti_phishing_key = "";
        // 商户号
        String alipaypartner = AlipayConfig.partner;

        // 把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("partner", alipaypartner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", payment_type);
        sParaTemp.put("notify_url", notify_url);
        sParaTemp.put("return_url", return_url);
        sParaTemp.put("seller_email", seller_email);
        sParaTemp.put("out_trade_no", out_trade_no);
        sParaTemp.put("subject", subject);
        sParaTemp.put("total_fee", total_fee);
        sParaTemp.put("body", body);
        sParaTemp.put("show_url", show_url);
        sParaTemp.put("anti_phishing_key", anti_phishing_key);
        sParaTemp.put("exter_invoke_ip", "");
        Order order = orderMapper.selectByOrderCode(out_trade_no);
        Payment dbpayment = paymentMapper.selectByOrderIdAndPayState(order.getOrderId(),PayState.PROCESSING.ordinal());
        if (null == dbpayment) {
            paymentMapper.insert(payment);
        }
        AlipayCore.logResult("建立支付请求 订单号：" + out_trade_no);
        //建立请求
        return AlipaySubmit.buildRequest(sParaTemp, "post", "submit");
    }

    public boolean callBackAlipay(Map<String, String> params) {
        AlipayCore.logInfo("支付回调验证开始。。。");
        // 计算得出通知验证结果
        boolean verify_result = AlipayNotify.verify(params);
        AlipayCore.logInfo("支付回调验证结果：" + verify_result);
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

            String error_code = params.get("error_code");

            Order order = orderMapper.selectByOrderCode(out_trade_no);
            Payment payment = paymentMapper.selectByOrderIdAndPayState(order.getOrderId(), PayState.PROCESSING.ordinal());
            if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
                try {
                    /**已有支付记录**/
                    if (order.getOrderState() == OrderState.PAID_PAYMENT && payment.getChannelType() == ChannelType.ALIPAY.ordinal()) {
                        AlipayCore.logInfo("已有支付记录！支付宝前台同步回调已经执行，后台回调不再次做订单处理");
                    } else {
                        AlipayCore.logInfo("支付宝支付开始。。。");
                        Date curDate = new Date();
                        payment.setBuyerAccount(buyer_email);
                        payment.setPayFlowCode(trade_no);
                        payment.setPayNotifyState(PayNotifyState.NOTIFY_SUCCESS.ordinal());
                        payment.setNotifyTime(DateUtils.parseTimestamp(notify_time));
                        //支付信息
                        if (payment.getPayState() == PayState.PROCESSING.ordinal()) {/**0:处理中1:处理成功2:处理失败3:部分成功*/
                            if (payment.getErrorCode() != null) {
                                payment.setErrorCode(error_code);
                                payment.setPayState(!payment.getErrorCode().equals("00") ? PayState.PROCESSED_FAILURE.ordinal() : PayState.PROCESSED_SUCCESS.ordinal());//'0：处理中1：处理成功2：处理失败'
                            } else if (payment.getErrorCode() == null || payment.getErrorCode().equals("00")) {
                                payment.setPayState(PayState.PROCESSED_SUCCESS.ordinal());
                                AlipayCore.logInfo("支付宝支付处理成功！");
                            }
                            payment.setCompleteTime(curDate);
                        }

                        order.setOrderState(OrderState.PAID_PAYMENT);
                        order.setUpdateTime(curDate);
                        order.setPaymentType(PaymentType.ALIPAY.ordinal());
                        order.setPaymentSequence(trade_no);
                        order.setPaymentTime(curDate);
                        //找到当前用户，更新用户积分，根据当前积分，更新用户等级 并在积分记录表插入数据
                        //rankService.updateRankAndIntegral(payment.getCustomer().getCustomerId(), Double.valueOf(total_fee));
                        AlipayCore.logInfo("支付宝支付成功！更新用户等级并在积分记录表插入数据");
                    }

                    AlipayCore.logInfo("支付回调验证结束。。。");
                    return true;
                } catch (Exception e) {
                    AlipayCore.logInfo("支付回调验证异常。。。异常信息：" + e);
                    return false;
                }
            } else {
                payment.setPayNotifyState(PayNotifyState.NOTIFY_FAILURE.ordinal());
                payment.setNotifyTime(DateUtils.parseTimestamp(notify_time));
                payment.setPayState(PayState.PROCESSED_FAILURE.ordinal());
                payment.setErrorCode(error_code);
                AlipayCore.logInfo("支付回调验证状态失败。。。失败信息，error_code=" + error_code);
                return false;
            }
        } else {
            logger.info("alipay verify fail");
            AlipayCore.logInfo("支付回调验证失败。。。");
            return false;
        }
    }

    public String refund(String servicePath, String orderCode, ChannelType channelType) throws Exception {
        Order order = orderMapper.selectByOrderCode(orderCode);
        Payment payment = paymentMapper.selectByOrderId(order.getOrderId());
        if (null == payment) {
            throw new Exception("该订单无付款记录");
        }
        AlipayCore.logInfo("用户提交退款请求 订单号：" + orderCode);
        String htmlText = null;
        // 组织支付参数、加密，发送支付请求(根据支付通道选择相应的方法 方法计3个)。支付订单编号用payId
        if (channelType == ChannelType.ALIPAY) {// 支付宝
            htmlText = refundAlipay(payment, servicePath);
        }
        return htmlText;
    }

    public String refundAlipay(Payment payment, String servicePath) {
        //批次号，必填，格式：当天日期[8位]+序列号[3至24位]，如：201603081000001
        String batch_no = UtilDate.getOrderNum();

        List<ProductReturn> productReturns = productReturnMapper.selectByOrderId(payment.getOrderId());
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (ProductReturn productReturn : productReturns) {
            if (productReturn.getReturnRefundState() == ReturnRefundState.RETURNED_WAIT_REFUND.ordinal() || productReturn.getReturnRefundState() == ReturnRefundState.WAIT_REFUND.ordinal()) {
                sb.append(payment.getPayFlowCode() + "^");
                sb.append(productReturn.getRefundPrice() + "^");
                String reason = null;
                if (productReturn.getRefundOnly()) {
                    reason = productReturn.getRefundReason().toString();
                } else {
                    reason = productReturn.getReturnReason().toString();
                }
                sb.append(reason + "#");
                i++;
                Payment payment1 = new Payment();
                payment1.setChannelType(ChannelType.ALIPAY.ordinal());
                payment1.setOrder(payment.getOrder());
                payment1.setBatchNo(batch_no);
                payment1.setPayFlowCode(payment.getPayFlowCode());
                payment1.setReturnCode(productReturn.getReturnCode());
                payment1.setCustomerId(payment.getCustomerId());
                payment1.setCreateTime(new Date());
                payment1.setPayType(PayType.REIMBURSE.ordinal());
                payment1.setPayState(PayState.PROCESSING.ordinal());
                payment1.setPayNotifyState(PayNotifyState.NOT_NOTIFY.ordinal());
                payment1.setPayAmount(productReturn.getRefundPrice());
                payment1.setBatchNum(i);
                paymentMapper.insert(payment1);
            }
        }

        //退款笔数，必填，参数detail_data的值中，“#”字符出现的数量加1，最大支持1000笔（即“#”字符出现的数量999个）
        String batch_num = String.valueOf(i);

        //退款详细数据，必填，格式（支付宝交易号^退款金额^备注），多笔请用#隔开
        String dData = sb.toString();
        dData = dData.substring(0, dData.lastIndexOf("#"));
        String detail_data = dData;


        // 服务器异步通知页面路径 //需http://格式的完整路径，不能加?id=123这类自定义参数
        String notify_url = servicePath + GlobalConfig.getProperty(Config.ALIPAY_REFUND_NOTIFY_URL);
        System.out.println(notify_url);
        //////////////////////////////////////////////////////////////////////////////////

        //把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", AlipayConfig.service);
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("notify_url", notify_url);
        sParaTemp.put("seller_email", AlipayConfig.seller_email);
        sParaTemp.put("refund_date", AlipayConfig.refund_date);
        sParaTemp.put("batch_no", batch_no);
        sParaTemp.put("batch_num", batch_num);
        sParaTemp.put("detail_data", detail_data);

        //建立请求
        String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
        AlipayCore.logInfo("建立退款请求 退款批次号：" + batch_no);
        return sHtmlText;
    }

    public boolean refundAlipayCallBack(Map<String, String> params) throws Exception {

        AlipayCore.logInfo("支付宝退款回调开始。。。");
        // 计算得出通知验证结果
        boolean verify_result = AlipayNotify.verify(params);
        AlipayCore.logInfo("支付宝退款回调验证结果：" + verify_result);
        // 验证成功
        if (verify_result) {
            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
            //批次号
            String batch_no = params.get("batch_no");

            //批量退款数据中转账成功的笔数
            String success_num = params.get("success_num");

            //批量退款数据中的详细信息
            String result_details = params.get("result_details");
            AlipayCore.logInfo("支付宝退款回调验证成功！退款批次号：" + batch_no + " 退款详情数据：" + result_details);
            String notify_time = params.get("notify_time");
            List<Payment> payments = paymentMapper.selectByBatchNoAndPayState(batch_no, PayState.PROCESSING.ordinal());
            if (!success_num.equalsIgnoreCase("0")) {
                for (Payment payment : payments) {
                    String[] results = result_details.split("$");
                    if (LogicUtils.isNotNullAndEmpty(results)) {
                        for (String rl : results) {
                            String[] frl = rl.split("^");
                            if (frl[2].equalsIgnoreCase("SUCCESS")) {
                                if (payment.getPayFlowCode().equalsIgnoreCase(frl[0])) {
                                    setPaymentSate(payment, notify_time, success_num);

                                }
                            } else {
                                throw new Exception("支付宝退款回调状态错误,交易流水号:" + frl[0] + ",状态码：" + frl[2]);
                            }
                        }
                    } else {
                        String[] frl = result_details.split("^");
                        if (frl[2].equalsIgnoreCase("SUCCESS")) {
                            if (payment.getPayFlowCode().equalsIgnoreCase(frl[0])) {
                                setPaymentSate(payment, notify_time, success_num);
                            } else {
                                throw new Exception("支付宝退款回调状态错误,交易流水号:" + frl[0] + ",状态码：" + frl[2]);
                            }
                        }
                    }
                }

            }
            return true;
        } else {
            AlipayCore.logInfo("支付宝退款回调验证失败！");
            return false;
        }

    }

    private void setPaymentSate(Payment payment, String notify_time, String success_num) {
        payment.setPayState(PayState.PROCESSED_SUCCESS.ordinal());
        payment.setPayNotifyState(PayNotifyState.NOTIFY_SUCCESS.ordinal());
        payment.setNotifyTime(DateUtils.parseDate(notify_time));
        payment.setBatchNum(Integer.valueOf(success_num));
        paymentMapper.insert(payment);

        ProductReturn productReturn = productReturnMapper.selectByReturnCode(payment.getReturnCode());
        if (productReturn.getRefundOnly()) {
            productReturn.setReturnRefundState(ReturnRefundState.REFUNDED.ordinal());
        } else {
            productReturn.setReturnRefundState(ReturnRefundState.RETURNED_REFUND.ordinal());
        }
        AlipayCore.logInfo("支付宝退款成功！更新退款号：" + productReturn.getReturnCode() + " 状态为成功！通知时间：" + notify_time);
    }

    protected String unionPay(Payment payment, HttpServletRequest request) {
        if (StringUtils.isEmpty(payment.getUserIPAddress())) {
            throw new NullArgumentException("UserIPAddress is null");
        }
        String reqPath = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath();  //URL路径，用于拼接回调函数

        String str = getClass().getResource("/").getFile().toString();
        int num = str.indexOf("WEB-INF");
        String privateKeyPath = str.substring(0, num + "WEB-INF".length()) + "/MerPrK_808080201307375_20141118112327.key";   //私钥
        String publicKeyPath = str.substring(0, num + "WEB-INF".length()) + "/PgPubk.key";    //公钥

        File file = new File(privateKeyPath);
        if (file.exists()) {
            System.out.print("123141234134123");
        }

        String MerId = UnionPayConf.PAY_UNIONPAY_MERCODE;  //商户号，长度为15个字节的数字串，由ChinaPay分配
        String OrdId = payment.getOrder().getOrderCode().substring(1, 17);  //订单号，长度为16个字节的数字串，由商户系统生成，失败的订单号允许重复支付(我们的订单号为17位)
        String TransAmt = String.format("%012d", new BigDecimal(payment.getPayAmount()).multiply(new BigDecimal(100)).intValue());   //交易金额,长度为12个字节的数字串，例如：数字串"000000001234"表示12.34元。
        String CuryId = "156";  //货币代码, 长度为3个字节的数字串，目前只支持人民币，取值为"156"
        String TransDate = new SimpleDateFormat("yyyyMMdd").format(new Date());  //交易日期，长度为8个字节的数字串，表示格式为：YYYYMMDD
        String TransType = "0001";  //交易类型，长度为4个字节的数字串，取值范围为："0001"和"0002"， 其中"0001"表示消费交易，"0002"表示退货交易
        String Version = "20070129";  //支付接入版本号，必填
        String BgRetUrl = reqPath + "/serverUnion.htm";  //后台交易接收URL，长度不要超过80个字节，必填
        String PageRetUrl = reqPath + "/callbackUnionpay.htm";  //页面交易接收URL，长度不要超过80个字节，必填
        String GateId = "";  //支付网关号，可选
        String Priv1 = "dongzuo";  //商户私有域，长度不要超过60个字节
        String ChkValue;  //256字节长的ASCII码,为此次交易提交关键数据的数字签名，必填

//        说明：
//        PageRetUrl为页面接受应答地址，用于引导使用者返回支付后的商户网站页面。
//        BgRetUrl 为后台接受应答地址，用于商户记录交易信息和处理，对于使用者是不可见的。另外ChinaPay会根据后台发送的http的返回码来判定是否重新发送后台应答流水，以确保后台应答的接收。
//        Priv1表示 “商户私有域”，在支付版本20070129中会参与数字签名，商户通过此字段向Chinapay发送的信息，Chinapay依原样填充返回给商户。
//        GateId为可选项，表示 “支付网关号”，如填写GateId（支付网关号），则消费者将直接进入支付页面，否则进入网关选择页面。
//        OrdId，表示 “订单号”， 该域段的内容可以完全由用户自己定义。


        String signData = MerId + OrdId + TransAmt + CuryId + TransDate + TransType + Priv1;   //拼接待签名的字符串

        /**生成数字签名**/

        Map<String, String> map = new HashMap<>();
        map.put("MerId", MerId);
        map.put("OrdId", OrdId);
        map.put("TransAmt", TransAmt);
        map.put("CuryId", CuryId);
        map.put("TransDate", TransDate);
        map.put("TransType", TransType);
        map.put("Version", Version);
        map.put("BgRetUrl", BgRetUrl);
        map.put("PageRetUrl", PageRetUrl);
        map.put("Priv1", Priv1);
        // map.put("ChkValue",ChkValue);

        return new UnionPayUtils().generateAutoSubmitForm(UnionPayConf.gateWay, map);// 跳转到银联页面支付
    }

    @Override
    public Payment addPay(Payment payment) {
        payment.setCreateTime(new Date());
        paymentMapper.insert(payment);
        return payment;
    }





}
