package com.yunxin.common;

import java.util.HashMap;
import java.util.Map;
import com.yunxin.common.ConstantsCB.*;

/**
 * 常量类
 * @Auther: lxc
 * @Date: 2018/8/7 15:36
 * @Description:
 */
public interface ConstantsCBEnumMap {

    /**
     * FundsPoolLog类型,1累计,2报账,
     * @return
     * @author lxc 2018-8-8 下午7:55:02
     */
    static Map<String, String> getFundsPoolLogType() {
        Map<String, String> reMap = new HashMap<String, String>();
        FundsPoolLogType[] logTypes = FundsPoolLogType.values();
        for (FundsPoolLogType lt : logTypes) {
            reMap.put(String.valueOf(lt.getStatus()), lt.getMsg());
        }
        return reMap;
    }
}
