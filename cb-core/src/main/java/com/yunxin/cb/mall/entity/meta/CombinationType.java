package com.yunxin.cb.mall.entity.meta;

/**
 * Created by x001393 on 2014/11/7.
 * 商品组合类型
 */

public enum CombinationType {

    THZH("特惠组合"), TJDP("推荐搭配");

    private String name;

    private CombinationType(String name) {
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
