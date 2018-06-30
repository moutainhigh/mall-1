/**
 *
 */
package com.yunxin.cb.mall.entity.meta;

/**
 * @author Aidy_He
 */
public enum RescType {

    MENU("菜单"), BUTTON("按钮"), FUNCTION("功能"), DATA("数据"), OTHER("其他");

    private String name;

    private RescType(String name) {
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
