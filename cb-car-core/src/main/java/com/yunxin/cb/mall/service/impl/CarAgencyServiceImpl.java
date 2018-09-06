package com.yunxin.cb.mall.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.yunxin.cb.mall.common.PageFinder;
import com.yunxin.cb.mall.common.Query;
import com.yunxin.cb.mall.dao.CarAgencyDao;
import com.yunxin.cb.mall.entity.CarAgency;
import com.yunxin.cb.mall.service.CarAgencyService;
import com.yunxin.cb.util.DateUtils;

/**
 * 代理商(卖家,商家) 服务实现类
 */
@Service
public class CarAgencyServiceImpl implements CarAgencyService {

	private static final Log log = LogFactory.getLog(CarAgencyServiceImpl.class);
	
	@Resource
	private CarAgencyDao carAgencyDao;
	

	/**
	 * 根据查询条件，查询并返回CarAgency的列表
	 * @param q 查询条件
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<CarAgency> getCarAgencyList(Query q) {
		List<CarAgency> list = null;
		try {
			//已删除的不查出
			CarAgency carAgency = (CarAgency)q.getData();
			if (carAgency != null) {
				carAgency.setIsDelete(0);
			}
					
			//直接调用Dao方法进行查询
			list = carAgencyDao.queryAll(q);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<CarAgency>(0) : list;
		return list; 
	}
	
	/**
	 * 根据查询条件，分页查询并返回CarAgency的分页结果
	 * @param q 查询条件
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public PageFinder<CarAgency> getCarAgencyPagedList(Query q) {
		
		List<CarAgency> list = null;
		long rowCount = 0L;
		
		try {
			//已删除的不查出
			CarAgency carAgency = (CarAgency)q.getData();
			if (carAgency != null) {
				carAgency.setIsDelete(0);
			}
					
			//调用dao查询满足条件的分页数据
			list = carAgencyDao.pageList(q);
			//调用dao统计满足条件的记录总数
			rowCount = carAgencyDao.count(q);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<CarAgency>(0) : list;
	
		//将分页数据和记录总数设置到分页结果对象中
		PageFinder<CarAgency> page = new PageFinder<CarAgency>(q.getPageNo(), q.getPageSize(),rowCount,list);
		
		return page;
	}	
	
	/**
	 * 根据主键，查询并返回一个CarAgency对象
	 * @param id 主键id
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public CarAgency getCarAgency(Integer id) {
		CarAgency obj = null;
		if (id == null || id <= 0) { //传入的主键无效时直接返回null
			log.info("param error : " + " id = " + id);
			return obj;
		}
		try {
			//调用dao，根据主键查询
			obj = carAgencyDao.get(id); 
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return obj;
	}

	/**
	 * 根据主键数组，查询并返回一组CarAgency对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<CarAgency> getCarAgencyByIds(Integer[] ids) {
		List<CarAgency> list = null;
		if (ids == null || ids.length == 0) {
			log.info("param error : " + " ids is null or empty array.");
		} else {
			try {
				//调用dao，根据主键数组查询
				list = carAgencyDao.getByIds(ids);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
		
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<CarAgency>(0) : list;

		return list;
	}	
	
	/**
	 * 根据主键，删除特定的CarAgency记录
	 * @param id 主键id
	 * @return
	 */
	@Transactional
	public int delCarAgency(Integer id) {
		int count = 0;
		if (id == null || id <= 0) { //传入的主键无效时直接返回失败结果
			log.info("param error : " + " id = " + id);
			return count;
		}
		try {
			//逻辑删除，实际上执行update语句，以下设置待更新记录的主键、删除标识、更新时间、操作人信息等
			CarAgency carAgency = new CarAgency();
			carAgency.setId(id);
			carAgency.setIsDelete(1);
			carAgency.setUpdateTime(new Date());
			
			//调用Dao执行更新操作，并判断更新语句执行结果
			count = carAgencyDao.update(carAgency);			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return count;
	}
		
	/**
	 * 新增一条CarAgency记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param carAgency 新增的CarAgency数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @return
	 */
	@Transactional
	public int addCarAgency(CarAgency carAgency) {
		int count = 0;
		
		if (carAgency != null) { //传入参数无效时直接返回失败结果
			try {
				//如果传入参数的主键为null，则调用生成主键的方法获取新的主键
				if (carAgency.getId() == null) {
					carAgency.setId(this.generatePK());
				}
				
				//设置创建时间和更新时间为当前时间
				Date now = DateUtils.getTimeNow();
				carAgency.setCreateTime(now);
				carAgency.setUpdateTime(now);
				
				//填充默认值
				this.fillDefaultValues(carAgency);
				
				//调用Dao执行插入操作
				carAgencyDao.add(carAgency);
				if (carAgency.getId() != null) {
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
	 * 根据主键，更新一条CarAgency记录
	 * @param carAgency 更新的CarAgency数据，且其主键不能为空
	 * @return
	 */
	@Transactional
	public int updateCarAgency(CarAgency carAgency) {
		int count = 0;
				
		if (carAgency != null && carAgency.getId() != null) { //传入参数无效时直接返回失败结果
			try {
				//设置更新时间为当前时间
				carAgency.setUpdateTime(DateUtils.getTimeNow());
				
				//调用Dao执行更新操作，并判断更新语句执行结果
				count = carAgencyDao.update(carAgency);			
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
	 * 为CarAgency对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(CarAgency obj) {
		if (obj != null) {
		}
	}

}
