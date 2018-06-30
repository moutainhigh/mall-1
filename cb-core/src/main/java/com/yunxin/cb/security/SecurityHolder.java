package com.yunxin.cb.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * Created by gonglei on 16/5/5.
 */
public interface SecurityHolder {

    UserDetails getUserDetailsByName(String userName);

    /**
     * 加载权限定义
     *
     * @return
     */
    List<Privilege> loadPrivilegesDefine();
}
