package com.yunxin.cb.search.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 运营分类
 */

public class Category implements java.io.Serializable {
    /**
     * ID
     */

    private int categoryId;
    /**
     * 分类编号
     */
    private String categoryNo;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 排序号
     */
    private int sortOrder;
    /**
     * 图标路径
     */
    private String iconPath;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(String categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
}
