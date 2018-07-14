package com.yunxin.cb.security;

import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.List;

public class Privilege implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String name;
    private PrivilegeType type;
    private String value;
    private String cssClass;
    private String childrenCssClass;
    private List<Privilege> children;

    public Privilege() {
    }

    public Privilege(String code, String name, PrivilegeType type, String value, String cssClass, String childrenCssClass) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.value = value;
        this.cssClass = cssClass;
        this.childrenCssClass = childrenCssClass;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String rescId) {
        this.code = rescId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String url) {
        this.value = url;
    }

    public PrivilegeType getType() {
        return this.type;
    }

    public void setType(PrivilegeType type) {
        this.type = type;
    }

    public String getCssClass() {
        return this.cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public List<Privilege> getChildren() {
        return this.children;
    }

    public void setChildren(List<Privilege> children) {
        this.children = children;
    }

    public String getChildrenCssClass() {
        return this.childrenCssClass;
    }

    public void setChildrenCssClass(String childrenCssClass) {
        this.childrenCssClass = childrenCssClass;
    }

    public boolean isHasChildrenMenu() {
        return CollectionUtils.isNotEmpty(this.children) && this.children.stream().anyMatch((p) -> {
            return p.getType() == PrivilegeType.MENU;
        });
    }

    public Privilege clone() {
        return new Privilege(this.code, this.name, this.type, this.value, this.cssClass, this.childrenCssClass);
    }
}
