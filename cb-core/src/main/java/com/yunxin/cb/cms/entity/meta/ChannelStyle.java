package com.yunxin.cb.cms.entity.meta;

/**
 * Created by gonglei on 16/2/4.
 */
public enum ChannelStyle {

    DETAIL("详细显示"), CATEGORY("分类显示"), BANNER("横幅显示"), LIST("列表显示"), SIMPLE("简单显示");

    ChannelStyle(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
