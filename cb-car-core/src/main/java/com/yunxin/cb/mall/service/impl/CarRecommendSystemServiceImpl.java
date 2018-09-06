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
import com.yunxin.cb.mall.dao.CarRecommendSystemDao;
import com.yunxin.cb.mall.entity.CarRecommendSystem;
import com.yunxin.cb.mall.service.CarRecommendSystemService;
import com.yunxin.cb.util.DateUtils;

/**
 * 推荐车系中间表 服务实现类
 */
@Service
public class CarRecommendSystemServiceImpl implements CarRecommendSystemService {

	private static final Log log = LogFactory.getLog(CarRecommendSystemServiceImpl.class);
	
	@Resource
	private CarRecommendSystemDao carRecommendSystemDao;
	

	/**
	 * 根据查询条件，查询并返回CarRecommendSystem的列表
	 * @param q 查询条件
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<CarRecommendSystem> getCarRecommendSystemList(Query q) {
		List<CarRecommendSystem> list = null;
		try {
			//直接调用Dao方法进行查询
			list = carRecommendSystemDao.queryAll(q);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<CarRecommendSystem>(0) : list;
		return list; 
	}
	
	/**
	 * 根据查询条件，分页查询并返回CarRecommendSystem的分页结果
	 * @param q 查询条件
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public PageFinder<CarRecommendSystem> getCarRecommendSystemPagedList(Query q) {
		
		List<CarRecommendSystem> list = null;
		long rowCount = 0L;
		
		try {
			//调用dao查询满足条件的分页数据
			list = carRecommendSystemDao.pageList(q);
			//调用dao统计满足条件的记录总数
			rowCount = carRecommendSystemDao.count(q);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<CarRecommendSystem>(0) : list;
	
		//将分页数据和记录总数设置到分页结果对象中
		PageFinder<CarRecommendSystem> page = new PageFinder<CarRecommendSystem>(q.getPageNo(), q.getPageSize(),rowCount,list);
		
		return page;
	}	
	
	/**
	 * 根据主键，查询并返回一个CarRecommendSystem对象
	 * @param id 主键id
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public CarRecommendSystem getCarRecommendSystem(Integer id) {
		CarRecommendSystem obj = null;
		if (id == null || id <= 0) { //传入的主键无效时直接返回null
			log.info("param error : " + " id = " + id);
			return obj;
		}
		try {
			//调用dao，根据主键查询
			obj = carRecommendSystemDao.get(id); 
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return obj;
	}

	/**
	 * 根据主键数组，查询并返回一组CarRecommendSystem对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<CarRecommendSystem> getCarRecommendSystemByIds(Integer[] ids) {
		List<CarRecommendSystem> list = null;
		if (ids == null || ids.length == 0) {
			log.info("param error : " + " ids is null or empty array.");
		} else {
			try {
				//调用dao，根据主键数组查询
				list = carRecommendSystemDao.getByIds(ids);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
		
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<CarRecommendSystem>(0) : list;

		return list;
	}	
	
	/**
	 * 根据主键，删除特定的CarRecommendSystem记录
	 * @param id 主键id
	 * @return
	 */
	@Transactional
	public int delCarRecommendSystem(Integer id) {
		int count = 0;
		if (id == null || id <= 0) { //传入的主键无效时直接返回失败结果
			log.info("param error : " + " id = " + id);
			return count;
		}
		try {
		    //调用Dao执行删除，并判断删除语句执行结果
			count = carRecommendSystemDao.delete(id);		  
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return count;
	}
		
	/**
	 * 新增一条CarRecommendSystem记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param carRecommendSystem 新增的CarRecommendSystem数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @return
	 */
	@Transactional
	public int addCarRecommendSystem(CarRecommendSystem carRecommendSystem) {
		int count = 0;
		
		if (carRecommendSystem != null) { //传入参数无效时直接返回失败结果
			try {
				//如果传入参数的主键为null，则调用生成主键的方法获取新的主键
				if (carRecommendSystem.getId() == null) {
					carRecommendSystem.setId(this.generatePK());
				}
				
				//设置创建时间和更新时间为当前时间
				Date now = DateUtils.getTimeNow();
				carRecommendSystem.setCreateTime(now);
				carRecommendSystem.setUpdateTime(now);
				
				//填充默认值
				this.fillDefaultValues(carRecommendSystem);
				
				//调用Dao执行插入操作
				carRecommendSystemDao.add(carRecommendSystem);
				if (carRecommendSystem.getId() != null) {
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
	 * 根据主键，更新一条CarRecommendSystem记录
	 * @param carRecommendSystem 更新的CarRecommendSystem数据，且其主键不能为空
	 * @return
	 */
	@Transactional
	public int updateCarRecommendSystem(CarRecommendSystem carRecommendSystem) {
		int count = 0;
				
		if (carRecommendSystem != null && carRecommendSystem.getId() != null) { //传入参数无效时直接返回失败结果
			try {
				//设置更新时间为当前时间
				carRecommendSystem.setUpdateTime(DateUtils.getTimeNow());
				
				//调用Dao执行更新操作，并判断更新语句执行结果
				count = carRecommendSystemDao.update(carRecommendSystem);			
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
	 * 为CarRecommendSystem对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(CarRecommendSystem obj) {
		if (obj != null) {
		}
	}

}
