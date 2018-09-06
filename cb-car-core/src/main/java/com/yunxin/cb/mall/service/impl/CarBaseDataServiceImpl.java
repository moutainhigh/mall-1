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
import com.yunxin.cb.mall.dao.CarBaseDataDao;
import com.yunxin.cb.mall.entity.CarBaseData;
import com.yunxin.cb.mall.service.CarBaseDataService;
import com.yunxin.cb.util.DateUtils;

/**
 * 基础数据表 服务实现类
 */
@Service
public class CarBaseDataServiceImpl implements CarBaseDataService {

	private static final Log log = LogFactory.getLog(CarBaseDataServiceImpl.class);
	
	@Resource
	private CarBaseDataDao carBaseDataDao;
	

	/**
	 * 根据查询条件，查询并返回CarBaseData的列表
	 * @param q 查询条件
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<CarBaseData> getCarBaseDataList(Query q) {
		List<CarBaseData> list = null;
		try {
			//直接调用Dao方法进行查询
			list = carBaseDataDao.queryAll(q);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<CarBaseData>(0) : list;
		return list; 
	}
	
	/**
	 * 根据查询条件，分页查询并返回CarBaseData的分页结果
	 * @param q 查询条件
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public PageFinder<CarBaseData> getCarBaseDataPagedList(Query q) {
		
		List<CarBaseData> list = null;
		long rowCount = 0L;
		
		try {
			//调用dao查询满足条件的分页数据
			list = carBaseDataDao.pageList(q);
			//调用dao统计满足条件的记录总数
			rowCount = carBaseDataDao.count(q);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<CarBaseData>(0) : list;
	
		//将分页数据和记录总数设置到分页结果对象中
		PageFinder<CarBaseData> page = new PageFinder<CarBaseData>(q.getPageNo(), q.getPageSize(),rowCount,list);
		
		return page;
	}	
	
	/**
	 * 根据主键，查询并返回一个CarBaseData对象
	 * @param id 主键id
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public CarBaseData getCarBaseData(Integer id) {
		CarBaseData obj = null;
		if (id == null || id <= 0) { //传入的主键无效时直接返回null
			log.info("param error : " + " id = " + id);
			return obj;
		}
		try {
			//调用dao，根据主键查询
			obj = carBaseDataDao.get(id); 
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return obj;
	}

	/**
	 * 根据主键数组，查询并返回一组CarBaseData对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<CarBaseData> getCarBaseDataByIds(Integer[] ids) {
		List<CarBaseData> list = null;
		if (ids == null || ids.length == 0) {
			log.info("param error : " + " ids is null or empty array.");
		} else {
			try {
				//调用dao，根据主键数组查询
				list = carBaseDataDao.getByIds(ids);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
		
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<CarBaseData>(0) : list;

		return list;
	}	
	
	/**
	 * 根据主键，删除特定的CarBaseData记录
	 * @param id 主键id
	 * @return
	 */
	@Transactional
	public int delCarBaseData(Integer id) {
		int count = 0;
		if (id == null || id <= 0) { //传入的主键无效时直接返回失败结果
			log.info("param error : " + " id = " + id);
			return count;
		}
		try {
		    //调用Dao执行删除，并判断删除语句执行结果
			count = carBaseDataDao.delete(id);		  
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return count;
	}
		
	/**
	 * 新增一条CarBaseData记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param carBaseData 新增的CarBaseData数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @return
	 */
	@Transactional
	public int addCarBaseData(CarBaseData carBaseData) {
		int count = 0;
		
		if (carBaseData != null) { //传入参数无效时直接返回失败结果
			try {
				//如果传入参数的主键为null，则调用生成主键的方法获取新的主键
				if (carBaseData.getId() == null) {
					carBaseData.setId(this.generatePK());
				}
				
				//设置创建时间和更新时间为当前时间
				Date now = DateUtils.getTimeNow();
				carBaseData.setCreateTime(now);
				carBaseData.setUpdateTime(now);
				
				//填充默认值
				this.fillDefaultValues(carBaseData);
				
				//调用Dao执行插入操作
				carBaseDataDao.add(carBaseData);
				if (carBaseData.getId() != null) {
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
	 * 根据主键，更新一条CarBaseData记录
	 * @param carBaseData 更新的CarBaseData数据，且其主键不能为空
	 * @return
	 */
	@Transactional
	public int updateCarBaseData(CarBaseData carBaseData) {
		int count = 0;
				
		if (carBaseData != null && carBaseData.getId() != null) { //传入参数无效时直接返回失败结果
			try {
				//设置更新时间为当前时间
				carBaseData.setUpdateTime(DateUtils.getTimeNow());
				
				//调用Dao执行更新操作，并判断更新语句执行结果
				count = carBaseDataDao.update(carBaseData);			
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
	 * 为CarBaseData对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(CarBaseData obj) {
		if (obj != null) {
		    if (obj.getEnabled() == null) {
		    	obj.setEnabled();
		    }
		    if (obj.getSortOrder() == null) {
		    	obj.setSortOrder(0);
		    }
		}
	}

}
