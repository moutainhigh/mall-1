package com.yunxin.cb.mall.entity.meta;

import java.util.HashMap;
import java.util.Map;

public enum CarOrderBuyType {
    //购买类型(1全款,2置换)
    FULL_MONEY("全款",1),EXCHANGE("置换",2);


    private String name;
    private int value;

    CarOrderBuyType(String name,int value) {
        this.name = name;this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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

    /**
     * 把枚举放到map中,通过value找name
     * @return
     * @author lxc 2018-9-6 下午7:55:02
     */
    public static Map<Integer, String> getCarOrderBuyType() {
        Map<Integer, String> reMap = new HashMap<>();
        CarOrderBuyType[] values = CarOrderBuyType.values();
        for (CarOrderBuyType lt : values) {
            reMap.put(lt.getValue(), lt.getName());
        }
        return reMap;
    }
}
