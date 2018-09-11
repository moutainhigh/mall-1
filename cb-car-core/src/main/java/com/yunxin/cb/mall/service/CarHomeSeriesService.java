package com.yunxin.cb.mall.service;

import java.util.List;

import com.yunxin.cb.mall.common.BaseService;
import com.yunxin.cb.mall.common.PageFinder;
import com.yunxin.cb.mall.common.Query;
import com.yunxin.cb.mall.entity.CarHomeSeries;

/**
 * 汽车之家的车系,与品牌相关 服务接口类
 */
public interface CarHomeSeriesService extends BaseService {

	/**
	 * 根据查询条件，查询并返回CarHomeSeries的列表
	 * @param q 查询条件
	 * @return
	 */
	public List<CarHomeSeries> getCarHomeSeriesList(Query q);
	
	/**
	 * 根据查询条件，分页查询并返回CarHomeSeries的分页结果
	 * @param q 查询条件
	 * @return
	 */
	public PageFinder<CarHomeSeries> getCarHomeSeriesPagedList(Query q);
	
	/**
	 * 根据主键，查询并返回一个CarHomeSeries对象
	 * @param id 主键id
	 * @return
	 */
	public CarHomeSeries getCarHomeSeries(Integer id);

	/**
	 * 根据主键数组，查询并返回一组CarHomeSeries对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	public List<CarHomeSeries> getCarHomeSeriesByIds(Integer[] ids);
	
	/**
	 * 根据主键，删除特定的CarHomeSeries记录
	 * @param id 主键id
	 * @return
	 */
	public int delCarHomeSeries(Integer id);
	
	/**
	 * 新增一条CarHomeSeries记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param carHomeSeries 新增的CarHomeSeries数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @return
	 */
	public int addCarHomeSeries(CarHomeSeries carHomeSeries);
	
	/**
	 * 根据主键，更新一条CarHomeSeries记录
	 * @param carHomeSeries 更新的CarHomeSeries数据，且其主键不能为空
	 * @return
	 */
	public int updateCarHomeSeries(CarHomeSeries carHomeSeries);

	/**
	 * 生成主键，如果表主键自增，则本方法可直接返回null；如非自增主键，则本方法必须返回一个大于0的值。
	 * @return
	 */
	public Integer generatePK();

		/**
	 * 为CarHomeSeries对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(CarHomeSeries obj);
		
}
