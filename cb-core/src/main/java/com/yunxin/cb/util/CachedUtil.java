package com.yunxin.cb.util;

import java.util.HashMap;
import java.util.Map;

public class CachedUtil {

    private static Map<String,Object> context=new HashMap<String,Object>();

    private static CachedUtil instance=new CachedUtil();
    private CachedUtil()
    {
    }

    public static CachedUtil  getInstance(){
        return instance;
    }

    public void setContext(String key,Object data){
        context.put(key,data);
    }

    public Object getContext(String key){
        return context.get(key);
    }
    public void removeContext(String key){
        context.remove(key);
    }

}
