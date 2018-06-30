package com.yunxin.cb.mall.vo;

import java.util.List;

public class MenuViewItem implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6219618681507888626L;
    private String id;
    private String text;
    private boolean expanded;
    private boolean encoded;
    private String cssClass;
    private String content;
    private boolean checked;
    private String url;
    private String contentUrl;
    private String spriteCssClass;
    private List<MenuViewItem> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isEncoded() {
        return encoded;
    }

    public void setEncoded(boolean encoded) {
        this.encoded = encoded;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getSpriteCssClass() {
        return spriteCssClass;
    }

    public void setSpriteCssClass(String spriteCssClass) {
        this.spriteCssClass = spriteCssClass;
    }

    public List<MenuViewItem> getItems() {
        return items;
    }

    public void setItems(List<MenuViewItem> items) {
        this.items = items;
    }

}
