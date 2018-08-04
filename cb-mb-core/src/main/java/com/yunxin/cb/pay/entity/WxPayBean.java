package com.yunxin.cb.pay.entity;

import com.jpay.weixin.api.WxPayApiConfig;
import com.jpay.weixin.api.WxPayApiConfig.PayModel;
import com.jpay.weixin.api.WxPayApiConfigKit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WxPayBean {
	@Value("${wxpay.appId}")
    private String appId;
	@Value("${wxpay.appSecret}")
    private String appSecret;
	@Value("${wxpay.mchId}")
    private String mchId;
	@Value("${wxpay.partnerKey}")
    private String partnerKey;
	@Value("${wxpay.certPath}")
    private String certPath;
	@Value("${wxpay.domain}")
    private String domain;

	public void init(){
		WxPayApiConfigKit.setThreadLocalWxPayApiConfig(getApiConfig());
	}

	public WxPayApiConfig getApiConfig() {
		//notify_url = wxPayBean.getDomain().concat("/wxpay/pay_notify");
		return WxPayApiConfig.New()
				.setAppId(appId)
				.setMchId(mchId)
				.setPaternerKey(partnerKey)
				.setPayModel(PayModel.BUSINESSMODEL);
	}

	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getPartnerKey() {
		return partnerKey;
	}
	public void setPartnerKey(String partnerKey) {
		this.partnerKey = partnerKey;
	}
	public String getCertPath() {
		return certPath;
	}
	public void setCertPath(String certPath) {
		this.certPath = certPath;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Override
	public String toString() {
		return "WxPayBean [appId=" + appId + ", appSecret=" + appSecret + ", mchId=" + mchId + ", partnerKey="
				+ partnerKey + ", certPath=" + certPath + ", domain=" + domain + "]";
	}
}
