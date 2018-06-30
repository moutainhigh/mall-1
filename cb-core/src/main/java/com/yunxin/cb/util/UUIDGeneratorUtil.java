/**
 *
 */
package com.yunxin.cb.util;

import com.yunxin.core.util.CommonUtils;
import com.yunxin.core.util.DateUtils;

import java.util.UUID;

/**
 * @author Aidy_He
 */
public class UUIDGeneratorUtil {

    /***
     * 生成32位UUID串
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString().replaceAll("-", "");
        return str;
    }

    public static String getUUCode() {
        String dateS = DateUtils.getSeriNo() + CommonUtils.randomString(2, CommonUtils.RANDRULE.RAND_NUMBER);
        return dateS;
    }
}
