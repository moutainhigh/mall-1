/**
 *
 */
package com.yunxin.cb.mall.entity.meta;

/**
 * @author Aidy
 */
public enum PayNotifyState {

    NOT_NOTIFY("未通知"), NOTIFY_SUCCESS("通知成功"), NOTIFY_FAILURE("通知失败");

    private String name;

    private PayNotifyState(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
