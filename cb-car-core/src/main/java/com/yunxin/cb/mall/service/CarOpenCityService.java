package com.yunxin.cb.mall.service;

import java.util.List;

import com.yunxin.cb.mall.common.BaseService;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import com.yunxin.cb.mall.entity.CarOpenCity;

/**
 * 已开通城市表 服务接口类
 */
public interface CarOpenCityService extends BaseService {

	/**
	 * 根据查询条件，查询并返回CarOpenCity的列表
	 * @param q 查询条件
	 * @return
	 */
	public List<CarOpenCity> getCarOpenCityList(Query q);
	
	/**
	 * 根据查询条件，分页查询并返回CarOpenCity的分页结果
	 * @param q 查询条件
	 * @return
	 */
	public PageFinder<CarOpenCity> getCarOpenCityPagedList(Query q);
	
	/**
	 * 根据主键，查询并返回一个CarOpenCity对象
	 * @param id 主键id
	 * @return
	 */
	public CarOpenCity getCarOpenCity(Integer id);

	/**
	 * 根据主键数组，查询并返回一组CarOpenCity对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	public List<CarOpenCity> getCarOpenCityByIds(Integer[] ids);
	
	/**
	 * 根据主键，删除特定的CarOpenCity记录
	 * @param id 主键id
	 * @return
	 */
	public int delCarOpenCity(Integer id);
	
	/**
	 * 新增一条CarOpenCity记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param carOpenCity 新增的CarOpenCity数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @return
	 */
	public int addCarOpenCity(CarOpenCity carOpenCity);
	
	/**
	 * 根据主键，更新一条CarOpenCity记录
	 * @param carOpenCity 更新的CarOpenCity数据，且其主键不能为空
	 * @return
	 */
	public int updateCarOpenCity(CarOpenCity carOpenCity);

	/**
	 * 生成主键，如果表主键自增，则本方法可直接返回null；如非自增主键，则本方法必须返回一个大于0的值。
	 * @return
	 */
	public Integer generatePK();
	
		/**
	 * 为CarOpenCity对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(CarOpenCity obj);
		
}
