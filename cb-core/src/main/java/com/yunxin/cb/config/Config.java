package com.yunxin.cb.config;

/**
 * Created by gonglei on 16/1/31.
 */
public enum Config {

    MEDIA_PATH("/data"), SEND_MAIL(""), ALIPAY_NOTIFY_URL("/pay/callBackAlipayAsync.htm"), ALIPAY_RETURN_URL("/pay/callBackAlipaySynch.htm"), ALIPAY_REFUND_NOTIFY_URL("/refund/callBackRefundAlipayAsync.do");

    private String defaultValue;

    Config(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
