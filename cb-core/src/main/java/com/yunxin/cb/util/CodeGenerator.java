/**
 *
 */
package com.yunxin.cb.util;

import com.yunxin.core.util.CommonUtils;
import com.yunxin.core.util.DateUtils;

import java.util.UUID;

/**
 * @author tanggangyi
 */
public class CodeGenerator {


    public static String getInsuranceCode() {
        String dateS = "119"+DateUtils.getSeriNo() + CommonUtils.randomString(3, CommonUtils.RANDRULE.RAND_NUMBER);
        return dateS;
    }
}
