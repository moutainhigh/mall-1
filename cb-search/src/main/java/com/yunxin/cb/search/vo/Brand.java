package com.yunxin.cb.search.vo;

/**
 * 品牌
 */
public class Brand implements java.io.Serializable {

    private int brandId;
    /**
     * 品牌编号
     */
    private String brandNo;
    /**
     * 品牌中文名称
     */
    private String brandName;
    /**
     * 品牌英文名称
     */
    private String brandEnName;
    /**
     * 品牌标题
     */
    private String brandTitle;
    /**
     * 图片路径  150*58 png
     */
    private String picPath;


    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandNo() {
        return brandNo;
    }

    public void setBrandNo(String brandNo) {
        this.brandNo = brandNo;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandEnName() {
        return brandEnName;
    }

    public void setBrandEnName(String brandEnName) {
        this.brandEnName = brandEnName;
    }

    public String getBrandTitle() {
        return brandTitle;
    }

    public void setBrandTitle(String brandTitle) {
        this.brandTitle = brandTitle;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
}
