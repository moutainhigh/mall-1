package com.yunxin.cb.mall.entity.meta;

/**
 * Created by Aidy_He on 16/9/22.
 */
public enum ArticleType {
    VEGETABLES("蔬菜"), FISH("鱼"), EGG("蛋"), DRINK("饮料"), OTHER("其他");

    private String name;

    private ArticleType(String name) {
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
