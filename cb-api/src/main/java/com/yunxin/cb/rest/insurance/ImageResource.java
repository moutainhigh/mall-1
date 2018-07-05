package com.yunxin.cb.rest.insurance;

import com.yunxin.cb.vo.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.yunxin.cb.meta.Result.FAILURE;

/**
 * 公共图片上传接口
 * create by dengchenggang
 */
@Api(description = "公共图片上传接口")
@RestController
@RequestMapping(value = "/common/image")
public class ImageResource {


    @ApiOperation(value = "图片上传")
    @PostMapping(value = "upload")
    public ResponseResult saveOrder(MultipartFile poster, String type) {
        try {
            String fileName = poster.getOriginalFilename();
            long currentTime = System.currentTimeMillis();
            String path = type + File.separatorChar + type + File.separatorChar + currentTime + File.separatorChar + fileName;
            //图片的uri路径
            String uriPath =   path;

            //图片的存储路径
            String dstFilePath =  path;//filePath.getRoot()可以改为你需要的存储路径

            File picFile = new File(dstFilePath);
            if (!picFile.exists()) {
                if (!picFile.getParentFile().exists()) {
                    picFile.getParentFile().mkdirs();
                }
                poster.transferTo(picFile);
            }
            return new ResponseResult(uriPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseResult(FAILURE,"上传失败");
    }


}
