package com.yunxin.cb.mall.entity.meta;

public enum InfoTemplateStatus {
    SAVE("保存"), APPLY("应用"), BACKUP("备份");
    private String name;

    private InfoTemplateStatus(String name) {
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
        return "InformatitonTemplateStatus [name=" + name + "]";
    }

}
