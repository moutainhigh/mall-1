package com.yunxin.cb.mall.dao;

import com.yunxin.cb.mall.common.BaseDao;
import com.yunxin.cb.mall.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserInfo 数据库处理类
 */
@Mapper
public interface UserInfoDao extends BaseDao<UserInfo,Integer> {

    public UserInfo getSysUser(String loginName, String loginPassword);
}
