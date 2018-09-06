/**
 *
 */
package com.yunxin.cb.mall.web.vo;

/**
 * @author gonglei
 */
public class TemplateNode implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5712924289950374360L;


    private String path;

    /**
     * 组织名称
     */
    private String name;


    private boolean hasChildren;

    private String nodeType="folder";


    public TemplateNode(String path, String name,String nodeType, boolean hasChildren) {
        this.path = path;
        this.name = name;
        this.hasChildren = hasChildren;
        this.nodeType=nodeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }


    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }
}
