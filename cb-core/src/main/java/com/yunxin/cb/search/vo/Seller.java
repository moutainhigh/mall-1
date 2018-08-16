package com.yunxin.cb.search.vo;

import com.yunxin.cb.search.vo.meta.SellerType;

/**
 * 供应商
 */
public class Seller implements java.io.Serializable {
    /****/
    private int sellerId;
    /**
     * 商家编码
     **/
    private String sellerCode;
    /**
     * 商家名称
     **/
    private String sellerName;

    /**
     * 商家类型
     **/
    private SellerType sellerType;

    /**
     * 商家地理位置经度 X轴坐标
     **/
    private String positionX;

    /**
     * 商家地理位置玮度 Y轴坐标
     **/
    private String positionY;

    public Seller() {
    }

    public Seller(com.yunxin.cb.mall.entity.Seller seller) {
        this.sellerId = seller.getSellerId();
        this.sellerCode = seller.getSellerCode();
        this.sellerName = seller.getSellerName();
        this.sellerType = SellerType.values()[seller.getSellerType().ordinal()];
        this.positionX = seller.getPositionX();
        this.positionY = seller.getPositionY();
    }


    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public SellerType getSellerType() {
        return sellerType;
    }

    public void setSellerType(SellerType sellerType) {
        this.sellerType = sellerType;
    }

    public String getPositionX() {
        return positionX;
    }

    public void setPositionX(String positionX) {
        this.positionX = positionX;
    }

    public String getPositionY() {
        return positionY;
    }

    public void setPositionY(String positionY) {
        this.positionY = positionY;
    }
}
