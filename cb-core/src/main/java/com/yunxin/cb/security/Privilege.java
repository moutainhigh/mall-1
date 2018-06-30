package com.yunxin.cb.security;

/**
 * Created by gonglei on 16/5/6.
 */
public interface Privilege {

    /**
     * 获得资源编码
     *
     * @return
     */
    String getResourceCode();

    /**
     * 获得角色编码
     *
     * @return
     */
    String getRoleCode();
}
