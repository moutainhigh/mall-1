package com.yunxin.cb.system.meta;

/**
 * @author yangzhen
 */
public enum PushStatus {

    HAVE_NOT_PUSHED(0),//未推送
    HAVE_PUSHED(1)//已推送
    ;

    private int value;

    PushStatus(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}