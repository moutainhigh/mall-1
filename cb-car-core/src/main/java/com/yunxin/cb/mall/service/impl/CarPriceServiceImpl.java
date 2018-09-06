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
import com.yunxin.cb.mall.dao.CarPriceDao;
import com.yunxin.cb.mall.entity.CarPrice;
import com.yunxin.cb.mall.service.CarPriceService;
import com.yunxin.cb.util.DateUtils;

/**
 * 车型价格信息 服务实现类
 */
@Service
public class CarPriceServiceImpl implements CarPriceService {

	private static final Log log = LogFactory.getLog(CarPriceServiceImpl.class);
	
	@Resource
	private CarPriceDao carPriceDao;
	

	/**
	 * 根据查询条件，查询并返回CarPrice的列表
	 * @param q 查询条件
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<CarPrice> getCarPriceList(Query q) {
		List<CarPrice> list = null;
		try {
			//已删除的不查出
			CarPrice carPrice = (CarPrice)q.getQ();
			if (carPrice != null) {
				carPrice.setIsDelete(0);
			}
					
			//直接调用Dao方法进行查询
			list = carPriceDao.queryAll(q);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<CarPrice>(0) : list;
		return list; 
	}
	
	/**
	 * 根据查询条件，分页查询并返回CarPrice的分页结果
	 * @param q 查询条件
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public PageFinder<CarPrice> getCarPricePagedList(Query q) {
		
		List<CarPrice> list = null;
		long rowCount = 0L;
		
		try {
			//已删除的不查出
			CarPrice carPrice = (CarPrice)q.getQ();
			if (carPrice != null) {
				carPrice.setIsDelete(0);
			}
					
			//调用dao查询满足条件的分页数据
			list = carPriceDao.pageList(q);
			//调用dao统计满足条件的记录总数
			rowCount = carPriceDao.count(q);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<CarPrice>(0) : list;
	
		//将分页数据和记录总数设置到分页结果对象中
		PageFinder<CarPrice> page = new PageFinder<CarPrice>(q.getPageNo(), q.getPageSize(),rowCount,list);
		
		return page;
	}	
	
	/**
	 * 根据主键，查询并返回一个CarPrice对象
	 * @param id 主键id
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public CarPrice getCarPrice(Integer id) {
		CarPrice obj = null;
		if (id == null || id <= 0) { //传入的主键无效时直接返回null
			log.info("param error : " + " id = " + id);
			return obj;
		}
		try {
			//调用dao，根据主键查询
			obj = carPriceDao.get(id); 
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return obj;
	}

	/**
	 * 根据主键数组，查询并返回一组CarPrice对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<CarPrice> getCarPriceByIds(Integer[] ids) {
		List<CarPrice> list = null;
		if (ids == null || ids.length == 0) {
			log.info("param error : " + " ids is null or empty array.");
		} else {
			try {
				//调用dao，根据主键数组查询
				list = carPriceDao.getByIds(ids);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
		
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<CarPrice>(0) : list;

		return list;
	}	
	
	/**
	 * 根据主键，删除特定的CarPrice记录
	 * @param id 主键id
	 * @return
	 */
	@Transactional
	public int delCarPrice(Integer id) {
		int count = 0;
		if (id == null || id <= 0) { //传入的主键无效时直接返回失败结果
			log.info("param error : " + " id = " + id);
			return count;
		}
		try {
			//逻辑删除，实际上执行update语句，以下设置待更新记录的主键、删除标识、更新时间、操作人信息等
			CarPrice carPrice = new CarPrice();
			carPrice.setId(id);
			carPrice.setIsDelete(1);
			carPrice.setUpdateTime(new Date());
			
			//调用Dao执行更新操作，并判断更新语句执行结果
			count = carPriceDao.update(carPrice);			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return count;
	}
		
	/**
	 * 新增一条CarPrice记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param carPrice 新增的CarPrice数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @return
	 */
	@Transactional
	public int addCarPrice(CarPrice carPrice) {
		int count = 0;
		
		if (carPrice != null) { //传入参数无效时直接返回失败结果
			try {
				//如果传入参数的主键为null，则调用生成主键的方法获取新的主键
				if (carPrice.getId() == null) {
					carPrice.setId(this.generatePK());
				}
				
				//设置创建时间和更新时间为当前时间
				Date now = DateUtils.getTimeNow();
				carPrice.setCreateTime(now);
				carPrice.setUpdateTime(now);
				
				//填充默认值
				this.fillDefaultValues(carPrice);
				
				//调用Dao执行插入操作
				carPriceDao.add(carPrice);
				if (carPrice.getId() != null) {
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
	 * 根据主键，更新一条CarPrice记录
	 * @param carPrice 更新的CarPrice数据，且其主键不能为空
	 * @return
	 */
	@Transactional
	public int updateCarPrice(CarPrice carPrice) {
		int count = 0;
				
		if (carPrice != null && carPrice.getId() != null) { //传入参数无效时直接返回失败结果
			try {
				//设置更新时间为当前时间
				carPrice.setUpdateTime(DateUtils.getTimeNow());
				
				//调用Dao执行更新操作，并判断更新语句执行结果
				count = carPriceDao.update(carPrice);			
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
	 * 为CarPrice对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(CarPrice obj) {
		if (obj != null) {
		    if (obj.getIsDelete() == null) {
		    	obj.setIsDelete(0);
		    }
		}
	}

}
