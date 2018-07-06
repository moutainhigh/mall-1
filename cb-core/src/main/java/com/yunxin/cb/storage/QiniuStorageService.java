package com.yunxin.cb.storage;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.yunxin.cb.mall.entity.meta.UploadType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class QiniuStorageService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${qiniu.accessKey}")
    private String accessKey;

    @Value("${qiniu.secretKey}")
    private String secretKey;

    @Value("${qiniu.bucket-1}")
    private String bucket;

    @Value("${qiniu.domain-1}")
    private String domain;

    @Value("${qiniu.bucket-2}")
    private String bucket_2;

    @Value("${qiniu.domain-2}")
    private String domain_2;

    private UploadManager uploadManager;

    public QiniuStorageService(){
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        uploadManager = new UploadManager(cfg);
    }

    public String put(InputStream inputStream, UploadType type) {
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(inputStream, key, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            String url = domain+putRet.key;
            logger.info("qiniu put success, url:"+url);
            return url;
        } catch (QiniuException ex) {
            Response r = ex.response;
            logger.error(r.toString());
            try {
                logger.error(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return null;
    }
}
