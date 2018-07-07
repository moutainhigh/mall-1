package com.yunxin.cb.rest.common;

import com.yunxin.cb.mall.entity.meta.UploadType;
import com.yunxin.cb.storage.IStorageService;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Base64;

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

    @ApiOperation(value = "文件上传")
    @PostMapping(value = "upload/{type}")
    public ResponseResult upload(MultipartFile file, @PathVariable(value = "type") UploadType type) {
        if (!file.isEmpty()) {
            try {
                String url = qiniuStorageService.put(file.getInputStream(), type);
                return new ResponseResult(url);
            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseResult(FAILURE, "上传失败");
            }
        } else {
            return new ResponseResult(FAILURE, "上传失败");
        }

    }

    @ApiOperation(value = "base64图片上传")
    @PostMapping(value = "uploadBase64/{type}")
    public ResponseResult uploadBase64(@RequestBody String base64, @PathVariable(value = "type") UploadType type) {
        if (StringUtils.isNotBlank(base64)) {
            try {
                base64 = base64.substring(base64.indexOf("\"") + 1,base64.lastIndexOf("\""));
                byte[] imgBytes = Base64.getDecoder().decode(base64);//Base64转换成byte数组

                String url = qiniuStorageService.put(imgBytes, type);
                return new ResponseResult(url);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseResult(FAILURE, "上传失败");
            }
        } else {
            return new ResponseResult(FAILURE, "上传失败");
        }

    }


}
