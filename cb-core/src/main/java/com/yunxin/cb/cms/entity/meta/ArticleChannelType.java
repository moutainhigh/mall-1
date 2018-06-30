package com.yunxin.cb.cms.entity.meta;

/**
 * Created by chenxing on 2016/2/23.
 */
public enum ArticleChannelType {
    WEB_SITE("网站"), PAD("平板"), MOBILE("手机");

    private String name;

    ArticleChannelType(String name) {
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
