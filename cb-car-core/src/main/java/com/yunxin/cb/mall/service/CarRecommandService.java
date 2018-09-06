package com.yunxin.cb.mall.service;

import java.util.List;

import com.yunxin.cb.mall.common.BaseService;
import com.yunxin.cb.mall.common.PageFinder;
import com.yunxin.cb.mall.common.Query;
import com.yunxin.cb.mall.entity.CarRecommand;

/**
 * 推荐管理 服务接口类
 */
public interface CarRecommandService extends BaseService {

	/**
	 * 根据查询条件，查询并返回CarRecommand的列表
	 * @param q 查询条件
	 * @return
	 */
	public List<CarRecommand> getCarRecommandList(Query q);
	
	/**
	 * 根据查询条件，分页查询并返回CarRecommand的分页结果
	 * @param q 查询条件
	 * @return
	 */
	public PageFinder<CarRecommand> getCarRecommandPagedList(Query q);
	
	/**
	 * 根据主键，查询并返回一个CarRecommand对象
	 * @param id 主键id
	 * @return
	 */
	public CarRecommand getCarRecommand(Integer id);

	/**
	 * 根据主键数组，查询并返回一组CarRecommand对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	public List<CarRecommand> getCarRecommandByIds(Integer[] ids);
	
	/**
	 * 根据主键，删除特定的CarRecommand记录
	 * @param id 主键id
	 * @return
	 */
	public int delCarRecommand(Integer id);
	
	/**
	 * 新增一条CarRecommand记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param carRecommand 新增的CarRecommand数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @return
	 */
	public int addCarRecommand(CarRecommand carRecommand);
	
	/**
	 * 根据主键，更新一条CarRecommand记录
	 * @param carRecommand 更新的CarRecommand数据，且其主键不能为空
	 * @return
	 */
	public int updateCarRecommand(CarRecommand carRecommand);

	/**
	 * 生成主键，如果表主键自增，则本方法可直接返回null；如非自增主键，则本方法必须返回一个大于0的值。
	 * @return
	 */
	public Integer generatePK();

		/**
	 * 为CarRecommand对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(CarRecommand obj);
		
}
