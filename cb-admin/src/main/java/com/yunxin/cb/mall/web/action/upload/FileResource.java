package com.yunxin.cb.mall.web.action.upload;

import com.yunxin.cb.mall.entity.meta.UploadType;
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

    @RequestMapping(value = "upload/{type}")
    @ResponseBody
    public Map upload(@RequestParam( "file")MultipartFile file, @PathVariable(value = "type") UploadType type) {
        Map<String,String> result=new HashMap<String,String>();
        if (!file.isEmpty()) {
            try {
                String url = qiniuStorageService.put(file.getInputStream(), type);
                result.put("code","0");
                result.put("info","上传成功");
                result.put("url",url);
                return  result;
            } catch (IOException e) {
                e.printStackTrace();
                result.put("code","1");
                result.put("info","上传失败");
                return  result;
            }
        } else {
            result.put("code","1");
            result.put("info","上传失败");
            return  result;
        }

    }

    /**
     * 图片上传
     * @param base64
     * @param type
     * @return
     */
    @RequestMapping(value = "uploadBase64/{type}")
    public ResponseResult uploadBase64(@RequestBody String base64, @PathVariable(value = "type") UploadType type) {
        if (StringUtils.isNotBlank(base64)) {
            try {
                base64 = base64.substring(base64.indexOf("\"") + 1,base64.lastIndexOf("\""));
                byte[] imgBytes = Base64.getDecoder().decode(base64);//Base64转换成byte数组

                String url = qiniuStorageService.put(imgBytes, type);
                return new ResponseResult(url);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseResult(Result.FAILURE, "上传失败");
            }
        } else {
            return new ResponseResult(Result.FAILURE, "上传失败");
        }

    }


}
