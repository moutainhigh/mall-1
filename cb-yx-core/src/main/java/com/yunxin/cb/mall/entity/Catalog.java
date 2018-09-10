package com.yunxin.cb.mall.entity;

import java.io.Serializable;

public class Catalog implements Serializable {
    private static final long serialVersionUID = -7849330042947420690L;
    private int catalogId;
    private int parentCatalogId;

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getParentCatalogId() {
        return parentCatalogId;
    }

    public void setParentCatalogId(int parentCatalogId) {
        this.parentCatalogId = parentCatalogId;
    }
}
