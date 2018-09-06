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
import com.yunxin.cb.mall.dao.CarTestDriveDao;
import com.yunxin.cb.mall.entity.CarTestDrive;
import com.yunxin.cb.mall.service.CarTestDriveService;
import com.yunxin.cb.util.DateUtils;

/**
 * 试驾信息 服务实现类
 */
@Service
public class CarTestDriveServiceImpl implements CarTestDriveService {

	private static final Log log = LogFactory.getLog(CarTestDriveServiceImpl.class);
	
	@Resource
	private CarTestDriveDao carTestDriveDao;
	

	/**
	 * 根据查询条件，查询并返回CarTestDrive的列表
	 * @param q 查询条件
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<CarTestDrive> getCarTestDriveList(Query q) {
		List<CarTestDrive> list = null;
		try {
			//已删除的不查出
			CarTestDrive carTestDrive = (CarTestDrive)q.getData();
			if (carTestDrive != null) {
				carTestDrive.setIsDelete(0);
			}
					
			//直接调用Dao方法进行查询
			list = carTestDriveDao.queryAll(q);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<CarTestDrive>(0) : list;
		return list; 
	}
	
	/**
	 * 根据查询条件，分页查询并返回CarTestDrive的分页结果
	 * @param q 查询条件
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public PageFinder<CarTestDrive> getCarTestDrivePagedList(Query q) {
		
		List<CarTestDrive> list = null;
		long rowCount = 0L;
		
		try {
			//已删除的不查出
			CarTestDrive carTestDrive = (CarTestDrive)q.getData();
			if (carTestDrive != null) {
				carTestDrive.setIsDelete(0);
			}
					
			//调用dao查询满足条件的分页数据
			list = carTestDriveDao.pageList(q);
			//调用dao统计满足条件的记录总数
			rowCount = carTestDriveDao.count(q);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<CarTestDrive>(0) : list;
	
		//将分页数据和记录总数设置到分页结果对象中
		PageFinder<CarTestDrive> page = new PageFinder<CarTestDrive>(q.getPageNo(), q.getPageSize(),rowCount,list);
		
		return page;
	}	
	
	/**
	 * 根据主键，查询并返回一个CarTestDrive对象
	 * @param id 主键id
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public CarTestDrive getCarTestDrive(Integer id) {
		CarTestDrive obj = null;
		if (id == null || id <= 0) { //传入的主键无效时直接返回null
			log.info("param error : " + " id = " + id);
			return obj;
		}
		try {
			//调用dao，根据主键查询
			obj = carTestDriveDao.get(id); 
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return obj;
	}

	/**
	 * 根据主键数组，查询并返回一组CarTestDrive对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<CarTestDrive> getCarTestDriveByIds(Integer[] ids) {
		List<CarTestDrive> list = null;
		if (ids == null || ids.length == 0) {
			log.info("param error : " + " ids is null or empty array.");
		} else {
			try {
				//调用dao，根据主键数组查询
				list = carTestDriveDao.getByIds(ids);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
		
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<CarTestDrive>(0) : list;

		return list;
	}	
	
	/**
	 * 根据主键，删除特定的CarTestDrive记录
	 * @param id 主键id
	 * @return
	 */
	@Transactional
	public int delCarTestDrive(Integer id) {
		int count = 0;
		if (id == null || id <= 0) { //传入的主键无效时直接返回失败结果
			log.info("param error : " + " id = " + id);
			return count;
		}
		try {
			//逻辑删除，实际上执行update语句，以下设置待更新记录的主键、删除标识、更新时间、操作人信息等
			CarTestDrive carTestDrive = new CarTestDrive();
			carTestDrive.setId(id);
			carTestDrive.setIsDelete(1);
			carTestDrive.setUpdateTime(new Date());
			
			//调用Dao执行更新操作，并判断更新语句执行结果
			count = carTestDriveDao.update(carTestDrive);			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return count;
	}
		
	/**
	 * 新增一条CarTestDrive记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param carTestDrive 新增的CarTestDrive数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @return
	 */
	@Transactional
	public int addCarTestDrive(CarTestDrive carTestDrive) {
		int count = 0;
		
		if (carTestDrive != null) { //传入参数无效时直接返回失败结果
			try {
				//如果传入参数的主键为null，则调用生成主键的方法获取新的主键
				if (carTestDrive.getId() == null) {
					carTestDrive.setId(this.generatePK());
				}
				
				//设置创建时间和更新时间为当前时间
				Date now = DateUtils.getTimeNow();
				carTestDrive.setCreateTime(now);
				carTestDrive.setUpdateTime(now);
				
				//填充默认值
				this.fillDefaultValues(carTestDrive);
				
				//调用Dao执行插入操作
				carTestDriveDao.add(carTestDrive);
				if (carTestDrive.getId() != null) {
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
	 * 根据主键，更新一条CarTestDrive记录
	 * @param carTestDrive 更新的CarTestDrive数据，且其主键不能为空
	 * @return
	 */
	@Transactional
	public int updateCarTestDrive(CarTestDrive carTestDrive) {
		int count = 0;
				
		if (carTestDrive != null && carTestDrive.getId() != null) { //传入参数无效时直接返回失败结果
			try {
				//设置更新时间为当前时间
				carTestDrive.setUpdateTime(DateUtils.getTimeNow());
				
				//调用Dao执行更新操作，并判断更新语句执行结果
				count = carTestDriveDao.update(carTestDrive);			
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
	 * 为CarTestDrive对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(CarTestDrive obj) {
		if (obj != null) {
		}
	}

}
