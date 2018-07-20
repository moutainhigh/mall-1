package com.yunxin.cb.mall.web.action.profile;


import com.yunxin.cb.insurance.entity.InsuranceProduct;
import com.yunxin.cb.system.entity.Profile;
import com.yunxin.cb.system.meta.ProfileName;
import com.yunxin.cb.system.service.IProfileService;
import com.yunxin.core.persistence.PageSpecification;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    @Resource
    private IProfileService profileService;
    /**
     * 页面跳转(导入ANDROID版本信息)
     * @author      likang
     * @param modelMap
     * @return      java.lang.String
     * @exception
     * @date        2018/7/17 20:21
     */
    @RequestMapping(value = "profiles")
    public String profiles( ModelMap modelMap) {
        return "profile/profiles";
    }



    /**
     * 进入导入ANDROID版本页面
     * @author      likang
     * @return      java.lang.String
     * @exception
     * @date        2018/7/19 10:05
     */
    @RequestMapping(value = "toAddProfile")
    public String toAddProfile(ModelMap modelMap){
        return "profile/addprofile";
    }

    /**
     * 进入修改页面
     * @author      likang
     * @param modelMap
     * @return      java.lang.String
     * @exception
     * @date        2018/7/20 16:40
     */
    @RequestMapping(value = "toEditProfiles")
    public String toEditProfiles(ModelMap modelMap,@RequestParam("fileId") int fileId){
        modelMap.addAttribute("profile", profileService.getProfile(fileId));
        return "profile/editprofile";
    }

    /**
     * 修改参数配置
     * @author      likang
     * @param profile
     * @return      java.lang.String
     * @exception
     * @date        2018/7/20 16:47
     */
    @RequestMapping(value = "updateProfile", method = RequestMethod.POST)
    public String updateProfile(@ModelAttribute("profile") Profile profile){
        profileService.updateProfile(profile);
        return "redirect:../common/success.do?reurl=profile/profiles.do";
    }

    /**
     * 添加系统配置
     * @author      likang
     * @param
     * @return      java.lang.String
     * @exception
     * @date        2018/7/19 9:56
     */
    @RequestMapping(value = "addProfile", method = RequestMethod.POST)
    public String addProfile(@ModelAttribute("ANDROID_VERSION_CODE") String ANDROID_VERSION_CODE,
                             @ModelAttribute("ANDROID_VERSION_NAME") String ANDROID_VERSION_NAME,
                             @ModelAttribute("ANDROID_APP_NAME") String ANDROID_APP_NAME,
                             @ModelAttribute("ANDROID_URL") String ANDROID_URL,
                             @ModelAttribute("ANDROID_DESCRIPTION") String ANDROID_DESCRIPTION){
        ProfileName.ANDROID_VERSION_CODE.setDefaultValue(ANDROID_VERSION_CODE);
        ProfileName.ANDROID_VERSION_NAME.setDefaultValue(ANDROID_VERSION_NAME);
        ProfileName.ANDROID_APP_NAME.setDefaultValue(ANDROID_APP_NAME);
        ProfileName.ANDROID_URL.setDefaultValue(ANDROID_URL);
        ProfileName.ANDROID_DESCRIPTION.setDefaultValue(ANDROID_DESCRIPTION);
        profileService.addProfile();
        return "redirect:../common/success.do?reurl=profile/profiles.do";
    }


    /**
     * 系统配置分页信息
     * @author      likang
     * @param query
     * @return      org.springframework.data.domain.Page<com.yunxin.cb.system.entity.Profile>
     * @exception
     * @date        2018/7/19 9:56
     */
    @RequestMapping(value = "pageProfile", method = RequestMethod.POST)
    @ResponseBody
    public Page<Profile> pageProfile(@RequestBody PageSpecification<Profile> query) {
        Page<Profile> page = profileService.pageProfile(query);
        return page;
    }
}
