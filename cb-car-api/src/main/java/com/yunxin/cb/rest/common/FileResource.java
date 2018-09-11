package com.yunxin.cb.rest.common;

import com.yunxin.cb.mall.entity.meta.UploadType;
import com.yunxin.cb.storage.IStorageService;
import com.yunxin.cb.storage.ObjectType;
import com.yunxin.cb.vo.ImageBase64;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static com.yunxin.cb.meta.Result.FAILURE;

/**
 * 公共文件上传接口
 * create by dengchenggang
 */
@Api(description = "公共文件上传接口")
@RestController
@CrossOrigin
@RequestMapping(value = "/common/file")
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

}
