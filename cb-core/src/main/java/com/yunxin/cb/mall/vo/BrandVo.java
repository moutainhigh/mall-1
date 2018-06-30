package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.Catalog;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by w001499 on 2014/7/31.
 */
public class BrandVo {


    /**
     * ID
     */
    private int brandId;


    /**
     * 名称
     */
    private String brandName;


    /**
     * 英文名称
     */

    private String engName;


    /**
     * 图片路径
     */
    private String picPath;


    /**
     * 是否热门品牌
     */
    private boolean hot;


    /**
     * 创建时间
     */

    private Date createTime;


    /**
     * 是否删除
     */
    private boolean del;


    /**
     * 备注
     */
    private String remark;

    private Set<Catalog> categories = new HashSet<Catalog>();

    public BrandVo(int brandId, String brandName, String engName, String picPath) {
        this.brandId = brandId;
        this.brandName = brandName;
        this.engName = engName;
        this.picPath = picPath;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<Catalog> getCategories() {
        return categories;
    }

    public void setCategories(Set<Catalog> categories) {
        this.categories = categories;
    }
}
