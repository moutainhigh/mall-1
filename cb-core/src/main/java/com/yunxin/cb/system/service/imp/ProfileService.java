package com.yunxin.cb.system.service.imp;

import com.yunxin.cb.system.MobileOSType;
import com.yunxin.cb.system.dao.ProfileDao;
import com.yunxin.cb.system.entity.Profile;
import com.yunxin.cb.system.meta.ProfileName;
import com.yunxin.cb.system.service.IProfileService;
import com.yunxin.cb.system.vo.AppCheckUpdate;
import com.yunxin.cb.system.vo.ShareInfo;
import com.yunxin.core.persistence.CustomSpecification;
import com.yunxin.core.persistence.PageSpecification;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProfileService implements IProfileService {

    @Resource
    private ProfileDao profileDao;

    /**
     * 根据手机系统类型查询APP版本信息
     *
     * @param mobileOSType
     * @return
     */
    @Override
    public AppCheckUpdate getAppCheckUpdate(MobileOSType mobileOSType) {
        if (mobileOSType == MobileOSType.ANDROID) {
            String v = profileDao.getProfileByProfileName(ProfileName.ANDROID_VERSION_CODE).getFileValue();
            int versionCode = StringUtils.isNotBlank(v) ? Integer.parseInt(v) : 0;
            String versionName = profileDao.getProfileByProfileName(ProfileName.ANDROID_VERSION_NAME).getFileValue();
            String appName = profileDao.getProfileByProfileName(ProfileName.ANDROID_APP_NAME).getFileValue();
            String url = profileDao.getProfileByProfileName(ProfileName.ANDROID_URL).getFileValue();
            String description = profileDao.getProfileByProfileName(ProfileName.ANDROID_DESCRIPTION).getFileValue();
            return new AppCheckUpdate(versionCode, versionName, appName, url, description);
        }

        return null;
    }

    public ShareInfo getShareInfo(String invitationCode){
        String sharePath = profileDao.getProfileByProfileName(ProfileName.SHARE_PATH).getFileValue()+invitationCode;
        String shareTitle = profileDao.getProfileByProfileName(ProfileName.SHARE_TITLE).getFileValue();
        String shareIcon = profileDao.getProfileByProfileName(ProfileName.SHARE_ICON).getFileValue();
        String shareDescription = profileDao.getProfileByProfileName(ProfileName.SHARE_DESCRIPTION).getFileValue();
        String shareShortmessageContent = profileDao.getProfileByProfileName(ProfileName.SHARE_SHORTMESSAGE_CONTENT).getFileValue();
        return new ShareInfo(sharePath,shareTitle,shareIcon,shareDescription,shareShortmessageContent);
    }

    /**
     * 系统配置分页信息
     *
     * @param query
     * @return org.springframework.data.domain.Page<com.yunxin.cb.system.entity.Profile>
     * @throws
     * @author likang
     * @date 2018/7/19 9:50
     */
    @Override
    public Page<Profile> pageProfile(PageSpecification<Profile> query) {
        query.setCustomSpecification(new CustomSpecification<Profile>() {
            @Override
            public void buildFetch(Root<Profile> root) {

            }

            @Override
            public void addConditions(Root<Profile> root, CriteriaQuery<?> query,
                                      CriteriaBuilder builder, List<Predicate> predicates) {
            }
        });
        Page<Profile> page = profileDao.findAll(query, query.getPageRequest());
        return page;
    }

    /**
     * 添加系统配置
     *
     * @return com.yunxin.cb.system.entity.Profile
     * @throws
     * @author likang
     * @date 2018/7/19 10:14
     */
    @Override
    @Transactional
    public Profile addProfile() {
        //如果不存在ANDROID的系统配置，直接添加
        if (null == profileDao.getProfileByProfileName(ProfileName.ANDROID_VERSION_CODE)) {
            profileDao.save(new Profile(ProfileName.ANDROID_VERSION_CODE, ProfileName.ANDROID_VERSION_CODE.getDefaultValue()));
            profileDao.save(new Profile(ProfileName.ANDROID_VERSION_NAME, ProfileName.ANDROID_VERSION_NAME.getDefaultValue()));
            profileDao.save(new Profile(ProfileName.ANDROID_APP_NAME, ProfileName.ANDROID_APP_NAME.getDefaultValue()));
            profileDao.save(new Profile(ProfileName.ANDROID_URL, ProfileName.ANDROID_URL.getDefaultValue()));
            profileDao.save(new Profile(ProfileName.ANDROID_DESCRIPTION, ProfileName.ANDROID_DESCRIPTION.getDefaultValue()));
        } else {
            profileDao.getProfileByProfileName(ProfileName.ANDROID_VERSION_CODE).setFileValue(ProfileName.ANDROID_VERSION_CODE.getDefaultValue());
            profileDao.getProfileByProfileName(ProfileName.ANDROID_VERSION_NAME).setFileValue(ProfileName.ANDROID_VERSION_NAME.getDefaultValue());
            profileDao.getProfileByProfileName(ProfileName.ANDROID_APP_NAME).setFileValue(ProfileName.ANDROID_APP_NAME.getDefaultValue());
            profileDao.getProfileByProfileName(ProfileName.ANDROID_URL).setFileValue(ProfileName.ANDROID_URL.getDefaultValue());
            profileDao.getProfileByProfileName(ProfileName.ANDROID_DESCRIPTION).setFileValue(ProfileName.ANDROID_DESCRIPTION.getDefaultValue());
        }
        return null;
    }

    /**
     * 获取Profile详情
     *
     * @param fileId
     * @return com.yunxin.cb.system.entity.Profile
     * @throws
     * @author likang
     * @date 2018/7/20 16:42
     */
    @Override
    public Profile getProfile(int fileId) {
        return profileDao.findOne(fileId);
    }

    /**
     * 修改参数配置
     *
     * @param profile
     * @return com.yunxin.cb.system.entity.Profile
     * @throws
     * @author likang
     * @date 2018/7/20 16:44
     */
    @Override
    @Transactional
    public Profile updateProfile(Profile profile) {
        Profile oldProfile = profileDao.findOne(profile.getFileId());
        oldProfile.setFileValue(profile.getFileValue());
        oldProfile.setRemarks(profile.getRemarks());
        oldProfile.setIsPicture(profile.getIsPicture());
        return oldProfile;
    }

    @Override
    public Profile getProfileByProfileName(ProfileName profileName) {
        return profileDao.getProfileByProfileName(profileName);
    }

    @Override
    public void addProfileByProfileIsExit(){
        for (ProfileName e : ProfileName.values()) {
            Profile profile=profileDao.getProfileByProfileName(e);
            if(profile==null){
                profile=new Profile();
                profile.setProfileName(e);
                profile.setFileValue(e.getDefaultValue());
                profile.setRemarks(e.getDefaultValue());
                profileDao.save(profile);
            }
        }
    }
}
