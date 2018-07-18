/**
 *
 */
package com.yunxin.cb.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @author Aidy_He
 */
public class UUIDGeneratorUtil {

    private static Random randGen = null;

    private static char[] numbersAndLetters = null;
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
        String dateS = getSeriNo() + randomString(2, RANDRULE.RAND_NUMBER);
        return dateS;
    }

    /**
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * @return
     *      以yyyyMMddHHmmss为格式的当前系统时间
     */
    public  static String getSeriNo(){
        Date date=new Date();
        DateFormat df=new SimpleDateFormat("yMMddHHmmss");
        return df.format(date);
    }

    /**
     * 产生随机字符串
     * @param length 长度
     * @param rule 规则 LOWER：全小写 ，UPPER：全大写 ，IGNORE：不区分大小写
     * @return
     */
    public static final String randomString(int length,RANDRULE rule) {
        if (length < 1) {
            return null;
        }
        if (randGen == null) {
            randGen = new Random();
            numbersAndLetters = ("123456789abcdefghijklmnopqrstuvwxyz" +
                    "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
        }
        char [] randBuffer = new char[length];
        if(rule.equals(RANDRULE.RAND_NUMBER)){
            for (int i=0; i<randBuffer.length; i++) {
                randBuffer[i] = numbersAndLetters[randGen.nextInt(9)];
            }
        }else{
            for (int i=0; i<randBuffer.length; i++) {
                randBuffer[i] = numbersAndLetters[randGen.nextInt(61)];
            }
        }
        String res=new String(randBuffer);
        if(rule.equals(RANDRULE.RAND_LOWER)){
            res=res.toLowerCase();
        }else if(rule.equals(RANDRULE.RAND_UPPER)){
            res=res.toUpperCase();
        }
        return res;
    }

    public enum RANDRULE{
        RAND_NUMBER,RAND_LOWER,RAND_UPPER,RAND_IGNORE
    }
}
