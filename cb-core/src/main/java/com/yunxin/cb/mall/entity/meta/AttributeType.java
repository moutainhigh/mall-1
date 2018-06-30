/**
 *
 */
package com.yunxin.cb.mall.entity.meta;

/**
 * @author z001075
 */
public enum AttributeType {
    SINGLE("单选"), SR("输入");

    private String name;

    private AttributeType(String name) {
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
