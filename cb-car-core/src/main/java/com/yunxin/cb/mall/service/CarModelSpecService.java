package com.yunxin.cb.mall.service;

import java.util.List;

import com.yunxin.cb.mall.common.BaseService;
import com.yunxin.cb.mall.common.PageFinder;
import com.yunxin.cb.mall.common.Query;
import com.yunxin.cb.mall.entity.CarModelSpec;

/**
 * 汽车配置信息 服务接口类
 */
public interface CarModelSpecService extends BaseService {

	/**
	 * 根据查询条件，查询并返回CarModelSpec的列表
	 * @param q 查询条件
	 * @return
	 */
	public List<CarModelSpec> getCarModelSpecList(Query q);
	
	/**
	 * 根据查询条件，分页查询并返回CarModelSpec的分页结果
	 * @param q 查询条件
	 * @return
	 */
	public PageFinder<CarModelSpec> getCarModelSpecPagedList(Query q);
	
	/**
	 * 根据主键，查询并返回一个CarModelSpec对象
	 * @param id 主键id
	 * @return
	 */
	public CarModelSpec getCarModelSpec(Long id);

	/**
	 * 根据主键数组，查询并返回一组CarModelSpec对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	public List<CarModelSpec> getCarModelSpecByIds(Long[] ids);
	
	/**
	 * 根据主键，删除特定的CarModelSpec记录
	 * @param id 主键id
	 * @return
	 */
	public int delCarModelSpec(Long id);
	
	/**
	 * 新增一条CarModelSpec记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param carModelSpec 新增的CarModelSpec数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @return
	 */
	public int addCarModelSpec(CarModelSpec carModelSpec);
	
	/**
	 * 根据主键，更新一条CarModelSpec记录
	 * @param carModelSpec 更新的CarModelSpec数据，且其主键不能为空
	 * @return
	 */
	public int updateCarModelSpec(CarModelSpec carModelSpec);

	/**
	 * 生成主键，如果表主键自增，则本方法可直接返回null；如非自增主键，则本方法必须返回一个大于0的值。
	 * @return
	 */
	public Long generatePK();

		/**
	 * 为CarModelSpec对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(CarModelSpec obj);
		
}