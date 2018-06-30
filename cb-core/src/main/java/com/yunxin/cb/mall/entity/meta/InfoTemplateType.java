package com.yunxin.cb.mall.entity.meta;

public enum InfoTemplateType {
    MAIL("邮件"), INSTATIONMEG("站内信"), MESSAGE("短信"), GOODSRETURNREASON("退货原因"), GOODSCHANGEREASON("换货原因");
    private String name;

    private InfoTemplateType(String name) {
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
