package com.yunxin.cb.system.service;

import com.yunxin.cb.system.MobileOSType;
import com.yunxin.cb.system.entity.Profile;
import com.yunxin.cb.system.meta.ProfileName;
import com.yunxin.cb.system.vo.AppCheckUpdate;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;

public interface IProfileService {
    AppCheckUpdate getAppCheckUpdate(MobileOSType mobileOSType);

    public Page<Profile> pageProfile(PageSpecification<Profile> query);

    public Profile addProfile();

}
