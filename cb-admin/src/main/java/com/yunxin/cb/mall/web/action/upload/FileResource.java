package com.yunxin.cb.mall.web.action.upload;

import com.yunxin.cb.mall.entity.meta.UploadType;
import com.yunxin.cb.mall.web.util.AppParseUtil;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.cb.storage.IStorageService;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
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
    public Map upload(@RequestParam( "file")MultipartFile file, @PathVariable(value = "type") UploadType type,HttpServletRequest request) throws IOException{

        Map<String,String> result=new HashMap<String,String>();
        if (!file.isEmpty()) {
            try {
                String url = "";
                //表示是apk文件，读取版本信息
                if(type.equals(UploadType.ANDROID)){
                    InputStream ins = file.getInputStream();
                    File f=new File(file.getOriginalFilename());
                    inputStreamToFile(ins, f);
                    String key=file.getOriginalFilename().substring(0,file.getOriginalFilename().indexOf('.'))+".apk";
                    url = qiniuStorageService.put(file.getInputStream(), type,key);
                    Map<String,Object> map=AppParseUtil.readAPK(f.getPath());
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

    public static void inputStreamToFile(InputStream ins,File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    }
