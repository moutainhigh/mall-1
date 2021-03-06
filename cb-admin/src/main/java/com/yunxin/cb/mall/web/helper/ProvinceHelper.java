/**
 * 
 */
package com.yunxin.cb.mall.web.helper;

import com.yunxin.core.util.FileUtils;
import com.yunxin.core.util.JacksonUtil;
import com.yunxin.core.util.LogicUtils;
import org.slf4j.Logger; import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Aidy_He
 *
 */
public class ProvinceHelper {

	private static final Logger logger = LoggerFactory.getLogger(ProvinceHelper.class);
	
	private static Map<String, String> provinceMaps=new HashMap<String, String>();
	
	@SuppressWarnings("rawtypes")
	public static void init(ServletContext servletContext){
		try {
			String provinceStr = FileUtils.bufferedReaderByFilePath(servletContext.getRealPath("/js/district/json-array-of-province.js"));
			String cityStr = FileUtils.bufferedReaderByFilePath(servletContext.getRealPath("/js/district/json-array-of-city.js"));
			String districtStr = FileUtils.bufferedReaderByFilePath(servletContext.getRealPath("/js/district/json-array-of-district.js"));
			List provinces =JacksonUtil.nonEmptyMapper().fromJson(provinceStr, ArrayList.class);
			List citys =JacksonUtil.nonEmptyMapper().fromJson(cityStr, ArrayList.class);
			List districts =JacksonUtil.nonEmptyMapper().fromJson(districtStr, ArrayList.class);
			
			if(LogicUtils.isNotNullAndEmpty(provinces)){
				for (Object obj : provinces) {
					String province= String.valueOf(obj);
					putMaps(province);
				}
			}
			if(LogicUtils.isNotNullAndEmpty(citys)){
				for (Object obj : citys) {
					String city= String.valueOf(obj);
					putMaps(city);
				}
			}
			if(LogicUtils.isNotNullAndEmpty(districts)){
				for (Object obj : districts) {
					String district= String.valueOf(obj);
					putMaps(district);
				}
			}
		} catch (Exception e) {
			logger.error("初始化省市区时发生异常" + e.getMessage(), e);
			logger.error("load Provinces data Exception",e);
		}
		
	}
	
	private static void putMaps(String region){
		if(LogicUtils.isNullOrEmpty(region)){
			throw new NullPointerException("the region String is empty");
		}
		String[] codeAndName = region.split(",");
		String name =codeAndName[0];
		String code =codeAndName[1];
		name = name.substring(name.indexOf("=")+1, name.length());
		code =code.substring(code.indexOf("=")+1, code.indexOf("}"));
		provinceMaps.put(code, name);
	}
	
	public static String getRegionNameByCode(String code){
		return provinceMaps.get(code);
	}
}
