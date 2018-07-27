package com.yunxin.cb.storage;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.cb.mall.entity.meta.UploadType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class QiniuStorageService implements IStorageService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${qiniu.accessKey}")
    private String accessKey;

    @Value("${qiniu.secretKey}")
    private String secretKey;

    @Value("${qiniu.bucket-1}")
    private String bucket_1;

    @Value("${qiniu.domain-1}")
    private String domain_1;

    private UploadManager uploadManager;

    public QiniuStorageService() {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone2());
        //...其他参数参考类注释
        uploadManager = new UploadManager(cfg);
    }

    @Override
    public String put(InputStream inputStream, UploadType type) {
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String bucket = bucket_1;
        String domain = domain_1;
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(inputStream, key, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            String url = domain + putRet.key;
            logger.info("qiniu put success, url:" + url);
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

    /**
     * 上传带文件名的文件
     * @author      likang
     * @param inputStream
    * @param type 文件夹
    * @param key 文件名
     * @return      java.lang.String
     * @exception
     * @date        2018/7/19 17:36
     */
    @Override
    public String put(InputStream inputStream, UploadType type,String key) {
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        Auth auth = Auth.create(accessKey, secretKey);
        String bucket = bucket_1;
        String domain = domain_1;
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(inputStream, key, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            String url = domain + putRet.key;
            logger.info("qiniu put success, url:" + url);
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

    @Override
    public String put(byte[] data, UploadType type) {
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String bucket = bucket_1;
        String domain = domain_1;
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(data, key, upToken, null, null, true);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            String url = domain + putRet.key;
            logger.info("qiniu put success, url:" + url);
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

    /**
     * 方法实现说明
     * @author      likang
     * @param inputStream
    * @param objectType
     * @return      java.lang.String
     * @exception
     * @date        2018/7/24 14:07
     */
    @Override
    public Map<String,String> put(InputStream inputStream, ObjectType objectType) {
        Map<String,String> result=new HashMap();
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        Long timeStr=new Date().getTime();
        Auth auth = Auth.create(accessKey, secretKey);
        String bucket = bucket_1;
        String domain = domain_1;
        String fileName= objectType.toString()+"/"+timeStr;
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(inputStream, fileName, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            String url = domain + putRet.key;
            result.put("url",url);
            result.put("fileName",fileName);
            result.put("timeStr",timeStr.toString());
            logger.info("qiniu put success, url:" + url);
        } catch (QiniuException ex) {
            Response r = ex.response;
            logger.error(r.toString());
            try {
                logger.error(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return result;
    }

    /**
     * 根据fileName删除文件
     * @author      likang
     * @param objectType
    * @param fileName
     * @return      java.util.Map<java.lang.String,java.lang.String>
     * @exception
     * @date        2018/7/24 16:05
     */
    @Override
    public Map<String,String> deleteByfileName(ObjectType objectType,String fileName) {
        Map<String,String> result=new HashMap();
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        Long timeStr=new Date().getTime();
        Auth auth = Auth.create(accessKey, secretKey);
        String bucket = bucket_1;
        String domain = domain_1;
        String upToken = auth.uploadToken(bucket);
        try {
            //构造一个带指定Zone对象的配置类
            Configuration cfg = new Configuration(Zone.zone2());
            //实例化一个BucketManager对象
            BucketManager bucketManager = new BucketManager(auth, cfg);
            bucketManager.delete(bucket, fileName);
        } catch (QiniuException ex) {
            Response r = ex.response;
            logger.error(r.toString());
            try {
                logger.error(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return result;
    }
}
