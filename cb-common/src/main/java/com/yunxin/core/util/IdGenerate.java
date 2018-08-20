package com.yunxin.core.util;

import com.yunxin.core.meta.IDType;
import com.yunxin.core.meta.SimpleDateFormatEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerate {

    private static Logger logger = LoggerFactory.getLogger(IdGenerate.class);

    private static String strIp = "";
    private static String COMSTR = "CB";

    public static final int SERIALNO_LENGTH = 4; // 流水号最大长度
    public static final int SERIALNO_MAX_VALUE = 9999;// 流水号最大值

    public static final HashMap<String, AtomicInteger> AUTOMICMAP = new HashMap<String, AtomicInteger>();

    // 加载所有的枚举 添加AtomicInteger
    static {
        for (IDType idType : IDType.values()) {
            AUTOMICMAP.put(idType.getType(), new AtomicInteger(0));
        }
    }

    static {
        strIp = getLocalIp();
    }
    /**
     * 生成不重复随机字符串包括字母数字
     *
     * @param len
     * @return
     */
    public static String generateRandomStr(int len) {
        //字符源，可以根据需要删减
        String generateSource = "0123456789abcdefghigklmnopqrstuvwxyz";
        String rtnStr = "";
        for (int i = 0; i < len; i++) {
            //循环随机获得当次字符，并移走选出的字符
            String nowStr = String.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
            rtnStr += nowStr;
            generateSource = generateSource.replaceAll(nowStr, "");
        }
        return rtnStr;
    }

//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(generateRandomStr(8));
//        }
//    }

    /**
     * 截取本地ip最位一段的数字
     *
     * @return
     */
    public static String getLocalIp() {
        String localIp = "";
        int ipLength = 3;
        try {
            String tmp = InetAddress.getLocalHost().getHostAddress();
            tmp = StringUtils.substringAfterLast(tmp, ".");
            if (tmp.length() > ipLength) {
                tmp = tmp.substring(tmp.length() - ipLength);
            }
            localIp = StringUtils.leftPad(tmp, ipLength, '0');
        } catch (Exception e) {
            logger.error("流水号功能无法取得本地IP");

            // 如果取不到ip则随机生成三个数字(危险)
            StringBuilder stringBuilder = new StringBuilder();
            Random rd = new Random();
            for (int i = 0; i < ipLength; i++) {
                stringBuilder.append(rd.nextInt(9));
            }
            localIp = stringBuilder.toString();
        }

        return localIp;
    }

    /**
     * 给流水号添加时间段 yyMMddHHmmssSSS + 4位流水号(最大值 5000)
     *
     * @param idType
     * @return
     */
    private static String genIDByType(IDType idType) {
        StringBuilder order = new StringBuilder(COMSTR);
        order.append(idType.getType());
        order.append(strIp);
        order.append(SimpleDateFormatEnum.sdf15.format(new Date()));
        return IDSerialPart(order, idType.getType());
    }

    /**
     * 给流水号添加流水号
     *
     * @param order
     * @return
     */
    private static String IDSerialPart(StringBuilder order, String type) {
        AtomicInteger atomicInteger = AUTOMICMAP.get(type);

        atomicInteger.compareAndSet(SERIALNO_MAX_VALUE, 0);
        int value = atomicInteger.incrementAndGet();
        return order.append(StringUtils.leftPad("" + value, SERIALNO_LENGTH, '0')).toString();
    }

    /**
     * 生成商品编号
     * @return
     */
    public static String genGoodsID() {
        return  genIDByType(IDType.GOODS);
    }
    /**
     * 生成广告编号
     * @return
     */
    public static String genAdvID() {
        return  genIDByType(IDType.ADV);
    }

    /**
     * 生成品牌编号
     * @return
     */
    public static String genBrandID() {
        return  genIDByType(IDType.BRAND);
    }

    /**
     * 生成价格编号
     * @return
     */
    public static String genPriceID() {
        return  genIDByType(IDType.PRICE);
    }

    /**
     * 生成商家编号
     * @return
     */
    public static String genSellerID() {
        return  genIDByType(IDType.SELLER);
    }

    /**
     * 生成货品编号
     * @return
     */
    public static String genProductID() {
        return  genIDByType(IDType.PRODUCT);
    }



}
