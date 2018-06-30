package com.yunxin.cb.mall.entity.meta;

/**
 * Created by Administrator on 2016/1/7.
 */
public enum CouponDistributionMethod {
    ALL("全站"), COMMODITY("商品"), CATEGORY("分类"), BRAND("品牌"), CUSTOMER("客户"), RANK("等级");
    private String name;

    private CouponDistributionMethod(String name) {
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
