package com.yunxin.cb.rest.common;

import com.yunxin.cb.mall.entity.meta.UploadType;
import com.yunxin.cb.storage.IStorageService;
import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

import static com.yunxin.cb.meta.Result.FAILURE;

/**
 * 公共文件上传接口
 * create by dengchenggang
 */
@Api(description = "公共文件上传接口")
@RestController
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


}
