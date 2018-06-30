package com.yunxin.cb.mall.entity.meta;

public enum LetterType {

    ALL_CUSTOMER("所有用户"), SPECFIC_CUSTOMER("指定用户");

    private String typeName;

    private LetterType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }


    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }
}
