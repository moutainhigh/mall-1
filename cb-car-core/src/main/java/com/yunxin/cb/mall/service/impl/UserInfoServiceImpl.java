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
import com.yunxin.cb.mall.dao.UserInfoDao;
import com.yunxin.cb.mall.entity.UserInfo;
import com.yunxin.cb.mall.service.UserInfoService;
import com.yunxin.cb.util.DateUtils;

/**
 * UserInfo 服务实现类
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

	private static final Log log = LogFactory.getLog(UserInfoServiceImpl.class);
	
	@Resource
	private UserInfoDao userInfoDao;
	

	/**
	 * 根据查询条件，查询并返回UserInfo的列表
	 * @param q 查询条件
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<UserInfo> getUserInfoList(Query q) {
		List<UserInfo> list = null;
		try {
			//直接调用Dao方法进行查询
			list = userInfoDao.queryAll(q);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<UserInfo>(0) : list;
		return list; 
	}
	
	/**
	 * 根据查询条件，分页查询并返回UserInfo的分页结果
	 * @param q 查询条件
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public PageFinder<UserInfo> getUserInfoPagedList(Query q) {
		
		List<UserInfo> list = null;
		long rowCount = 0L;
		
		try {
			//调用dao查询满足条件的分页数据
			list = userInfoDao.pageList(q);
			//调用dao统计满足条件的记录总数
			rowCount = userInfoDao.count(q);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<UserInfo>(0) : list;
	
		//将分页数据和记录总数设置到分页结果对象中
		PageFinder<UserInfo> page = new PageFinder<UserInfo>(q.getPageNo(), q.getPageSize(),rowCount,list);
		
		return page;
	}	
	
	/**
	 * 根据主键，查询并返回一个UserInfo对象
	 * @param id 主键id
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public UserInfo getUserInfo(Integer id) {
		UserInfo obj = null;
		if (id == null || id <= 0) { //传入的主键无效时直接返回null
			log.info("param error : " + " id = " + id);
			return obj;
		}
		try {
			//调用dao，根据主键查询
			obj = userInfoDao.get(id); 
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
		return obj;
	}

	/**
	 * 根据主键数组，查询并返回一组UserInfo对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<UserInfo> getUserInfoByIds(Integer[] ids) {
		List<UserInfo> list = null;
		if (ids == null || ids.length == 0) {
			log.info("param error : " + " ids is null or empty array.");
		} else {
			try {
				//调用dao，根据主键数组查询
				list = userInfoDao.getByIds(ids);
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
		
		//如list为null时，则改为返回一个空列表
		list = list == null ? new ArrayList<UserInfo>(0) : list;

		return list;
	}	
	
	/**
	 * 根据主键，删除特定的UserInfo记录
	 * @param id 主键id
	 * @return
	 */
	@Transactional
	public int delUserInfo(Integer id) {
		int count = 0;
		if (id == null || id <= 0) { //传入的主键无效时直接返回失败结果
			log.info("param error : " + " id = " + id);
			return count;
		}
		try {
		    //调用Dao执行删除，并判断删除语句执行结果
			count = userInfoDao.delete(id);		  
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return count;
	}
		
	/**
	 * 新增一条UserInfo记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param userInfo 新增的UserInfo数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @return
	 */
	@Transactional
	public int addUserInfo(UserInfo userInfo) {
		int count = 0;
		
		if (userInfo != null) { //传入参数无效时直接返回失败结果
			try {
				//如果传入参数的主键为null，则调用生成主键的方法获取新的主键
				if (userInfo.getUserId() == null) {
					userInfo.setUserId(this.generatePK());
				}
				
				//设置创建时间和更新时间为当前时间
				Date now = DateUtils.getTimeNow();
				userInfo.setCreateTime(now);
				
				//填充默认值
				this.fillDefaultValues(userInfo);
				
				//调用Dao执行插入操作
				userInfoDao.add(userInfo);
				if (userInfo.getUserId() != null) {
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
	 * 根据主键，更新一条UserInfo记录
	 * @param userInfo 更新的UserInfo数据，且其主键不能为空
	 * @return
	 */
	@Transactional
	public int updateUserInfo(UserInfo userInfo) {
		int count = 0;
				
		if (userInfo != null && userInfo.getUserId() != null) { //传入参数无效时直接返回失败结果
			try {
				
				//调用Dao执行更新操作，并判断更新语句执行结果
				count = userInfoDao.update(userInfo);			
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
	 * 为UserInfo对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(UserInfo obj) {
		if (obj != null) {
		}
	}

	@Override
	public UserInfo getSysUser(String loginName, String loginPassword) {
		return userInfoDao.getSysUser(loginName, loginPassword);
	}

	@Override
	public UserInfo getSysUserByUserName(String userName) {
		return userInfoDao.getSysUserByUserName(userName);
	}

}
