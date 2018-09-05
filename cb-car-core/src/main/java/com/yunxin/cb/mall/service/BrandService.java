package com.yunxin.cb.mall.service;

import java.util.List;

import com.yunxin.cb.mall.common.BaseService;
import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import com.yunxin.cb.mall.entity.Brand;

/**
 * Brand 服务接口类
 */
public interface BrandService extends BaseService {

	/**
	 * 根据查询条件，查询并返回Brand的列表
	 * @param q 查询条件
	 * @return
	 */
	public List<Brand> getBrandList(Query q);
	
	/**
	 * 根据查询条件，分页查询并返回Brand的分页结果
	 * @param q 查询条件
	 * @return
	 */
	public PageFinder<Brand> getBrandPagedList(Query q);
	
	/**
	 * 根据主键，查询并返回一个Brand对象
	 * @param id 主键id
	 * @return
	 */
	public Brand getBrand(Integer id);

	/**
	 * 根据主键数组，查询并返回一组Brand对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	public List<Brand> getBrandByIds(Integer[] ids);
	
	/**
	 * 根据主键，删除特定的Brand记录
	 * @param id 主键id
	 * @return
	 */
	public int delBrand(Integer id);
	
	/**
	 * 新增一条Brand记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param brand 新增的Brand数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @return
	 */
	public int addBrand(Brand brand);
	
	/**
	 * 根据主键，更新一条Brand记录
	 * @param brand 更新的Brand数据，且其主键不能为空
	 * @return
	 */
	public int updateBrand(Brand brand);

	/**
	 * 生成主键，如果表主键自增，则本方法可直接返回null；如非自增主键，则本方法必须返回一个大于0的值。
	 * @return
	 */
	public Integer generatePK();
	
		/**
	 * 为Brand对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(Brand obj);
		
}
