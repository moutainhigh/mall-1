package com.yunxin.cb.mall.entity.meta;

/**
 * Created by lemosen on 17/9/13.
 */
public enum AttachmentState {
    RUNNING("启用中"),
    WAIT("等待中"),
    CANCEL("关闭中")
    ;
    private String name;

    AttachmentState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
