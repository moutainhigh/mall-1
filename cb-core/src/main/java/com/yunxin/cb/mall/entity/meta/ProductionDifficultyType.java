package com.yunxin.cb.mall.entity.meta;

/**
 * Created by chenxing on 2016/2/24.
 */
public enum ProductionDifficultyType {
    EASY("简单"), GENERAL("一般"), MEDIUM("适中"), DIFFICULT("困难");

    private String name;

    ProductionDifficultyType(String name) {
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
