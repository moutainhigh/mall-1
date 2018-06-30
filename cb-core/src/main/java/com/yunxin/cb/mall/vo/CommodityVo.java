package com.yunxin.cb.mall.vo;


public class CommodityVo {
    private int commodityId;
    private String commodityName;
    private String commodityCode;
    private float price;
    private String defaultPicPath;
    private int activityId;
    private String shortName;
    private float value;
    private int goodsNumber;
    private boolean pass; //对于抢购活动，标志活动参与数量是否已全部抢购完
//	private Date startTime;
//	private Date deadLine;


    public CommodityVo() {

    }

    public CommodityVo(int commodityId, String commodityName,
                       String commodityCode, float price, String defaultPicPath,
                       String shortName) {
        super();
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.commodityCode = commodityCode;
        this.price = price;
        this.defaultPicPath = defaultPicPath;
        this.shortName = shortName;
    }


    public CommodityVo(int commodityId, String commodityName,
                       String commodityCode, float price, String defaultPicPath,
                       String shortName, float value, int activityId) {
        super();
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.commodityCode = commodityCode;
        this.price = price;
        this.defaultPicPath = defaultPicPath;
        this.activityId = activityId;
        this.shortName = shortName;
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
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

    public int getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(int goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }
}
