/**
 *
 */
package com.yunxin.cb.mall.vo;

import com.yunxin.cb.mall.entity.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gonglei
 */
public class CategoryNode {

    private int categoryId;

    /*
     * 分类名称
     */
    private String categoryName;

    /*
     * 是否启用
     */
    private boolean enabled;

    private int level;

    /**
     * 图标路径
     */
    private String iconPath;

    private List<CategoryNode> categoryNodes;

    public CategoryNode(Category category) {
        this.categoryId = category.getCategoryId();
        this.categoryName = category.getCategoryName();
        this.enabled = category.isEnabled();
        this.level = category.getLevel();
        this.iconPath = category.getIconPath();
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<CategoryNode> getCategoryNodes() {
        return categoryNodes;
    }

    public void setCategoryNodes(List<CategoryNode> categoryNodes) {
        this.categoryNodes = categoryNodes;
    }

    public void addChildCategory(CategoryNode category) {
        if (categoryNodes == null) {
            categoryNodes = new ArrayList<>();
        }
        categoryNodes.add(category);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
}
