package com.yunxin.cb.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gonglei on 16/2/24.
 */
public class Province {

    private String code;

    private String name;

    private List<City> cities;

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

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    public void addCity(City city) {
        if (cities == null) {
            cities = new ArrayList<>();
        }
        cities.add(city);
    }

}
