package com.yunxin.cb.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: tanggangyi
 * @Description:
 **/
@Service
public class RedisService {

    public static final String VERIFICATION_CODE = "VerificationCode";


    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 获取验证码
     * @param key
     * @return
     */
    public Object getVerificationCode(String key){
        Map<String, Object> data = (Map<String, Object>) redisTemplate.opsForValue().get(VERIFICATION_CODE);
        if(data == null) {
            return null;
        }else {
            return data.get(key);
        }
    }

    /**
     * 设置验证码
     * @param key
     * @param obj
     */
    public void setVerificationCode(String key, Object obj){
        Map<String, Object> data = (Map<String, Object>) redisTemplate.opsForValue().get(VERIFICATION_CODE);
        if(data == null) {
            data = new HashMap<>();
        }
        data.put(key, obj);
    }


    /**
     * 根据key获取值
     *
     * @param key
     * @return
     */
    public Object getKey(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 根据key设置值
     *
     * @param key
     * @param val
     */
    public void setKey(String key, Object val) {
        redisTemplate.opsForValue().set(key, val);
    }

    /**
     * 根据key删除缓存
     *
     * @param key
     */
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

}
