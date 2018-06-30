package com.yunxin.cb.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gonglei on 16/2/24.
 */
public class City {

    private Province province;

    private String code;

    private String name;

    private List<District> districts;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public void addDistrict(District district) {
        if (districts == null) {
            districts = new ArrayList<>();
        }
        districts.add(district);
    }
}
