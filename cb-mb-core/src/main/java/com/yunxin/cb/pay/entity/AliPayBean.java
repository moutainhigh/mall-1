package com.yunxin.cb.pay.entity;

import com.jpay.alipay.AliPayApiConfig;
import com.jpay.alipay.AliPayApiConfigKit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AliPayBean {
    @Value("${alipay.appId}")
    private String appId;
    @Value("${alipay.privateKey}")
    private String privateKey;
    @Value("${alipay.publicKey}")
    private String publicKey;
    @Value("${alipay.serverUrl}")
    private String serverUrl;
    @Value("${alipay.domain}")
    private String domain;


    public void init(){
        AliPayApiConfigKit.setThreadLocalAliPayApiConfig(getApiConfig());
    }

    public AliPayApiConfig getApiConfig() {
        return AliPayApiConfig.New()
                .setAppId(appId)
                .setAlipayPublicKey(privateKey)
                .setCharset("UTF-8")
                .setPrivateKey(publicKey)
                .setServiceUrl(serverUrl)
                .setSignType("RSA2")
                .build();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Override
	public String toString() {
		return "AliPayBean [appId=" + appId + ", privateKey=" + privateKey + ", publicKey=" + publicKey + ", serverUrl="
				+ serverUrl + ", domain=" + domain + "]";
	}

	
}
