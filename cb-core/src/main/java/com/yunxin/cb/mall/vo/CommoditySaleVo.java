package com.yunxin.cb.mall.vo;

public class CommoditySaleVo {
    private int commodityId;
    private String commodityName;
    private String commodityCode;
    private float price;
    private String defaultPicPath;
    private String shortName;
    private long productNum;


    public CommoditySaleVo(int commodityId, String commodityName,
                           String commodityCode, float price, String defaultPicPath,
                           String shortName, long productNum) {
        super();
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.commodityCode = commodityCode;
        this.price = price;
        this.defaultPicPath = defaultPicPath;
        this.shortName = shortName;
        this.productNum = productNum;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDefaultPicPath() {
        return defaultPicPath;
    }

    public void setDefaultPicPath(String defaultPicPath) {
        this.defaultPicPath = defaultPicPath;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public long getProductNum() {
        return productNum;
    }

    public void setProductNum(long productNum) {
        this.productNum = productNum;
    }


}
