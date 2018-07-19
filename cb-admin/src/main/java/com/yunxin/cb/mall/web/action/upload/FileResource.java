package com.yunxin.cb.mall.web.action.upload;

import com.yunxin.cb.mall.entity.meta.UploadType;
import com.yunxin.cb.mall.web.util.AppParseUtil;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.cb.storage.IStorageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


/**
 * 七牛云文件上传
 */
@Controller
@RequestMapping(value = "/uploads")
@SessionAttributes({SecurityConstants.LOGIN_SESSION})
public class FileResource {

    @Resource
    private IStorageService qiniuStorageService;

    /**
     * 七牛云文件上传
     * @author      likang
     * @param file
    * @param type
     * @return      java.util.Map
     * @exception
     * @date        2018/7/18 20:53
     */
    @RequestMapping(value = "upload/{type}")
    @ResponseBody
    public Map upload(@RequestParam( "file")MultipartFile file, @PathVariable(value = "type") UploadType type) {
        Map<String,String> result=new HashMap<String,String>();
        if (!file.isEmpty()) {
            try {
                String url = "";
                //表示是apk文件，读取版本信息
                if(type.equals(UploadType.ANDROID)){
                    String key=file.getName()+".apk";
                    url = qiniuStorageService.put(file.getInputStream(), type,key);
                    Map<String,Object> map=AppParseUtil.readAPK(url);
                    result.put("versionCode",map.get("versionCode").toString());
                    result.put("versionName",map.get("versionName").toString());
                }else{
                    url = qiniuStorageService.put(file.getInputStream(), type);
                }
                result.put("url",url);
                return  result;
            } catch (IOException e) {
                e.printStackTrace();
                return  result;
            }
        } else {
            return  result;
        }

    }

}
