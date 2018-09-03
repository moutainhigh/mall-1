package com.yunxin.cb.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangteng
 */

public class RSAUtils {
    private static final Logger log = LoggerFactory.getLogger(RSAUtils.class);
    /**
     * 加密算法
     */
    public static final String KEY_ALGORITHM = "RSA";
    /**
     * 签名算法
     */
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";
    /**
     * 获得公钥
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";
    /**
     * 获取私钥
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 生成密钥对(公钥和私钥)
     * @return
     * @throws Exception
     */
    public static Map<String, Object> genKeyPair() throws Exception {
                 KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
                 keyPairGen.initialize(1024);
                 KeyPair keyPair = keyPairGen.generateKeyPair();
                 RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
                 RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
                 Map<String, Object> keyMap = new HashMap<String, Object>(2);
                 keyMap.put(PUBLIC_KEY, publicKey);
                 keyMap.put(PRIVATE_KEY, privateKey);
                 return keyMap;
             }

    /**
     * 用私钥对信息生成数字签名
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
                 byte[] keyBytes = Base64Utils.decode(privateKey);
                 PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
                 KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
                 PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
                 Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
                 signature.initSign(privateK);
                 signature.update(data);
                 return Base64Utils.encode(signature.sign());
             }

    /**
     * 校验数字签名
     * @param data
     * @param publicKey
     * @param sign
     * @return
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
                 byte[] keyBytes = Base64Utils.decode(publicKey);
                 X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
                 KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
                 PublicKey publicK = keyFactory.generatePublic(keySpec);
                 Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
                 signature.initVerify(publicK);
                 signature.update(data);
                 return signature.verify(Base64Utils.decode(sign));
             }

    /**
     *  私钥解密
     * @param encryptedData
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) throws Exception {
                 byte[] keyBytes = Base64Utils.decode(privateKey);
                 PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
                 KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
                 Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
                 Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
                 cipher.init(Cipher.DECRYPT_MODE, privateK);
                 int inputLen = encryptedData.length;
                 ByteArrayOutputStream out = new ByteArrayOutputStream();
                 int offSet = 0;
                 byte[] cache;
                 int i = 0;
                 // 对数据分段解密
                 while (inputLen - offSet > 0) {
                         if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                                 cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
                             } else {
                                 cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
                             }
                         out.write(cache, 0, cache.length);
                         i++;
                         offSet = i * MAX_DECRYPT_BLOCK;
                     }
                 byte[] decryptedData = out.toByteArray();
                 out.close();
                 return decryptedData;
             }

    /**
     * 公钥解密
     * @param encryptedData
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) throws Exception {
                 byte[] keyBytes = Base64Utils.decode(publicKey);
                 X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
                 KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
                 Key publicK = keyFactory.generatePublic(x509KeySpec);
                 Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
                 cipher.init(Cipher.DECRYPT_MODE, publicK);
                 int inputLen = encryptedData.length;
                 ByteArrayOutputStream out = new ByteArrayOutputStream();
                 int offSet = 0;
                 byte[] cache;
                 int i = 0;
                 // 对数据分段解密
                 while (inputLen - offSet > 0) {
                         if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                                 cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
                             } else {
                                 cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
                             }
                         out.write(cache, 0, cache.length);
                         i++;
                         offSet = i * MAX_DECRYPT_BLOCK;
                     }
                 byte[] decryptedData = out.toByteArray();
                 out.close();
                 return decryptedData;
             }

    /**
     * 公钥加密
     * @param data
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
                 byte[] keyBytes = Base64Utils.decode(publicKey);
                 X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
                 KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
                 Key publicK = keyFactory.generatePublic(x509KeySpec);
                 // 对数据加密
                 Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
                 cipher.init(Cipher.ENCRYPT_MODE, publicK);
                 int inputLen = data.length;
                 ByteArrayOutputStream out = new ByteArrayOutputStream();
                 int offSet = 0;
                 byte[] cache;
                 int i = 0;
                 // 对数据分段加密
                 while (inputLen - offSet > 0) {
                         if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                                 cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
                             } else {
                                 cache = cipher.doFinal(data, offSet, inputLen - offSet);
                             }
                         out.write(cache, 0, cache.length);
                         i++;
                         offSet = i * MAX_ENCRYPT_BLOCK;
                     }
                 byte[] encryptedData = out.toByteArray();
                 out.close();
                 return encryptedData;
             }

    /**
     * 私钥加密
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String privateKey) throws Exception {
                 byte[] keyBytes = Base64Utils.decode(privateKey);
                 PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
                 KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
                 Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
                 Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
                 cipher.init(Cipher.ENCRYPT_MODE, privateK);
                 int inputLen = data.length;
                 ByteArrayOutputStream out = new ByteArrayOutputStream();
                 int offSet = 0;
                 byte[] cache;
                 int i = 0;
                 // 对数据分段加密
                 while (inputLen - offSet > 0) {
                         if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                                 cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
                             } else {
                                 cache = cipher.doFinal(data, offSet, inputLen - offSet);
                             }
                         out.write(cache, 0, cache.length);
                         i++;
                         offSet = i * MAX_ENCRYPT_BLOCK;
                     }
                 byte[] encryptedData = out.toByteArray();
                 out.close();
                 return encryptedData;
             }

    /**
     * 获取私钥
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap) throws Exception {
                 Key key = (Key) keyMap.get(PRIVATE_KEY);
                 return Base64Utils.encode(key.getEncoded());
             }

    /**
     *  获取公钥
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap) throws Exception {
                 Key key = (Key) keyMap.get(PUBLIC_KEY);
                 return Base64Utils.encode(key.getEncoded());
             }

    /**
     * 公钥加密
     * @param data
     * @param PUBLICKEY
     * @return
     */
     public static String encryptedData(String data, String PUBLICKEY) {
                 try {
                         data = Base64Utils.encode(encryptByPublicKey(data.getBytes(), PUBLICKEY));
                     } catch (Exception e) {
                         // TODO Auto-generated catch block
                     log.info("encryptedData faild",e);
                     }
                 return data;
             }

    /**
     * 私钥解密
     * @param data
     * @param PRIVATEKEY
     * @return
     */
    public static String decryptData(String data, String PRIVATEKEY) {
                 String temp = "";
                 try {
                         byte[] rs = Base64Utils.decode(data);
                         temp = new String(RSAUtils.decryptByPrivateKey(rs, PRIVATEKEY),"UTF-8"); //以utf-8的方式生成字符串

                     } catch (Exception e) {
                     log.info("decryptData faild",e);
                     }
                 return temp;
             }

}
