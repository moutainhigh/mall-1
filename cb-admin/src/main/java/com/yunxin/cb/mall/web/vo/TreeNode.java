package com.yunxin.cb.mall.web.vo;


import com.yunxin.cb.security.Privilege;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gonglei on 2017/2/18.
 */
public class TreeNode {

    private String id;

    private String text;

    private String type;

    private NodeState state = new NodeState();

    private List<TreeNode> children;

    public TreeNode() {
    }

    public TreeNode(Privilege resource) {
        this.id = resource.getCode();
        this.text = resource.getName();
    }

    public TreeNode(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public TreeNode(String id, String text, String type) {
        this.id = id;
        this.text = text;
        this.type = type;
    }


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public NodeState getState() {
        return state;
    }

    public void setState(NodeState state) {
        this.state = state;
    }

    public void addChildNode(TreeNode treeNode) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(treeNode);
    }
}
