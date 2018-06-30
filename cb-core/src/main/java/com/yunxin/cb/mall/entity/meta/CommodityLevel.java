/**
 *
 */
package com.yunxin.cb.mall.entity.meta;

/**
 * @author z001075
 */
public enum CommodityLevel {
    ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"),
    SEVEN("7"), EIGHT("8"), NINE("9");

    private String name;

    private CommodityLevel(String name) {
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
