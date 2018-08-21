package com.yunxin.cb.system.service.imp;

import com.yunxin.cb.redis.RedisService;
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

    @Resource
    private RedisService redisService;

    /**
     * 根据手机系统类型查询APP版本信息
     *
     * @param mobileOSType
     * @return
     */
    @Override
    public AppCheckUpdate getAppCheckUpdate(MobileOSType mobileOSType) {
        if (mobileOSType == MobileOSType.ANDROID) {
            String v = getRedis(profileDao.getProfileByProfileName(ProfileName.ANDROID_VERSION_CODE).getFileValue(), ProfileName.ANDROID_VERSION_CODE.toString());
            int versionCode = StringUtils.isNotBlank(v) ? Integer.parseInt(v) : 0;
            String versionName = getRedis(profileDao.getProfileByProfileName(ProfileName.ANDROID_VERSION_NAME).getFileValue(), ProfileName.ANDROID_VERSION_NAME.toString());
            String appName = getRedis(profileDao.getProfileByProfileName(ProfileName.ANDROID_APP_NAME).getFileValue(), ProfileName.ANDROID_APP_NAME.toString());
            String url = getRedis(profileDao.getProfileByProfileName(ProfileName.ANDROID_URL).getFileValue(), ProfileName.ANDROID_URL.toString());
            String description = getRedis(profileDao.getProfileByProfileName(ProfileName.ANDROID_DESCRIPTION).getFileValue(), ProfileName.ANDROID_DESCRIPTION.toString());
            return new AppCheckUpdate(versionCode, versionName, appName, url, description);
        }else if(mobileOSType == MobileOSType.IOS){
            String v = getRedis(profileDao.getProfileByProfileName(ProfileName.IOS_VERSION_CODE).getFileValue(), ProfileName.IOS_VERSION_CODE.toString());
            int versionCode = StringUtils.isNotBlank(v) ? Integer.parseInt(v) : 0;
            String versionName = getRedis(profileDao.getProfileByProfileName(ProfileName.IOS_VERSION_NAME).getFileValue(), ProfileName.IOS_VERSION_NAME.toString());
            String appName = getRedis(profileDao.getProfileByProfileName(ProfileName.IOS_APP_NAME).getFileValue(), ProfileName.IOS_APP_NAME.toString());
            String url = getRedis(profileDao.getProfileByProfileName(ProfileName.IOS_URL).getFileValue(), ProfileName.IOS_URL.toString());
            String description = getRedis(profileDao.getProfileByProfileName(ProfileName.IOS_DESCRIPTION).getFileValue(), ProfileName.IOS_DESCRIPTION.toString());
            return new AppCheckUpdate(versionCode, versionName, appName, url, description);
        }
        return null;
    }

    /**
     * 获取redis信息
     * @param daoVal
     * @param key
     * @return
     */
    public String getRedis(String daoVal,String key){
           if(redisService.getKey(key)!=null&&!redisService.getKey(key).toString().equals("")){
              return redisService.getKey(key).toString();
           }else{
              return daoVal;
           }
    }

    public ShareInfo getShareInfo(String invitationCode){
        String sharePath =getRedis(profileDao.getProfileByProfileName(ProfileName.SHARE_PATH).getFileValue(),ProfileName.SHARE_PATH.toString())+invitationCode;
        String shareTitle =getRedis(profileDao.getProfileByProfileName(ProfileName.SHARE_TITLE).getFileValue(),ProfileName.SHARE_TITLE.toString());
        String shareIcon =getRedis(profileDao.getProfileByProfileName(ProfileName.SHARE_ICON).getFileValue(),ProfileName.SHARE_ICON.toString());
        String shareDescription =getRedis(profileDao.getProfileByProfileName(ProfileName.SHARE_DESCRIPTION).getFileValue(),ProfileName.SHARE_DESCRIPTION.toString());
        String shareShortmessageContent =getRedis( profileDao.getProfileByProfileName(ProfileName.SHARE_SHORTMESSAGE_CONTENT).getFileValue(),ProfileName.SHARE_SHORTMESSAGE_CONTENT.toString());
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
        List<Profile> list=page.getContent();
//        list.stream().forEach(p ->{
//            redisService.updateRedisByKey(p.getProfileName().toString(),p.getFileValue());
//        });
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
        /**更新redis信息*/
        redisService.updateRedisByKey(ProfileName.ANDROID_VERSION_CODE.toString(),ProfileName.ANDROID_VERSION_CODE.getDefaultValue());
        redisService.updateRedisByKey(ProfileName.ANDROID_VERSION_NAME.toString(),ProfileName.ANDROID_VERSION_NAME.getDefaultValue());
        redisService.updateRedisByKey(ProfileName.ANDROID_APP_NAME.toString(),ProfileName.ANDROID_APP_NAME.getDefaultValue());
        redisService.updateRedisByKey(ProfileName.ANDROID_URL.toString(),ProfileName.ANDROID_URL.getDefaultValue());
        redisService.updateRedisByKey(ProfileName.ANDROID_DESCRIPTION.toString(),ProfileName.ANDROID_DESCRIPTION.getDefaultValue());
        return null;
    }


    /**
     * 添加IOS系统配置
     * @return
     */
    @Override
    @Transactional
    public Profile addProfileIos() {
        //如果不存在IOS的系统配置，直接添加
        if (null == profileDao.getProfileByProfileName(ProfileName.IOS_VERSION_CODE)) {
            profileDao.save(new Profile(ProfileName.IOS_VERSION_CODE, ProfileName.IOS_VERSION_CODE.getDefaultValue()));
            profileDao.save(new Profile(ProfileName.IOS_VERSION_NAME, ProfileName.IOS_VERSION_NAME.getDefaultValue()));
            profileDao.save(new Profile(ProfileName.IOS_APP_NAME, ProfileName.IOS_APP_NAME.getDefaultValue()));
            profileDao.save(new Profile(ProfileName.IOS_URL, ProfileName.IOS_URL.getDefaultValue()));
            profileDao.save(new Profile(ProfileName.IOS_DESCRIPTION, ProfileName.IOS_DESCRIPTION.getDefaultValue()));
        } else {
            profileDao.getProfileByProfileName(ProfileName.IOS_VERSION_CODE).setFileValue(ProfileName.IOS_VERSION_CODE.getDefaultValue());
            profileDao.getProfileByProfileName(ProfileName.IOS_VERSION_NAME).setFileValue(ProfileName.IOS_VERSION_NAME.getDefaultValue());
            profileDao.getProfileByProfileName(ProfileName.IOS_APP_NAME).setFileValue(ProfileName.IOS_APP_NAME.getDefaultValue());
            profileDao.getProfileByProfileName(ProfileName.IOS_URL).setFileValue(ProfileName.IOS_URL.getDefaultValue());
            profileDao.getProfileByProfileName(ProfileName.IOS_DESCRIPTION).setFileValue(ProfileName.IOS_DESCRIPTION.getDefaultValue());
        }
        /**更新redis信息*/
        redisService.updateRedisByKey(ProfileName.IOS_VERSION_CODE.toString(),ProfileName.IOS_VERSION_CODE.getDefaultValue());
        redisService.updateRedisByKey(ProfileName.IOS_VERSION_NAME.toString(),ProfileName.IOS_VERSION_NAME.getDefaultValue());
        redisService.updateRedisByKey(ProfileName.IOS_APP_NAME.toString(),ProfileName.IOS_APP_NAME.getDefaultValue());
        redisService.updateRedisByKey(ProfileName.IOS_URL.toString(),ProfileName.IOS_URL.getDefaultValue());
        redisService.updateRedisByKey(ProfileName.IOS_DESCRIPTION.toString(),ProfileName.IOS_DESCRIPTION.getDefaultValue());
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
//        String test=redisService.getKey(profile.getProfileName().toString()).toString();
        redisService.updateRedisByKey(profile.getProfileName().toString(),profile.getFileValue());
        return oldProfile;
    }

    
    @Override
    public Profile getProfileByProfileName(ProfileName profileName) {
        return profileDao.getProfileByProfileName(profileName);
    }

    /**
     * 加载系统配置项
     */
    @Override
    @Transactional
    public void addProfileByProfileIsExit(){
        for (ProfileName e : ProfileName.values()) {
            Profile profile=profileDao.getProfileByProfileName(e);
            if(profile==null){
                profile=new Profile();
                profile.setProfileName(e);
                profile.setFileValue(e.getDefaultValue());
                profile.setRemarks(e.getDefaultValue());
                profile.setIsPicture(0);
                profileDao.save(profile);
                /**添加redis信息*/
                redisService.setKey(e.toString(),e.getDefaultValue());
            }else if(null==profile.getFileValue()||profile.getFileValue().equals("")){
                profile=profileDao.getOne(profile.getFileId());
                profile.setFileValue(e.getDefaultValue());
                /**更新redis信息*/
                redisService.updateRedisByKey(profile.getProfileName().toString(),profile.getFileValue());
            }else{
                /**更新redis信息*/
                redisService.updateRedisByKey(profile.getProfileName().toString(),profile.getFileValue());
            }
        }
    }
}
