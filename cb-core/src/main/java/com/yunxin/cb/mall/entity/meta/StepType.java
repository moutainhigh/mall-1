package com.yunxin.cb.mall.entity.meta;

/**
 * Created by Aidy_He on 16/9/23.
 */
public enum StepType {

    STAND_BY("准备工作"), COOKING("烹饪中"), COOKING_FINISH("烹饪完成");

    private String name;

    private StepType(String name) {
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
