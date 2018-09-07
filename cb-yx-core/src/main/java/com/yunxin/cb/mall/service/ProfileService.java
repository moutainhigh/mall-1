package com.yunxin.cb.mall.service;

import com.yunxin.cb.mall.entity.Profile;

public interface ProfileService {

    /**
     * @title: 根据配置名称获取配置
     * @param: [profileName]
     * @return: com.yunxin.cb.mall.entity.Profile
     * @auther: eleven
     * @date: 2018/8/8 16:13
     */
    public Profile getProfileByName(String profileName);
}
