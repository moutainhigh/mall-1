package com.yunxin.cb.mall.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.yunxin.cb.util.page.PageFinder;
import com.yunxin.cb.util.page.Query;
import com.yunxin.cb.mall.dao.CarOpenCityDao;
import com.yunxin.cb.mall.entity.CarOpenCity;
import com.yunxin.cb.mall.service.CarOpenCityService;
import com.yunxin.cb.util.DateUtils;

/**
 * 已开通城市表 服务实现类
 */
@Service
public class CarOpenCityServiceImpl implements CarOpenCityService {

	private static final Log log = LogFactory.getLog(CarOpenCityServiceImpl.class);
	
	@Resource
	private CarOpenCityDao carOpenCityDao;
	

	/**
	 * 根据查询条件，查询并返回CarOpenCity的列表
	 * @param q 查询条件
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<CarOpenCity> getCarOpenCityList(Query q) {
		List<CarOpenCity> list = null;
		try {
			//直接调用Dao方法进行查询
			list = carOpenCityDao.queryAll(q);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<CarOpenCity>(0) : list;
		return list; 
	}
	
	/**
	 * 根据查询条件，分页查询并返回CarOpenCity的分页结果
	 * @param q 查询条件
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public PageFinder<CarOpenCity> getCarOpenCityPagedList(Query q) {
		
		List<CarOpenCity> list = null;
		long rowCount = 0L;
		
		try {
			//调用dao查询满足条件的分页数据
			list = carOpenCityDao.pageList(q);
			//调用dao统计满足条件的记录总数
			rowCount = carOpenCityDao.count(q);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<CarOpenCity>(0) : list;
	
		//将分页数据和记录总数设置到分页结果对象中
		PageFinder<CarOpenCity> page = new PageFinder<CarOpenCity>(q.getPageNo(), q.getPageSize(),rowCount,list);
		
		return page;
	}	
	
	/**
	 * 根据主键，查询并返回一个CarOpenCity对象
	 * @param id 主键id
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public CarOpenCity getCarOpenCity(Integer id) {
		CarOpenCity obj = null;
		if (id == null || id <= 0) { //传入的主键无效时直接返回null
			log.info("param error : " + " id = " + id);
			return obj;
		}
		try {
			//调用dao，根据主键查询
			obj = carOpenCityDao.get(id); 
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return obj;
	}

	/**
	 * 根据主键数组，查询并返回一组CarOpenCity对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<CarOpenCity> getCarOpenCityByIds(Integer[] ids) {
		List<CarOpenCity> list = null;
		if (ids == null || ids.length == 0) {
			log.info("param error : " + " ids is null or empty array.");
		} else {
			try {
				//调用dao，根据主键数组查询
				list = carOpenCityDao.getByIds(ids);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
		
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<CarOpenCity>(0) : list;

		return list;
	}	
	
	/**
	 * 根据主键，删除特定的CarOpenCity记录
	 * @param id 主键id
	 * @return
	 */
	@Transactional
	public int delCarOpenCity(Integer id) {
		int count = 0;
		if (id == null || id <= 0) { //传入的主键无效时直接返回失败结果
			log.info("param error : " + " id = " + id);
			return count;
		}
		try {
		    //调用Dao执行删除，并判断删除语句执行结果
			count = carOpenCityDao.delete(id);		  
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return count;
	}
		
	/**
	 * 新增一条CarOpenCity记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param carOpenCity 新增的CarOpenCity数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @return
	 */
	@Transactional
	public int addCarOpenCity(CarOpenCity carOpenCity) {
		int count = 0;
		
		if (carOpenCity != null) { //传入参数无效时直接返回失败结果
			try {
				//如果传入参数的主键为null，则调用生成主键的方法获取新的主键
				if (carOpenCity.getId() == null) {
					carOpenCity.setId(this.generatePK());
				}
				
				//设置创建时间和更新时间为当前时间
				Date now = DateUtils.getTimeNow();
				carOpenCity.setCreateTime(now);
				carOpenCity.setUpdateTime(now);
				
				//填充默认值
				this.fillDefaultValues(carOpenCity);
				
				//调用Dao执行插入操作
				carOpenCityDao.add(carOpenCity);
				if (carOpenCity.getId() != null) {
					count = 1;
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}	
		}
		
		return count;
	}			
	
	/**
	 * 根据主键，更新一条CarOpenCity记录
	 * @param carOpenCity 更新的CarOpenCity数据，且其主键不能为空
	 * @return
	 */
	@Transactional
	public int updateCarOpenCity(CarOpenCity carOpenCity) {
		int count = 0;
				
		if (carOpenCity != null && carOpenCity.getId() != null) { //传入参数无效时直接返回失败结果
			try {
				//设置更新时间为当前时间
				carOpenCity.setUpdateTime(DateUtils.getTimeNow());
				
				//调用Dao执行更新操作，并判断更新语句执行结果
				count = carOpenCityDao.update(carOpenCity);			
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			}
		}
		
		return count;
	}	
	
	/**
	 * 生成主键，如果表主键自增，则本方法可直接返回null；如非自增主键，则本方法必须返回一个大于0的值。
	 * @return
	 */
	public Integer generatePK() {
		return null;
	}
	
	/**
	 * 为CarOpenCity对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(CarOpenCity obj) {
		if (obj != null) {
		    if (obj.getIsDisplay() == null) {
		    	obj.setIsDisplay();
		    }
		    if (obj.getIsHot() == null) {
		    	obj.setIsHot();
		    }
		}
	}

}
