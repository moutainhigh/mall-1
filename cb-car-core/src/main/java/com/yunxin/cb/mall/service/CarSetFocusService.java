package com.yunxin.cb.mall.service;

import java.util.List;

import com.yunxin.cb.mall.common.BaseService;
import com.yunxin.cb.mall.common.PageFinder;
import com.yunxin.cb.mall.common.Query;
import com.yunxin.cb.mall.entity.CarSetFocus;

/**
 * 设置中心 服务接口类
 */
public interface CarSetFocusService extends BaseService {

	/**
	 * 根据查询条件，查询并返回CarSetFocus的列表
	 * @param q 查询条件
	 * @return
	 */
	public List<CarSetFocus> getCarSetFocusList(Query q);
	
	/**
	 * 根据查询条件，分页查询并返回CarSetFocus的分页结果
	 * @param q 查询条件
	 * @return
	 */
	public PageFinder<CarSetFocus> getCarSetFocusPagedList(Query q);
	
	/**
	 * 根据主键，查询并返回一个CarSetFocus对象
	 * @param id 主键id
	 * @return
	 */
	public CarSetFocus getCarSetFocus(Integer id);

	/**
	 * 根据主键数组，查询并返回一组CarSetFocus对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	public List<CarSetFocus> getCarSetFocusByIds(Integer[] ids);
	
	/**
	 * 根据主键，删除特定的CarSetFocus记录
	 * @param id 主键id
	 * @return
	 */
	public int delCarSetFocus(Integer id);
	
	/**
	 * 新增一条CarSetFocus记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param carSetFocus 新增的CarSetFocus数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @return
	 */
	public int addCarSetFocus(CarSetFocus carSetFocus);
	
	/**
	 * 根据主键，更新一条CarSetFocus记录
	 * @param carSetFocus 更新的CarSetFocus数据，且其主键不能为空
	 * @return
	 */
	public int updateCarSetFocus(CarSetFocus carSetFocus);

	/**
	 * 生成主键，如果表主键自增，则本方法可直接返回null；如非自增主键，则本方法必须返回一个大于0的值。
	 * @return
	 */
	public Integer generatePK();

		/**
	 * 为CarSetFocus对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(CarSetFocus obj);
		
}
