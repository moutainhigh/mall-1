/**
 *
 */
package com.yunxin.cb.mall.entity.meta;

/**
 * @author apple
 */
public enum ChannelType {
    UNIONPAY("银联"), ALIPAY("支付宝"), TENPAY("财付通"), WXPAY("微信");
    private String name;

    private ChannelType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
