package com.yunxin.cb.mall.service;

import java.util.List;

import com.yunxin.cb.mall.common.BaseService;
import com.yunxin.cb.mall.common.PageFinder;
import com.yunxin.cb.mall.common.Query;
import com.yunxin.cb.mall.entity.UserInfo;

/**
 * UserInfo 服务接口类
 */
public interface UserInfoService extends BaseService {

	/**
	 * 根据查询条件，查询并返回UserInfo的列表
	 * @param q 查询条件
	 * @return
	 */
	public List<UserInfo> getUserInfoList(Query q);
	
	/**
	 * 根据查询条件，分页查询并返回UserInfo的分页结果
	 * @param q 查询条件
	 * @return
	 */
	public PageFinder<UserInfo> getUserInfoPagedList(Query q);
	
	/**
	 * 根据主键，查询并返回一个UserInfo对象
	 * @param id 主键id
	 * @return
	 */
	public UserInfo getUserInfo(Integer id);

	/**
	 * 根据主键数组，查询并返回一组UserInfo对象
	 * @param ids 主键数组，数组中的主键值应当无重复值，如有重复值时，则返回的列表长度可能小于传入的数组长度
	 * @return
	 */
	public List<UserInfo> getUserInfoByIds(Integer[] ids);
	
	/**
	 * 根据主键，删除特定的UserInfo记录
	 * @param id 主键id
	 * @return
	 */
	public int delUserInfo(Integer id);
	
	/**
	 * 新增一条UserInfo记录，执行成功后传入对象及返回对象的主键属性值不为null
	 * @param userInfo 新增的UserInfo数据（如果无特殊需求，新增对象的主键值请保留为null）
	 * @return
	 */
	public int addUserInfo(UserInfo userInfo);
	
	/**
	 * 根据主键，更新一条UserInfo记录
	 * @param userInfo 更新的UserInfo数据，且其主键不能为空
	 * @return
	 */
	public int updateUserInfo(UserInfo userInfo);

	/**
	 * 生成主键，如果表主键自增，则本方法可直接返回null；如非自增主键，则本方法必须返回一个大于0的值。
	 * @return
	 */
	public Integer generatePK();
	
		/**
	 * 为UserInfo对象设置默认值
	 * @param obj
	 */
	public void fillDefaultValues(UserInfo obj);

	/**
	 * 根据用户名密码得到用户
	 *
	 * @param loginName
	 * @param loginPassword
	 * @return
	 */
	public UserInfo getSysUser(String loginName, String loginPassword);

    UserInfo getSysUserByUserName(String userName);
}
