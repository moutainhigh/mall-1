package com.yunxin.cb.mall.web.action.upload;

import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.cb.mall.entity.meta.UploadType;
import com.yunxin.cb.mall.web.util.AppParseUtil;
import com.yunxin.cb.security.SecurityConstants;
import com.yunxin.cb.storage.IStorageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
* @Description:    七牛云图片上传工具类
* @Author:         likang
* @CreateDate:     2018/7/18 20:21
*/
@Controller
@RequestMapping(value = "/uploads")
@SessionAttributes({SecurityConstants.LOGIN_SESSION})
public class FileResource {

    @Resource
    private IStorageService qiniuStorageService;

    /**
     * 七牛云文件上传（用于保险产品保留）
     *
     * @param file
     * @param type
     * @return java.util.Map
     * @throws
     * @author likang
     * @date 2018/7/18 20:53
     */
    @RequestMapping(value = "upload/{type}")
    @ResponseBody
    public Map upload(@RequestParam("file") MultipartFile file, @PathVariable(value = "type") UploadType type, HttpServletRequest request) throws IOException {
        Map<String, String> result = new HashMap<String, String>();
        if (!file.isEmpty()) {
            try {
                String url = "";
                //表示是apk文件，读取版本信息
                if (type.equals(UploadType.ANDROID)) {
                    InputStream ins = file.getInputStream();
                    File f = new File(file.getOriginalFilename());
                    inputStreamToFile(ins, f);
                    String key = file.getOriginalFilename().substring(0, file.getOriginalFilename().length()-4) + ".apk";
                    url = qiniuStorageService.put(file.getInputStream(), type, key);
                    Map<String, Object> map = AppParseUtil.readAPK(f.getPath());
                    result.put("versionCode", map.get("versionCode").toString());
                    result.put("versionName", map.get("versionName").toString());
                } else {
                    url = qiniuStorageService.put(file.getInputStream(), type);
                }
                result.put("url", url);
                return result;
            } catch (IOException e) {
                e.printStackTrace();
                return result;
            }
        } else {
            return result;
        }
    }


    /**
     * 上传文件
     * @author      likang
     * @param file
    * @param type
    * @param request
     * @return      java.util.Map
     * @exception
     * @date        2018/7/24 14:04
     */
    @RequestMapping(value = "uploadFile/{type}")
    @ResponseBody
    public Map uploadFile(@RequestParam("file") MultipartFile file, @PathVariable(value = "type") ObjectType type, HttpServletRequest request) throws IOException {
        Map<String, String> result = new HashMap<String, String>();
        if (!file.isEmpty()) {
            try {
                result=qiniuStorageService.put(file.getInputStream(),type);
                return result;
            } catch (IOException e) {
                e.printStackTrace();
                return result;
            }
        } else {
            return result;
        }
    }

    /**
     * 删除文件
     * @author      likang
     * @param type
    * @param key
    * @param request
     * @return      java.util.Map
     * @exception
     * @date        2018/7/24 16:07
     */
    @RequestMapping(value = "delete/{type}")
    @ResponseBody
    public Map delete(@PathVariable(value = "type") ObjectType type,@RequestParam("key") String key, HttpServletRequest request) throws IOException {
        Map<String, String> result = new HashMap<String, String>();
        //获取保存文件的key
        String fileName=type+"/"+key;
        qiniuStorageService.deleteByfileName(type,fileName);
        return result;

    }


    /**
     * InputStream转成File
     *
     * @param ins
     * @param file
     * @return void
     * @throws
     * @author likang
     * @date 2018/7/23 9:42
     */
    public static void inputStreamToFile(InputStream ins, File file) {
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
