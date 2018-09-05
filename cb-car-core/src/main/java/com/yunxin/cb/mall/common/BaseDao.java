package com.yunxin.cb.mall.common;

import java.io.Serializable;
import java.util.List;

import com.yunxin.cb.mall.common.Query;

/**
 * 数据对象基础操作类
 * @author yong
 * 
 */
public interface BaseDao<T,PK extends Serializable> {
	
	/**
	 * 增加对象
	 * @param obj
	 */
	public void add(T obj);

	/**
	 * 修改对象
	 * @param obj
	 */
	public int update(T obj);

	/**
	 * 删除对象
	 * @param pk
	 */
	public int delete(PK pk);

	/**
	 * 得到某个对象
	 * @param pk
	 */
	public T get(PK pk);
	
	/**
	 * 根据主键得到一组对象
	 * @param pks
	 */
	public List<T> getByIds(Integer[] pks);
	
	/**
	 * 获得数据条数
	 * @param obj
	 * @return
	 */
	public Long count(Query obj);
	
	/**
	 * 查询所有数据
	 * @return
	 */
	public List<T> queryAll(Query obj);
	
	/**
	 * 分页查询
	 * @param obj
	 * @return
	 */
	public List<T> pageList(Query obj);
}
