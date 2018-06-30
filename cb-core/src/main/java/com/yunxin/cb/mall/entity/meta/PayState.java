/**
 *
 */
package com.yunxin.cb.mall.entity.meta;

/**
 * @author Aidy
 */
public enum PayState {

    PROCESSING("处理中"), PROCESSED_SUCCESS("处理成功"), PROCESSED_FAILURE("处理失败"), PARTIALLY_SUCCESS("部分成功");

    private String name;

    private PayState(String name) {
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
