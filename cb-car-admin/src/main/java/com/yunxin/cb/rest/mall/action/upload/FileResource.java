package com.yunxin.cb.rest.mall.action.upload;

import com.yunxin.cb.storage.IStorageService;
import com.yunxin.cb.storage.ObjectType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
public class FileResource {

    @Resource
    private IStorageService qiniuStorageService;


    /**
     * 获取七牛信息
     * @return
     */
    @RequestMapping(value = "getQiniuInfo")
    @ResponseBody
    public Map getQiniuInfo(){
        return qiniuStorageService.getQiniuInfo();
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
    public Map delete(@PathVariable(value = "type") ObjectType type, @RequestParam("key") String key, HttpServletRequest request) throws IOException {
        Map<String, String> result = new HashMap<String, String>();
        //获取保存文件的key
        String fileName=type+"/"+key;
        qiniuStorageService.deleteByfileName(fileName);
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
