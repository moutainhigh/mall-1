package com.yunxin.cb.mall.entity.meta;

/**
 * Created by chenxing on 2016/2/24.
 */
public enum CuisineType {
    SICHUAN_CUISINE("川菜"), HUNAN_CUISINE("湘菜"), CANTONESE_CUISINE("粤菜"), SHANDONG_CUISINE("鲁菜"), OTHERS("其它菜系");

    private String name;

    CuisineType(String name) {
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
