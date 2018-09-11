package com.yunxin.cb.storage;

import java.io.InputStream;
import java.util.Map;

public interface IStorageService {




    /**
     * 上传文件
     * @param
     * @return
     */
    public Map<String,String> put(InputStream inputStream, ObjectType objectType);



    public void refresh(String fileName);


    public Map<String,String> deleteByfileName(String fileName);


    public Map<String,String> getQiniuInfo();
}
