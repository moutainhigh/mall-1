package com.yunxin.cb.pay.unionPay;

/**
 * 名称：支付配置类
 * 功能：配置类
 * 类属性：公共类
 * 版本：1.0
 * 日期：2011-03-11
 * 作者：中国银联UPOP团队
 * 版权：中国银联
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。该代码仅供参考。
 */
public class UnionPayConf {
    // 版本号
    public final static String version = "1.0.0";

    // 编码方式
    public final static String charset = "UTF-8";

    // 基础网址（请按相应环境切换）

    /* 测试环境 */
//    private final static String UPOP_BASE_URL = "http://payment-test.chinapay.com/";

    /* 生产环境 */
    private final static String UPOP_BASE_URL = "https://payment.chinapay.com/";

    // 支付网址（测试环境和生产环境都为此格式）
    public final static String gateWay = UPOP_BASE_URL + "pay/TransGet";

    // 退款网址（测试环境）
//    public final static String backStagegateWay = UPOP_BASE_URL + "refund/SingleRefund.jsp";
    // 退款网址（生产环境）
    public final static String backStagegateWay = "https://bak.chinapay.com/refund/SingleRefund.jsp";


    // 查询网址（测试环境）
//    public final static String queryUrl = UPOP_BASE_URL + "QueryWeb/processQuery.jsp";
    // 查询网址（生产环境）
    public final static String queryUrl = "http://console.chinapay.com/QueryWeb/processQuery.jsp";

    // 商户代码
    public final static String PAY_UNIONPAY_MERCODE = "808080201307375";//ConfigUtil.getConfigValueByKey("unionpay.mercode");

    // 商城密匙，需要和银联商户网站上配置的一样
    public final static String PAY_UNIONPAY_SECURITYKEY = "ILLCG9HK";//ConfigUtil.getConfigValueByKey("unionpay.securityKey");


    // 商户名称
    public final static String PAY_UNIONPAY_MERNAME = "浙江东阳东作云文化产业投资公司B2C";//ConfigUtil.getConfigValueByKey("unionpay.mername");


    // 前台回调商户URL
    public final static String PAY_UNIONPAY_MERFRONTENDURL = "/mall/callbackUnionpay.htm";//ConfigUtil.getConfigValueByKey("unionpay.merfrontendurl");

    // 后台回调商户URL
    public final static String PAY_UNIONPAY_MERBACKENDURL = "/mall/serverUnion.htm";//ConfigUtil.getConfigValueByKey("unionpay.merbackendurl");

    // 加密方式
    public final static String signType = "MD5";
    public final static String signType_SHA1withRSA = "SHA1withRSA";

    // 签名
    public final static String signature = "signature";
    public final static String signMethod = "signMethod";


    //组装消费请求包
    public final static String[] reqVo = new String[]{
            "version",
            "charset",
            "transType",//交易类型
            "origQid",//原始交易流水号
            "merId",//商户代码
            "merAbbr",//商户名称
            "acqCode",//收单机构代码
            "merCode",//商户类型
            "commodityUrl",//商品URL
            "commodityName",//商品名称
            "commodityUnitPrice",//商品单价
            "commodityQuantity",//商品数量
            "commodityDiscount",//优惠信息
            "transferFee",//运输费用
            "orderCode",//商户订单号
            "orderAmount",//orderAmount
            "orderCurrency",//orderCurrency
            "orderTime",//交易开始日期时间
            "customerIp",//持卡人IP
            "customerName",//持卡人姓名
            "defaultPayType",//默认支付方式
            "defaultBankNumber",//默认银行编码
            "transTimeout",//交易超时时间
            "frontEndUrl",//返回URL
            "backEndUrl",//通知URL
            "merReserved"//商户保留域
    };

    public final static String[] notifyVo = new String[]{
            "charset",
            "cupReserved",
            "exchangeDate",//兑换日期
            "exchangeRate",//清算汇率
            "merAbbr",//商户名称
            "merId",//商户代码
            "orderAmount",//交易金额
            "orderCurrency",//交易币种
            "orderNumber",//商户订单号  --8
            "qid",//交易流水号 -9
            "respCode",//响应码   -10
            "respMsg",//响应信息 -11
            "respTime",//交易完成时间   -12
            "settleAmount",//清算金额
            "settleCurrency",//清算币种
            "settleDate",//清算日期
            "traceNumber",//系统跟踪号
            "traceTime",//系统跟踪时间
            "transType",//交易类型
            "version"
    };

    public final static String[] queryVo = new String[]{
            "version",
            "charset",
            "transType",
            "merId",
            "orderNumber",
            "orderTime",
            "merReserved"
    };

}
