package com.yunxin.cb.mall.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TreeViewItem {
    private String id;
    private String text;
    private String treeLevel;
    private Boolean expanded;
    private String spriteCssClass;
    private boolean hasChildren;
    private boolean checked = true;
    private List<TreeViewItem> items;

    /** 分类比例配置 add by lxc   2018-08-09 */
    private BigDecimal ratio;

    public TreeViewItem() {
    }

    public TreeViewItem(String id, String text,String treeLevel, Boolean expanded, String spriteCssClass, boolean hasChildren, boolean checked) {
        this.id = id;
        this.text = text;
        this.treeLevel = treeLevel;
        this.expanded = expanded;
        this.spriteCssClass = spriteCssClass;
        this.hasChildren = hasChildren;
        this.checked = checked;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String value) {
        this.text = value;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String value) {
        this.id = value;
    }

    public Boolean getExpanded() {
        return this.expanded;
    }

    public void setExpanded(Boolean value) {
        this.expanded = value;
    }

    public String getSpriteCssClass() {
        return this.spriteCssClass;
    }

    public void setSpriteCssClass(String value) {
        this.spriteCssClass = value;
    }

    public List<TreeViewItem> getItems() {
        if (this.items == null) {
            this.items = new ArrayList();
        }

        return this.items;
    }

    public void setFields(String id, String text, String treeLevel,String spriteCssClass, Boolean expanded) {
        this.setId(id);
        this.setText(text);
        this.setTreeLevel(treeLevel);
        this.setSpriteCssClass(spriteCssClass);
        this.setExpanded(expanded);
    }

    public void setItems(List<TreeViewItem> items) {
        this.items = items;
    }

    public boolean isHasChildren() {
        return this.hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean selected) {
        this.checked = selected;
    }

    public BigDecimal getRatio() {
        return ratio;
    }

    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public String getTreeLevel() {
        return treeLevel;
    }

    public void setTreeLevel(String treeLevel) {
        this.treeLevel = treeLevel;
    }
}
