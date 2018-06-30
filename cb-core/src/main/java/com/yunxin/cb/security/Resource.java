/**
 *
 */
package com.yunxin.cb.security;

import java.util.List;

/**
 * @author gonglei
 */
public class Resource {

    private String code;

    private String name;

    private ResourceType type;

    private String value;

    private String cssClass;

    private String childrenCssClass;

    private List<Resource> children;


    public Resource() {

    }

    public Resource(String code, String name, ResourceType type, String value, String cssClass, String childrenCssClass) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.value = value;
        this.cssClass = cssClass;
        this.childrenCssClass = childrenCssClass;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String rescId) {
        this.code = rescId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String url) {
        this.value = url;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public List<Resource> getChildren() {
        return children;
    }

    public void setChildren(List<Resource> children) {
        this.children = children;
    }

    public String getChildrenCssClass() {
        return childrenCssClass;
    }

    public void setChildrenCssClass(String childrenCssClass) {
        this.childrenCssClass = childrenCssClass;
    }

    public boolean isHasChildrenMenu() {
        if (children != null && children.size() > 0) {
            return children.stream().anyMatch(p -> p.getType() == ResourceType.MENU);
        } else {
            return false;
        }
    }

    @Override
    public Resource clone() {
        Resource resc = new Resource(code, name, type, value, cssClass, childrenCssClass);
        return resc;
    }
}
