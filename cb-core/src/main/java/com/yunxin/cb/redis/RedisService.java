package com.yunxin.cb.redis;

import com.yunxin.cb.mall.dao.CustomerDao;
import com.yunxin.cb.mall.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: tanggangyi
 * @Description:
 **/
@Service
public class RedisService {

    public static final String VERIFICATION_CODE = "VerificationCode";
    public static final String CUSTOMER_LIST="customer";
    public static final String CUSTOMERCODE="customerCode";
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    @Resource
    private CustomerDao customerDao;
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
            data.put(key,obj);
        redisTemplate.opsForValue().set(VERIFICATION_CODE,data);

    }

    /**
     * 获取等级编码与邀请码
     * @param key
     * @return
     */
    public Object getCustomercode(String key){
        Map<String, Object> data = (Map<String, Object>) redisTemplate.opsForValue().get(CUSTOMERCODE);
        if(data == null) {
            if("invitationCode".equals(key)){
                return "CCCCCC";
            }else{
                return "CCCC";
            }
        }else {
            return data.get(key);
        }
    }

    /**
     * 设置等级编码与邀请码
     * @param key
     * @param obj
     */
    public void setCustomercode(String key, Object obj){
        Map<String, Object> data = (Map<String, Object>) redisTemplate.opsForValue().get(CUSTOMERCODE);
        if(data == null) {
            data = new HashMap<>();
        }
        data.put(key,obj);
        redisTemplate.opsForValue().set(CUSTOMERCODE,data);
    }

    /**
     * 获取账户
     * @return
     */
    public Object getCustomerList(){
        Map<String, Object> data = (Map<String, Object>) redisTemplate.opsForValue().get(CUSTOMER_LIST);
        if(data == null) {
            data = new HashMap<>();
            List<Customer>  list=customerDao.findAll();
            for (Customer customer:list)
                data.put(customer.getMobile(),customer);
            redisTemplate.opsForValue().set(CUSTOMER_LIST,data);
        }
        return data;
    }

    /**
     * 设置账户
     * @param key
     * @param obj
     */
    public void setCustomerList(String key, Object obj){
        Map<String, Object> data = (Map<String, Object>) redisTemplate.opsForValue().get(CUSTOMER_LIST);
        if(data == null) {
            data = new HashMap<>();
        }
        data.put(key,obj);
        redisTemplate.opsForValue().set(CUSTOMER_LIST,data);
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


    /**
     * 更新key
     * @param key
     * @param object
     */
    public void updateRedisByKey(String key,Object object){
        deleteKey(key);
        setKey(key,object);
    }

}
