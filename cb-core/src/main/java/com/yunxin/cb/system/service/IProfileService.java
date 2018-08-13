package com.yunxin.cb.system.service;

import com.yunxin.cb.system.MobileOSType;
import com.yunxin.cb.system.entity.Profile;
import com.yunxin.cb.system.meta.ProfileName;
import com.yunxin.cb.system.vo.AppCheckUpdate;
import com.yunxin.cb.system.vo.ShareInfo;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

public interface IProfileService {
    AppCheckUpdate getAppCheckUpdate(MobileOSType mobileOSType);

    ShareInfo getShareInfo(String invitationCode);

    public Page<Profile> pageProfile(PageSpecification<Profile> query);

    public Profile addProfile();

    public Profile addProfileIos();

    public Profile getProfile(int fileId);

    public Profile updateProfile(Profile profile);

    public Profile getProfileByProfileName(ProfileName profileName);

    public void addProfileByProfileIsExit();
}
