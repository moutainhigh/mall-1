package com.yunxin.cb.config;

import com.yunxin.cb.bean.City;
import com.yunxin.cb.bean.District;
import com.yunxin.cb.bean.Province;
import com.yunxin.core.util.FileUtils;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gonglei on 16/2/24.
 */
@Component
public class ProvinceConfig {

    private List<Province> provinces;
    private List<City> cities;
    private List<District> districts;

    private Map<String, Province> provinceMap;

    public void init(ServletContext servletContext) throws IOException {
        String provinceStr = FileUtils.bufferedReaderByFilePath(servletContext.getRealPath("/js/district/json-array-of-province.js"));
        String cityStr = FileUtils.bufferedReaderByFilePath(servletContext.getRealPath("/js/district/json-array-of-city.js"));
        String districtStr = FileUtils.bufferedReaderByFilePath(servletContext.getRealPath("/js/district/json-array-of-district.js"));
        provinces = (List<Province>) JSONArray.toCollection(JSONArray.fromObject(provinceStr), Province.class);
        cities = (List<City>) JSONArray.toCollection(JSONArray.fromObject(cityStr), City.class);
        districts = (List<District>) JSONArray.toCollection(JSONArray.fromObject(districtStr), District.class);

        provinceMap = new HashMap<>();
        provinces.stream().forEach(p -> provinceMap.put(p.getCode(), p));
        cities.stream().forEach(p -> {
            String provinceCode = p.getCode().substring(0, 2) + "0000";
            Province province = provinceMap.get(provinceCode);
            if (province != null) {
                province.addCity(p);
                p.setProvince(province);
            }
            districts.stream().forEach(d -> {
                String cityCode = d.getCode().substring(0, 4) + "00";
                if (p.getCode().equals(cityCode)) {
                    p.addDistrict(d);
                    d.setCity(p);
                }
            });
        });
    }

    /**
     * 获得所有省份对象
     *
     * @return
     */
    public List<Province> getProvinces() {
        return provinces;
    }

    /**
     * 获得所有城市对象
     *
     * @return
     */
    public List<City> getCities() {
        return cities;
    }

    public List<District> getDistricts() {
        return districts;
    }

    /**
     * 通过省份查询 所有的城市
     *
     * @param provinceCode
     * @return
     */
    public List<City> getCitiesByProvinceCode(String provinceCode) {
        Province province = provinceMap.get(provinceCode);
        return province != null ? province.getCities() : null;
    }


    public List<District> getDistrictsByCityCode(String cityCode) {
        City city = getCityByCode(cityCode);
        return city != null ? city.getDistricts() : null;
    }

    public City getCityByCode(String cityCode) {
        for (City city : cities) {
            if (city.getCode().equals(cityCode)) {
                return city;
            }
        }
        return null;
    }
}
