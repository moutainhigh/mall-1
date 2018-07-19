package com.yunxin.cb.system.service.imp;

import com.yunxin.cb.system.MobileOSType;
import com.yunxin.cb.system.dao.ProfileDao;
import com.yunxin.cb.system.meta.ProfileName;
import com.yunxin.cb.system.service.IProfileService;
import com.yunxin.cb.system.vo.AppCheckUpdate;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProfileService implements IProfileService {

    @Resource
    private ProfileDao profileDao;

    /**
     * 根据手机系统类型查询APP版本信息
     * @param mobileOSType
     * @return
     */
    @Override
    public AppCheckUpdate getAppCheckUpdate(MobileOSType mobileOSType){
        if(mobileOSType == MobileOSType.ANDROID){
            String v = profileDao.getProfileByProfileName(ProfileName.ANDROID_VERSION_CODE).getFileValue();
            int versionCode = StringUtils.isNotBlank(v)?Integer.parseInt(v):0;
            String versionName = profileDao.getProfileByProfileName(ProfileName.ANDROID_VERSION_NAME).getFileValue();
            String appName = profileDao.getProfileByProfileName(ProfileName.ANDROID_APP_NAME).getFileValue();
            String url = profileDao.getProfileByProfileName(ProfileName.ANDROID_URL).getFileValue();
            String description = profileDao.getProfileByProfileName(ProfileName.ANDROID_DESCRIPTION).getFileValue();
            return new AppCheckUpdate(versionCode,versionName, appName, url, description);
        }

        return null;
    }
}
