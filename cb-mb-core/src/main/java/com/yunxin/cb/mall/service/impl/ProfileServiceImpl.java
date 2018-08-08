package com.yunxin.cb.mall.service.impl;

import com.yunxin.cb.mall.entity.Profile;
import com.yunxin.cb.mall.mapper.ProfileMapper;
import com.yunxin.cb.mall.service.ProfileService;

import javax.annotation.Resource;

public class ProfileServiceImpl implements ProfileService {

    @Resource
    private ProfileMapper profileMapper;

    @Override
    public Profile getProfileByName(String profileName) {
        return profileMapper.getProfileByName(profileName);
    }
}
