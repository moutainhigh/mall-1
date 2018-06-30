/**
 *
 */
package com.yunxin.cb.mall.web.action.media;

/**
 * @author gonglei
 */
public class FolderNode implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5712924289950374360L;


    private String path;

    /**
     * 组织名称
     */
    private String folderName;


    private boolean hasChildren;

    private String nodeType="folder";


    public FolderNode(String path, String name, boolean hasChildren) {
        this.path = path;
        this.folderName = name;
        this.hasChildren = hasChildren;
    }


    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
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
