/**
 *
 */
package com.yunxin.cb.mall.entity.meta;

/**
 * 终端
 * @author gws
 */
public enum TerminalType {
    PC("PC"), WAP("H5"), APP("APP");
    private String name;

    private TerminalType(String name) {
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
